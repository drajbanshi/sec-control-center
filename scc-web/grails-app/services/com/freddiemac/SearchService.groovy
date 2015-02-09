package com.freddiemac

import grails.transaction.Transactional
import wslite.soap.SOAPClient
import groovy.io.FileType


class SearchService {
	
	def grailsApplication
	
	def searchPool(String cus) {
        SOAPClient client = new SOAPClient("http://${grailsApplication.config.com.freddiemac.mbs.server}/freddiemac/services/searchload.asmx")
        def response = client.send(SOAPAction:'SearchLoan') {
            body {
                SearchLoan('xmlns':'http://www.freddiemac.com/search') {
                    cusip(cus)
                }
            }
        }
		
		def  rsp = new XmlSlurper().parseText(response.getText())
		def rspObjectList = rsp.depthFirst().collect()
		def searchDetails = [:]
		//println "Prop : "+PropertyRetriever.getProp("EventPayload.EventContainer.BusinessData", response.SearchLoanResponse.SearchLoanResult.Events)
		rsp.depthFirst().collect().each{node->
					searchDetails.put(node.name(), node.text())
			}
		
		def p = response.SearchLoanResponse.SearchLoanResult.Error
		
		if(!p.isEmpty()) {
			return [success: false,
				    message: response.SearchLoanResponse.SearchLoanResult.Error.Message]
		}
		//return [success: true, events: response.SearchLoanResponse.SearchLoanResult.Events]
		return [success: true, events: searchDetails]
	 }
}
