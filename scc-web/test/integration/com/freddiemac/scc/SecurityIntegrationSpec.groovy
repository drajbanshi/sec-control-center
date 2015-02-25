package com.freddiemac.scc

import spock.lang.Ignore;
import spock.lang.Unroll;
import grails.test.spock.IntegrationSpec

class SecurityIntegrationSpec extends IntegrationSpec {

	def datamanagerService

	def setup() {
	}

	def cleanup() {
	}

	@Ignore
	void "fetch data from request table based on identifier using groovysql"(){

		given:
		def requestId = '00348505-38d0-4dd1-8d84-d67d00fb48a1'

		when:"fetch based on the request id :  00348505-38d0-4dd1-8d84-d67d00fb48a1"
		def res = datamanagerService.findByRequestId requestId

		then:"should fetch correct data"
		res!=null
	}
	
	void "payload test"() {
		given:
		String eventId = "AB83543510987654321098765432109871QQ"
		
		when:
		def res = datamanagerService.findEventPayload(eventId)
		
		then:
		res != null
		res.EventMetadata != null
	}
}
