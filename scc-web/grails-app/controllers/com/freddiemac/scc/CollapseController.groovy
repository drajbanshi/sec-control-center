package com.freddiemac.scc

import com.freddiemac.scc.utils.PropertyRetriever;
import com.freddiemac.scc.entities.EventProcessLog;
import com.freddiemac.scc.entities.EventType;
import com.freddiemac.scc.model.PropContainer;
import com.freddiemac.scc.model.PoolSearch

class CollapseController {
    
    	def searchService
	def grailsApplication

    def index() {
        
    }
	
	
      	def search(PoolSearch poolSearch) {
            def searchInput
		/*if (!params.cusip && !params.pool ) {
			flash.error =  "Enter a valid CUSIP ID or a Pool number"
			render view: 'index'
			return
		} else {
                    if (params.cusip) {
                        searchInput = params.cusip
                    } else {
                        searchInput = params.pool
                    } 
                } */
                //println "cus: "+poolSearch.cusipIdentifier
                if(!poolSearch.validate()) {
                    render view: "index", model: [poolSearch: poolSearch]
                    return
                    }
  
		def m = searchService.searchPool(params.cusipIdentifier, params.poolNumber)
                println "result:"+m.result
		if (!m.success) {
			flash.error =  "Pool Unavailable for the given CUSIP ID/Pool number ${params.cusipIdentifier}"
			render view: 'index'
		} else {
			flash.error = EventProcessLog.findByCusip(params?.cusipIdentifier)!=null? "A Collapse request has already been initiated for the given CUSIP ID/Pool number ${params.cusipIdentifier}": ''
			render view: 'index', model: ['result': generateModel(m.result), isCollapse:flash.error!='']
		}
	}    
    
	
	def collapse() {
            println params.cusipIdentifier
		if (!params.cusipIdentifier) {                        
			flash.error =  "Invalid cusip"
			redirect action: 'index'
			return
		} else {
			flash.message =  "Pool ${params.cusipIdentifier} collapsed successfully"
			redirect action: 'index'
			return
                }
	}
        
	private def generateModel(def m) {
		def keys = grailsApplication.config.com.freddiemac.searchpool.result.elements
		def mm = []
		keys.each {
                    println "key: "+it+" - value: "+m
			mm.add(new PropContainer(key: it, value: PropertyRetriever.getProp(it, m)))
		}
		return mm
	}    
}
