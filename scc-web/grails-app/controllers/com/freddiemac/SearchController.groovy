package com.freddiemac

import com.freddiemac.entities.EventProcessLog
import com.freddiemac.entities.EventType
import com.freddiemac.entities.Status

class SearchController {
	def searchService
	
	def dispatchService

	def grailsApplication

	def index() {
	}

	def search() {
		if (!params.cusip) {
			flash.error =  "Enter a valid CUSIP ID"
			redirect action: 'index', method: "Get"
		}
		def m = searchService.searchPool(params.cusip)
		
		if (!m.success) {
			flash.error =  "Pool Unavailable for CUSIP ID=${params.cusip}"
			render view: 'index'
		} else {
			flash.message = EventProcessLog.findByCusip(params?.cusip)!=null? "CUSIP ID=${params.cusip} is already sent for Dissolve process": ''
			render view: 'index', model: ['result': PropertyRetriever.getProp(grailsApplication.config.com.freddiemac.security.node.path, m.events), isDissolve:flash.message!='']
		}
		
		
	}
	
	def dissolve() {
		if (!params.cusip) {
			flash.message =  "Invalid cusip"
			redirect action: 'index'
			return
		}
		
		def m = searchService.searchPool(params.cusip)
		m.events.EventMetaData.EventName = 'DISSOLVE_EVENT'
		
	//	print XmlUtil.serialize(m)
		
		EventProcessLog e = new EventProcessLog(cusip: params.cusip, status: Status.INITIALIZED, eventType: EventType.DISSOLVE)
		e.save()
		
		if(dispatchService.dissolveSecurity(params.cusip)) {
			flash.message = "Dissolve event sent successfully"
		} else {
		   flash.message  = "Error sending dissolve event"
		}
		
		
		
		render view: 'index', model: ['result': PropertyRetriever.getProp(grailsApplication.config.com.freddiemac.security.node.path, m.events),'isDissolve':true]

	}
}
