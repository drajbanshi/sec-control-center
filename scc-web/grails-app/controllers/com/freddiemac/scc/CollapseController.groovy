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
		if (!params.cusip) {
			flash.error =  "Enter a valid CUSIP ID"
			render view: 'index'
			return
		}
		def m = searchService.searchPool(params.cusip)

		if (!m.success) {
			flash.error =  "Pool Unavailable for CUSIP ID=${params.cusip}"
			render view: 'index'
		} else {
			flash.error = EventProcessLog.findByCusip(params?.cusip)!=null? "A Collapse request has already been initiated for CUSIP ID ${params.cusip}": ''
			render view: 'index', model: ['result': generateModel( PropertyRetriever.getProp(grailsApplication.config.com.freddiemac.businessdata.path, m.events)), isDissolve:flash.error!='']
		}
	}    
    
	
	def collapse() {
		if (!params.cusip) {
			flash.error =  "Invalid cusip"
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
