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

	def grailsApplication

	def index() {
	}

	def search() {
		if (!params.cusip) {
			flash.error =  "Enter a valid CUSIP ID"
			redirect action: 'index', method: "Get"
		}
		def m = searchService.searchPool(params.cusip)
		
		if (m.equals("Not available")) {
			flash.error =  "Pool Unavailable"
			redirect action: 'index', method: "Get"
		} else {
			render view: 'index', model: ['result': PropertyRetriever.getProp(grailsApplication.config.com.freddiemac.security.node.path, m)]
		}
		
		
	}
	
	def dissolve() {
		if (!params.cusip) {
			flash.message =  "Invalid cusip"
			redirect action: 'index'
			return
		}
		
		def m = searchService.searchPool(params.cusip)
		m.EventMetaData.EventName = 'DISSOLVE_EVENT'
		
		//print XmlUtil.serialize(m)
		
		EventProcessLog e = new EventProcessLog(cusip: params.cusip, status: Status.INITIALIZED)
		e.save()
		
		EventNotification en = new EventNotification()
		//Events events = en.createEventFromXML(new File('c:/Users/c38051/event.xml'));

		Events e1 = en.notifyEvent(new File("c:/Users/c38051/test.xml"))
		
		flash.message = "Dissolved event sent successfully"
		render view: 'index', model: ['result': PropertyRetriever.getProp(grailsApplication.config.com.freddiemac.security.node.path, m)]

	}
}
