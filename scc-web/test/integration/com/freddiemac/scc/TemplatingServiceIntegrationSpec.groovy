package com.freddiemac.scc

import grails.test.spock.IntegrationSpec

class TemplatingServiceIntegrationSpec extends IntegrationSpec {
	
	def xmlTemplatingService

    void "test collapse event xml"() {
		when:
		String poolId = "abcdef"
		String xml = xmlTemplatingService.generateCollapseEvent(poolId)
		print xml
		then:
		assert xml != null && !xml.isEmpty()
		assert xml.contains(poolId)
		
    }
}
