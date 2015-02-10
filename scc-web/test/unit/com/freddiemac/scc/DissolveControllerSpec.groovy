package com.freddiemac.scc

import com.freddiemac.scc.entities.EventProcessLog;

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(DissolveController)
@grails.test.mixin.Mock([EventProcessLog])
class DissolveControllerSpec extends Specification {
	
	static doWithConfig(c) {
        c.com.freddiemac.onedotfive.interf = ['test1','test2','test3']
		c.com.freddiemac.businessdata.path = 'mytest'
    }
	
	def "grailsApplication is not null"() {
		expect:
		grailsApplication != null
		
		
	}
	
	

    def setup() {
		def searchService = Mock(SearchService)
		searchService.searchPool("CUSIP1234") >> ['success': false, 'events':  {}]
		searchService.searchPool("CUSIP2222") >> ['success': true, 'events':   ['mytest': ["test1":"", "test2": "", "test3": ""],"EventMetaData": ["EventName": "Test"]]]
		controller.searchService = searchService
		
		def dispatchService = Mock(DispatchService)
		dispatchService.dissolveSecurity(_) >> true
		controller.dispatchService = dispatchService
    }
	
	

    def cleanup() {
    }

    void "no Cusip set"() {
		when:
		params.cusip = ''
		controller.search()
		
		then:
		flash.error != ''
    }
	
	void "valid cusip set but Pool Unavailable"() {
		when:
		params.cusip = 'CUSIP1234'
		controller.search()
		
		then:
		flash.error != ''
		
	}
	
	void "valid cusip with pool available"() {
		when:
		params.cusip = "CUSIP2222"
		controller.search()
		
		then:
		model.result != null
		model.result.size() == 3
	}
	
	void "dissolve pool with invalid cusip"() {
		when:
		params.cusip = "CUSIP1234"
		controller.dissolve()
		
		then:
		flash.error != ''
		
	}
	
	void "dissolve pool with valid cusip"() {
		when:
		params.cusip = "CUSIP2222"
		controller.dissolve()
		
		then:
		model.result != null
		model.result.size() == 3
		model.isDissolve == true
		
		
	}
}
