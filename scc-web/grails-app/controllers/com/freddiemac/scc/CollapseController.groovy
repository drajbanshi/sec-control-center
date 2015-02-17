package com.freddiemac.scc

import com.freddiemac.scc.utils.PropertyRetriever;
import com.freddiemac.scc.entities.EventProcessLog;
import com.freddiemac.scc.entities.EventType;
import com.freddiemac.scc.model.PropContainer;
import com.freddiemac.scc.model.PoolSearch

class CollapseController {

	def searchService
	def dispatchService
	def grailsApplication

	def index() {
	}

	def search(PoolSearch poolSearch) {
		def searchInput

		if(poolSearch.hasErrors()) {
			render view: "index", model: [poolSearch: poolSearch]
			return
		}

		def m = searchService.searchPool(params.cusipIdentifier, params.poolNumber)
		if (!m.success) {
			flash.error =  "Pool Unavailable for the given CUSIP ID/Pool number ${params.cusipIdentifier}"
			render view: 'index'
		} else {
			String poolid = poolSearch.poolNumber ?:PropertyRetriever.getProp(grailsApplication.config.com.freddiemac.searchpool.result.poolid, m.result)
			String cusip = poolSearch.cusipIdentifier ?:PropertyRetriever.getProp(grailsApplication.config.com.freddiemac.searchpool.result.cusip, m.result)
			render view: 'index', model: ['result': generateModel(m.result), isCollapse:flash.error!='', poolid: poolid, cusip: cusip]
		}
	}


	def collapse() {
		if(params.poolid) {
			dispatchService.collapsePool(params.poolid, params.cusip)
			flash.message = "Collapse event sent successfully"
		} else {
			flash.error = "No pool id to collapse"
		}
		redirect action: "search", params:params
	}

	private def generateModel(def m) {
		def keys = grailsApplication.config.com.freddiemac.searchpool.result.elements
		def mm = []
		keys.each {
			mm.add(new PropContainer(key: it, value: PropertyRetriever.getProp(it, m)))
		}
		return mm
	}
}
