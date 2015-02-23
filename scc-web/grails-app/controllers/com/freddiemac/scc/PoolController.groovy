package com.freddiemac.scc

import com.freddiemac.scc.model.PoolSearch
import com.freddiemac.scc.model.PropContainer
import com.freddiemac.scc.utils.PropertyRetriever

class PoolController {

	def searchService
	def dispatchService
	def grailsApplication
	def eventLogService


	def index() {
		render view: "/pool/index"
	}

	def search(PoolSearch poolSearch) {

		if(poolSearch.hasErrors()) {
			render view: "/pool/index", model: [poolSearch: poolSearch, poolid: params.poolNumber, cusip: params.cusipIdentifier]
			return
		}

		def m = searchService.searchPool(poolSearch.cusipIdentifier, poolSearch.poolNumber)
		if (!m.success) {
			if (poolSearch.cusipIdentifier)
				flash.error =  message(code: 'Collapse.controller.search.error1', args: [poolSearch.cusipIdentifier])
			else
				flash.error =  message(code: 'Collapse.controller.search.error2', args: [poolSearch.poolNumber])
			render view: 'index'
		} else {
			String poolid = poolSearch.poolNumber ?:PropertyRetriever.getProp(grailsApplication.config.com.freddiemac.searchpool.result.poolid, m.result)
			String cusip = poolSearch.cusipIdentifier ?:PropertyRetriever.getProp(grailsApplication.config.com.freddiemac.searchpool.result.cusip, m.result)
			String secIssueDt = PropertyRetriever.getProp(grailsApplication.config.com.freddiemac.searchpool.result.securityissuedate, m.result)

			def isCollapsed = (secIssueDt && !secIssueDt.isEmpty()) || eventLogService.isEventProcessedForCusip(cusip)
			render view: 'index', model: ['result': generateModel(m.result), isCollapsed:isCollapsed, poolid: poolid, cusip: cusip]
		}
	}


	def collapse() {
		if(params.reqPoolNum && params.reqCUSIP) {
			if(dispatchService.collapsePool(params.reqPoolNum, params.reqCUSIP)) {
				flash.message = message(code: 'Collapse.controller.collapse.success', args: [params.cusipIdentifier])
			} else {
				flash.error = message(code: 'Collapse.controller.collapse.error', args: [params.cusipIdentifier])
			}
		} else {
			flash.error = message(code: 'Collapse.controller.collapse.fail', args: [params.cusipIdentifier])
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

	def dissolve() {
		//TODO: should be implemented depending on changes and with reference to Dissolve controller
	}
}
