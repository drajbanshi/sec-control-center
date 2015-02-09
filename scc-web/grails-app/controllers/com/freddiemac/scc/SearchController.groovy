package com.freddiemac.scc

import com.freddiemac.scc.entities.EventProcessLog;
import com.freddiemac.scc.entities.EventType;
import com.freddiemac.scc.entities.Status;
import com.freddiemac.scc.model.PropContainer;
import com.freddiemac.scc.utils.PropertyRetriever;

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
			flash.error = EventProcessLog.findByCusip(params?.cusip)!=null? "CUSIP ID=${params.cusip} is already sent for Dissolve process": ''
			render view: 'index', model: ['result': generateModel( PropertyRetriever.getProp(grailsApplication.config.com.freddiemac.businessdata.path, m.events)), isDissolve:flash.error!='']
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

	def dissolve() {
		if (!params.cusip) {
			flash.message =  "Invalid cusip"
			redirect action: 'index'
			return
		}

		def m = searchService.searchPool(params.cusip)
		m.events.EventMetaData.EventName = 'DISSOLVE_EVENT'


		EventProcessLog e = new EventProcessLog(cusip: params.cusip, status: Status.INITIALIZED, eventType: EventType.DISSOLVE)
		e.save()

		if(m.success) {
			if(dispatchService.dissolveSecurity(m.events)) {
				flash.message = "Dissolve event sent successfully"
			} else {
				flash.message  = "Error sending dissolve event"
			}
		} else {
			flash.error =  "Pool Unavailable for CUSIP ID=${params.cusip}"
		}


		render view: 'index', model: ['result': generateModel( PropertyRetriever.getProp(grailsApplication.config.com.freddiemac.businessdata.path, m.events)), isDissolve:flash.message!='']
	}
}
