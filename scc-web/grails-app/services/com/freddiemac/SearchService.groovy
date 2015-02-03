package com.freddiemac

import grails.transaction.Transactional
import wslite.soap.SOAPClient
import groovy.io.FileType


class SearchService {

    def searchPool(String cus) {
        SOAPClient client = new SOAPClient('http://localhost:9999/freddiemac/services/searchload.asmx')
        def response = client.send(SOAPAction:'FindLoan') {
            body {
                FindLoan('xmlns':'http://www.freddiemac.com/search') {
                    cusip('adfadf')
                }
            }
        }
		
		println response.text
		
		return response
	}
}
