package com.freddiemac.scc

import static com.github.tomakehurst.wiremock.client.WireMock.*
import spock.lang.Specification

import com.freddiemac.scc.utils.PropertyRetriever;
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.common.SingleRootFileSource
import com.github.tomakehurst.wiremock.core.WireMockConfiguration

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(SearchService)
class SearchServiceSpec extends Specification {

	WireMockServer server 
	
	def setup() {
		server = new WireMockServer(new WireMockConfiguration().port(7777).fileSource(new SingleRootFileSource("../stub-server")))
		server.start()
	
	}
	
	static doWithConfig(c) {
		c.com.freddiemac.searchpool.url = "http://localhost:7777/freddiemac/searchpool.asmx"
		
	}
	
	def "grailsApplication is not null"() {
		expect:
		grailsApplication != null
		
		
	}
	
	def "test invalid cusip"() {
		when:
			def m =	service.searchPool("CUS121212","")
			
		then:
			assert m.success == false
			assert m.errorCode == 404
			assert m.errorMessage != null
			
	}
	
	
	def "test valid cusip"() {
		when:
		def m = service.searchPool("CUSIP1234","")
		
		then:
		assert m.success == true
		assert m.result != null
		String p = PropertyRetriever.getProp('Loan.LoanAmortizationType', m.result)
		assert p != null && !p.isEmpty() 
	}
	
	def "test invalid response"() {
		when:
		service.searchPool("CUSIP4343434")
		
		then:
		thrown(Exception)

	}
	
  

    def cleanup() {
	   server.stop()
    }
}
