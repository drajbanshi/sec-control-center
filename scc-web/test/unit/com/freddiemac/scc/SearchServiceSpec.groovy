package com.freddiemac.scc

import static com.github.tomakehurst.wiremock.client.WireMock.*
import spock.lang.Specification

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.common.SingleRootFileSource
import com.github.tomakehurst.wiremock.core.WireMockConfiguration

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(SearchService)
class SearchServiceSpec extends Specification {

	WireMockServer server 
	
	static doWithConfig(c) {
		c.com.freddiemac.mbs.server = "localhost:7777"
		
	}
	
	def "grailsApplication is not null"() {
		expect:
		grailsApplication != null
		
		
	}
	
	def "test invalid cusip"() {
		when:
			def m =	service.searchPool("CUS121212")
			
		then:
			assert m.success == false
			
	}
	
	
	def "test valid cusip"() {
		when:
		def m = service.searchPool("CUSIP1234")
		
		then:
		assert m.success == true
		assert m.events != null
	}
	
	def "test invalid response"() {
		when:
		service.searchPool("CUSIP4343434")
		
		then:
		thrown(Exception)

	}
	
    def setup() {
		server = new WireMockServer(new WireMockConfiguration().port(7777).fileSource(new SingleRootFileSource("../stub-server")))
		server.start()
	
    }

    def cleanup() {
	   server.stop()
    }
}
