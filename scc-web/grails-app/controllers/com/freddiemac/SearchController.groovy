package com.freddiemac

import com.freddiemac.entities.EventProcessLog;
import com.freddiemac.entities.Status;

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
			render view: 'search', model: ['result': PropertyRetriever.getProp(grailsApplication.config.com.freddiemac.security.node.path, m)]
		}
		
		
	}
	
	def dissolve() {
		if (!params.cusip) {
			flash.message =  "Invalid cusip"
			redirect action: 'search', method: "Get"
		}
		
		def m = searchService.searchPool(params.cusip)
		m.EventMetaData.EventName = 'DISSOLVE_EVENT'
		
		print XmlUtil.serialize(m)
		
		EventProcessLog e = new EventProcessLog(cusip: params.cusip, status: Status.INITIALIZED)
		e.save()
		

	}
}
