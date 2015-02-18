package com.freddiemac.scc

import com.freddiemac.scc.utils.PropertyRetriever;
import com.freddiemac.scc.entities.EventProcessLog;
import com.freddiemac.scc.entities.EventType;
import com.freddiemac.scc.model.PropContainer;
import com.freddiemac.scc.model.PoolSearch
import com.freddiemac.scc.entities.Status;

class CollapseController {

	def searchService
	def dispatchService
	def grailsApplication

	def index() {
	}

	def search(PoolSearch poolSearch) {

		if(poolSearch.hasErrors()) {
			render view: "index", model: [poolSearch: poolSearch]
			return
		}

		def m = searchService.searchPool(params.cusipIdentifier, params.poolNumber)
		if (!m.success) {
			flash.error =  message(code: 'Collapse.controller.search.error1', args: [params.cusipIdentifier])
			render view: 'index'
		} else {
			String poolid = poolSearch.poolNumber ?:PropertyRetriever.getProp(grailsApplication.config.com.freddiemac.searchpool.result.poolid, m.result)
			String cusip = poolSearch.cusipIdentifier ?:PropertyRetriever.getProp(grailsApplication.config.com.freddiemac.searchpool.result.cusip, m.result)
                        String secIssueDt = PropertyRetriever.getProp(grailsApplication.config.com.freddiemac.searchpool.result.securityissuedate, m.result)
                        def criteria = EventProcessLog.createCriteria()
                        def eventLogs =  criteria.list { 
                                eq("eventType" , EventType.COLLAPSE)
                                eq("cusip", cusip)
                                ne("status", Status.CANCELLED)
                        }
                        def isCollapsed = false
                        if((eventLogs && eventLogs.size() > 0) || (secIssueDt!="")) {
                            isCollapsed  = true
                        }
			render view: 'index', model: ['result': generateModel(m.result), isCollapsed:isCollapsed, poolid: poolid, cusip: cusip]
		}
	}


	def collapse() {
		if(params.poolid) {
			dispatchService.collapsePool(params.poolid, params.cusip)
			flash.message = message(code: 'Collapse.controller.collapse.success', args: [params.cusip])
		} else {
			flash.error = message(code: 'Collapse.controller.collapse.fail', args: [params.cusip])
		}
		redirect action: "index", params:params
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
