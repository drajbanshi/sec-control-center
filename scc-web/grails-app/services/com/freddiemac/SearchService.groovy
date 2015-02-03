package com.freddiemac

import grails.transaction.Transactional
import wslite.soap.SOAPClient
import groovy.io.FileType


class SearchService {
	
	def grailsApplication
	
	def searchPool(String cus) {
        SOAPClient client = new SOAPClient("http://${grailsApplication.config.com.freediemac.mbs.server}/freddiemac/services/searchload.asmx")
        def response = client.send(SOAPAction:'SearchLoan') {
            body {
                SearchLoan('xmlns':'http://www.freddiemac.com/search') {
                    cusip(cus)
                }
            }
        }
		return response.SearchLoanResponse.SearchLoanResult.Events
	 }
}
