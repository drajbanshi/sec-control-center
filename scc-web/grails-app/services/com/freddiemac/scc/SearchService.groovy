package com.freddiemac.scc

import com.freddiemac.scc.utils.PropertyRetriever;

import wslite.soap.SOAPClient
import wslite.soap.SOAPClientException;


class SearchService {

	def grailsApplication

	def searchPool(String cus, String poolId) {
		SOAPClient client = new SOAPClient(grailsApplication.config.com.freddiemac.searchpool.url)
		def response
		try {
			response = client.send(SOAPAction:'SearchPool') {
				body {
					SearchPool('xmlns':'http://www.freddiemac.com/search') {
						cusip(cus)
						poolid(poolId)
					}
				}
			}


			def p = PropertyRetriever.getProp(grailsApplication.config.com.freddiemac.searchpool.error.path, response.getBody())
			if(p != null && !p.isEmpty()) {
				return [success: false, errorMessage: p.ErrorMessage,errorCode: p.ErrorCode ]
			}
			return [success: true, result: PropertyRetriever.getProp(grailsApplication.config.com.freddiemac.searchpool.result.path, response.getBody())]
		} catch (SOAPClientException e) {
			log.error(e)
			return [success: false, errorMessage: "Search System unavailable",errorCode: "" ]
		}
	}
}
