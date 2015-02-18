package com.freddiemac.scc

import com.freddiemac.scc.entities.EventProcessLog;
import com.freddiemac.scc.entities.EventType;
import com.freddiemac.scc.entities.Status;
import com.freddiemac.scc.model.PropContainer;
import com.freddiemac.scc.utils.PropertyRetriever;

class DissolveController {
	def searchService

	def dispatchService

	def grailsApplication

	
	def search() {
		if (!params.cusip) {
			flash.error =  message(code: 'Dissolve.controller.cusip.invalid')
			render view: 'index'
			return
		}
		def m = searchService.searchPool(params.cusip,params.poolid)

		if (!m.success) {
			flash.error =  message(code: 'Dissolve.controller.cusip.search.error1', args: [params.cusip])
			render view: 'index'
		} else {
			flash.error = EventProcessLog.findByCusip(params?.cusip)!=null? message(code: 'Dissolve.controller.cusip.dissolved', args: [params.cusip]): ''
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
			flash.error =  message(code: 'Dissolve.controller.cusip.invalid')
			redirect action: 'index'
			return
		}

		def m = searchService.searchPool(params.cusip,params.poolid)
	
		if(m.success) {
			EventProcessLog e = new EventProcessLog(cusip: params.cusip, status: Status.INITIALIZED, eventType: EventType.DISSOLVE)
			e.save()
			
			m.events.EventMetaData.EventName = 'DISSOLVE_EVENT'
			
			
			if(dispatchService.dissolveSecurity(m.events)) {
				flash.message = message(code: 'Dissolve.controller.cusip.dissolve.success')
			} else {
				flash.error  = message(code: 'Dissolve.controller.cusip.dissolve.fail')
			}
		} else {
			flash.error =  message(code: 'Dissolve.controller.cusip.search.error1', args: [params.cusip])
		}


		render view: 'index', model: ['result': m.success ? generateModel( PropertyRetriever.getProp(grailsApplication.config.com.freddiemac.businessdata.path, m.events)): null, isDissolve:flash.message!='' && flash.error != '']
	}
}
