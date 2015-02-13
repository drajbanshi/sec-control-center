package com.freddiemac.scc

import com.freddiemac.scc.utils.PropertyRetriever;
import com.freddiemac.scc.entities.EventProcessLog;
import com.freddiemac.scc.entities.EventType;
import com.freddiemac.scc.model.PropContainer;

class CollapseController {
    
    	def searchService

	def grailsApplication

    def index() {
        
    }
	
	
      	def search() {
            def searchInput
		if (!params.cusip && !params.pool ) {
			flash.error =  "Enter a valid CUSIP ID or a Pool number"
			render view: 'index'
			return
		} else {
                    if (params.cusip) {
                        searchInput = params.cusip
                    } else {
                        searchInput = params.pool
                    }
                }                
                
		def m = searchService.searchPool(searchInput)

		if (!m.success) {
			flash.error =  "Pool Unavailable for the given CUSIP ID/Pool number ${searchInput}"
			render view: 'index'
		} else {
			flash.error = EventProcessLog.findByCusip(params?.cusip)!=null? "A Collapse request has already been initiated for the given CUSIP ID/Pool number ${searchInput}": ''
			render view: 'index', model: ['result': generateModel( PropertyRetriever.getProp(grailsApplication.config.com.freddiemac.businessdata.path, m.events)), isCollapse:flash.error!='']
		}
	}    
    
	
	def collapse() {
            println params.cusip
		if (!params.cusip) {                        
			flash.error =  "Invalid cusip"
			redirect action: 'index'
			return
		} else {
			flash.message =  "Pool ${params.cusip} collapsed successfully"
			redirect action: 'index'
			return
                }
	}
        
	private def generateModel(def m) {
		def keys = grailsApplication.config.com.freddiemac.onedotfive.interf
		def mm = []
		keys.each {
			mm.add(new PropContainer(key: it, value: PropertyRetriever.getProp(it, m)))
		}
		return mm
	}    
}
