package com.freddiemac

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller

import com.freddiemac.entities.EventProcessLog;
import com.freddiemac.entities.Status;
import com.freddiemac.service.event.api.EventNotification;
import com.freddiemac.service.event.model.EventCompressedPayloadType
import com.freddiemac.service.event.model.EventMetaData
import com.freddiemac.service.event.model.EventPayloadType
import com.freddiemac.service.event.model.EventTypeEnumerated;
import com.freddiemac.service.event.model.Events;



import grails.converters.XML
import groovy.xml.XmlUtil;

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
			render view: 'index', model: ['result': PropertyRetriever.getProp(grailsApplication.config.com.freddiemac.security.node.path, m.events)]
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
		
		EventProcessLog e = new EventProcessLog(cusip: params.cusip, status: Status.INITIALIZED)
		e.save()
		
		if(dispatchService.dissolvePool(params.cusip)) {
			flash.message = "Dissolve event sent successfully"
		} else {
		   flash.message  = "Error sending dissolve event"
		}
		
		
		render view: 'index', model: ['result': PropertyRetriever.getProp(grailsApplication.config.com.freddiemac.security.node.path, m.events)]

	}
}
