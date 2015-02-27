package com.freddiemac.scc

import spock.lang.Specification

import com.freddiemac.scc.utils.PropertyRetriever
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
        
	def "test invalid pool number"() {
		when:
			def m =	service.searchPool("","POOL1214533")
			
		then:
			assert m.success == false
			assert m.errorCode == 404
			assert m.errorMessage != null
			
	}    
	
	
	def "test valid cusip for cash"() {
		when:
		def m = service.searchPool("CUSIP1234","")
		
		then:
		assert m.success == true
		assert m.result != null
		String p = PropertyRetriever.getProp('Loan.LoanAmortizationType', m.result)
		assert p != null && !p.isEmpty() 
                String poolType = PropertyRetriever.getProp('Pool.PoolType', m.result)
                assert poolType != null && !poolType.isEmpty() && poolType.equalsIgnoreCase("Cash")
	}
        
	def "test valid cusip for guarantor"() {
		when:
		def m = service.searchPool("CUSIP2222","")
		
		then:
		assert m.success == true
		assert m.result != null
		String p = PropertyRetriever.getProp('Pool.PoolType', m.result)
		assert p != null && !p.isEmpty() && p.equalsIgnoreCase("Guarantor")
	}    
        
	def "test valid cusip for giant"() {
		when:
		def m = service.searchPool("CUSIP3333","")
		
		then:
		assert m.success == true
		assert m.result != null
		String p = PropertyRetriever.getProp('Pool.PoolType', m.result)
		assert p != null && !p.isEmpty() && p.equalsIgnoreCase("Giant")
	}        
        
	def "test valid pool number for cash"() {
		when:
		def m = service.searchPool("","POOL12345")
		
		then:
		assert m.success == true
		assert m.result != null
		String p = PropertyRetriever.getProp('Loan.LoanAmortizationType', m.result)
		assert p != null && !p.isEmpty() 
                String poolType = PropertyRetriever.getProp('Pool.PoolType', m.result)
                assert poolType != null && !poolType.isEmpty() && poolType.equalsIgnoreCase("Cash")
	}
        
	def "test valid pool number for guarantor"() {
		when:
		def m = service.searchPool("","POOL22222")
		
		then:
		assert m.success == true
		assert m.result != null
		String p = PropertyRetriever.getProp('Pool.PoolType', m.result)
		assert p != null && !p.isEmpty() && p.equalsIgnoreCase("Guarantor")
	}    
        
	def "test valid pool number for giant"() {
		when:
		def m = service.searchPool("","POOL33333")
		
		then:
		assert m.success == true
		assert m.result != null
		String p = PropertyRetriever.getProp('Pool.PoolType', m.result)
		assert p != null && !p.isEmpty() && p.equalsIgnoreCase("Giant")
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
