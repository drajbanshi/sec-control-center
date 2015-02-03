package com.freddiemac

import grails.transaction.Transactional
import wslite.soap.SOAPClient
import groovy.io.FileType


class SearchService {

	String p = "SearchLoanResponse.SearchLoanResult.Events.EventPayload.EventContainer.BusinessData.CspSecIssuanceReqContentSet.SecIssuanceReqContainer.CollateralGroupContainer.CollateralInstruments.CollateralInstrumentContainer.CollateralSecurityContainer.Security"
    def searchPool(String cus) {
        SOAPClient client = new SOAPClient('http://localhost:9999/freddiemac/services/searchload.asmx')
        def response = client.send(SOAPAction:'SearchLoan') {
            body {
                SearchLoan('xmlns':'http://www.freddiemac.com/search') {
                    cusip(cus)
                }
            }
        }

		
		return response.SearchLoanResponse.SearchLoanResult.Events.EventPayload.EventContainer.BusinessData.CspSecIssuanceReqContentSet.SecIssuanceReqContainer.CollateralGroupContainer.CollateralInstruments.CollateralInstrumentContainer.CollateralSecurityContainer.Security
	 }
}
