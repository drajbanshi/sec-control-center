package com.freddiemac

import grails.converters.XML
import groovy.xml.XmlUtil;

class SearchController {
	def searchService

	def grailsApplication

	def index() {
	}

	def search() {
		if (!params.cusip) {
			flash.message =  "Enter a valid CUSIP ID"
			redirect action: 'index', method: "Get"
		}
		def m = searchService.searchPool(params.cusip)
		//print XmlUtil.serialize(m)
		if (m.equals("Not available")) {
			flash.message =  "Pool not found"
			redirect action: 'index', method: "Get"
		} else {
			
		
			render view: 'search', model: ['result': PropertyRetriever.getProp(grailsApplication.config.com.freddiemac.security.node.path, m)]
		}
	}
}
