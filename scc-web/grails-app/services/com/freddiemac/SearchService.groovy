package com.freddiemac

import grails.transaction.Transactional
import wslite.soap.SOAPClient


class SearchService {

    def searchPool(String cus) {

        SOAPClient client = new SOAPClient('http://192.168.1.147:9999/freddiemac/services/searchload.asmx')
        def response = client.send(SOAPAction:'FindLoan') {
            body {
                FindLoan('xmlns':'http://www.freddiemac.com/search') {
                    cusip('adfadf')
                }
            }
        }

        return response.text
    }
}
