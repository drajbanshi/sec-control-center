package com.freddiemac.scc

import com.freddiemac.scc.entities.EventProcessLog;

import grails.test.mixin.TestFor
import spock.lang.Specification
import com.freddiemac.scc.model.PoolSearch
import groovy.util.XmlSlurper

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(CollapseController)
@grails.test.mixin.Mock([EventProcessLog])
class CollapseControllerSpec extends Specification {
	
	
	def "grailsApplication is not null"() {
		expect:
		grailsApplication != null
		
		
	}
	
	

    def setup() {
		def searchService = Mock(SearchService)
                def eventLogService = Mock(EventLogService)
                eventLogService.isEventProcessedForCusip("CUSIP1111") >> false
                eventLogService.isEventProcessedForCusip("CUSIP1234") >> true
		searchService.searchPool("CUSIP1111","") >> ['success': true, 'result': new XmlSlurper().parseText("<mytest><test1/><test2/><test3/></mytest>") ]
                searchService.searchPool("","POOL12345") >> ['success': true, 'result': new XmlSlurper().parseText("<mytest><test1/><test2/><test3/></mytest>") ]
		searchService.searchPool("CUSIP7899","") >> ['success': false, 'result': '' ]
                searchService.searchPool("","POOL7899") >> ['success': false, 'result': '' ]                
		controller.searchService = searchService
                controller.eventLogService = eventLogService
		
		def dispatchService = Mock(DispatchService)
		dispatchService.collapsePool("POOL12345","CUSIP1111") >> true
                dispatchService.collapsePool("POOL7899","CUSIP7899") >> false
		controller.dispatchService = dispatchService
    }
	
	

    def cleanup() {
    }
	
	void "valid cusip set but Pool Unavailable"() {
		when:
                def poolSearch = new PoolSearch()
                poolSearch.cusipIdentifier = 'CUSIP7899'
                poolSearch.poolNumber = ''
		controller.search(poolSearch)
		
		then:
                flash.error == 'Collapse.controller.search.error1'
		model.result == null
		
	}
	
	void "valid cusip with pool available"() {
		when:
                def poolSearch = new PoolSearch()
                poolSearch.cusipIdentifier = 'CUSIP1111'
                poolSearch.poolNumber = ''
		controller.search(poolSearch)
		
		then:
		model.result != null
                model.isCollapsed != true
	}
        
    
	void "valid pool number with pool available"() {
		when:
                def poolSearch = new PoolSearch()
                poolSearch.cusipIdentifier = ''
                poolSearch.poolNumber = 'POOL12345'
		controller.search(poolSearch)
		
		then:
		model.result != null
                model.isCollapsed != true
	}    
        
	void "valid pool number with pool Unavailable"() {
		when:
                def poolSearch = new PoolSearch()
                poolSearch.cusipIdentifier = ''
                poolSearch.poolNumber = 'POOL7899'
		controller.search(poolSearch)
		
                then:
                flash.error == 'Collapse.controller.search.error2'
		model.result == null
	}        
        
	void "Initiate Pool Collapse request for valid pool"() {
		when:
                params.reqPoolNum = 'POOL12345'
                params.reqCUSIP = 'CUSIP1111'
		controller.collapse()
		
                then:
                flash.message == 'Collapse.controller.collapse.success'
	}            
        
	void "Initiate Pool Collapse request for invalid pool"() {
		when:
                params.reqPoolNum = 'POOL7899'
                params.reqCUSIP = 'CUSIP7899'
		controller.collapse()
		
                then:
                flash.error == 'Collapse.controller.collapse.fail'
	}                
    
}
