package com.freddiemac

import grails.converters.XML

class SearchController {
    def searchService

    def index() {


    }

    def search() {
	  if (!params.cusip) {
	  	flash.message =  "Enter a valid CUSPID"
		  redirect action: 'index', method: "Get"
		  }
      def m = searchService.searchPool(params.cusip)      
	  if (m.equals("Not available")) {
	  flash.message =  "CUSPID Unavailable"
	  redirect action: 'index', method: "Get"
	  } else {
	  	render view: 'search', model: ['result': m]
	  
	  }
	  
    }
}
