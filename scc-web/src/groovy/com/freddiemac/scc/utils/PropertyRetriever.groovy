package com.freddiemac.scc.utils

import wslite.soap.SOAPResponse;
import groovy.util.slurpersupport.GPathResult;

class PropertyRetriever {

	static def getProp(String propx, GPathResult o) {
		if(!o) {
			return null
		}
		def r = o
		for(def p : propx.split("\\.")) {
			r = r[p]
			if(!r || r.isEmpty()) {
				return null
			}
		}
		return r
	}
	
	static void main(String[] args) {
		SOAPResponse response = new SOAPResponse()
		response.setEnvelope(new XmlSlurper().parse(new File("../stub-server/__files/POOL_1.xml")))
		
        def p = getProp("SearchPoolResponse.ResponseEnvelope.SearchResultContainer", response.getBody())
		
		println p.toString()
		
		p = getProp("Loan.LoanAmortizationType", p)
		
		println p.toString()
		
		p = getProp("SearcPoolResponse.ResponseEnvelope.ErrorEnvelope", response.getBody())
		println p
		
	}
	
}
