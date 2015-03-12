


package com.freddiemac.scc

import spock.lang.Specification
import spock.lang.Unroll

import com.freddiemac.scc.entities.EventProcessLog
import com.freddiemac.scc.entities.EventType
import com.freddiemac.scc.entities.Status
import com.freddiemac.scc.model.PoolSearch
import com.ibm.icu.text.SimpleDateFormat;

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(PoolController)
@grails.test.mixin.Mock([EventProcessLog])
class PoolControllerSpec extends Specification {

	static doWithConfig(c) {
		c.com.freddiemac.searchpool.result.elements = ['test1', 'test2', 'test3']
		c.com.freddiemac.searchpool.result.path = 'mytest'
		c.com.freddiemac.searchpool.result.securityissuedate = 'test1'
		c.com.freddiemac.searchpool.result.poolid = 'test2'
	}

	def "grailsApplication is not null"() {
		expect:
		grailsApplication != null
	}



	def setup() {
		def searchService = Mock(SearchService)
		XmlSlurper xmlSlurper = new XmlSlurper()
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd")
		Calendar c = Calendar.getInstance()
		c.add(Calendar.DAY_OF_MONTH, 5)
		String dt = formatter.format(c.getTime())
		searchService.searchPool("CUSIP2222",_) >> ['success': true, 'result':  xmlSlurper.parseText("<mytest><test1>2015-01-02</test1><test2/>test3/></mytest>")]
		searchService.searchPool("","POOL2222") >> ['success': true, 'result':  xmlSlurper.parseText("<mytest><test1>${dt}</test1><test2/>test3/></mytest>")]
		searchService.searchPool("CUSIP1234","") >> ['success': true, 'result':  xmlSlurper.parseText("<mytest><test1></test1><test2/>test3/></mytest>")]
		searchService.searchPool(_,_) >> ['success': false, 'result':  xmlSlurper.parseText("<ErrorEvnelope><ErrorNo/><ErrorMessage/></ErrorEvnelope>")]
		controller.searchService = searchService

		def dispatchService = Mock(DispatchService)
		dispatchService.collapsePool("PPPPPPP","CUSIP2222",_) >> false
		dispatchService.collapsePool("POOL2222","CCCCCC",_) >> true
		
		controller.dispatchService = dispatchService

		def eventLogService = Mock(EventLogService)
		eventLogService.logEvent("CUSIP2222", _) >> new EventProcessLog(cusip: "CUSIP2222", eventType: EventType.COLLAPSE,status: Status.INITIALIZED)
		eventLogService.isEventProcessedForCusip("CUSIP2222") >> false
		eventLogService.logEvent("CUSIP1234", _) >> new EventProcessLog(cusip: "CUSIP1234", eventType: EventType.COLLAPSE,status: Status.INITIALIZED)
		eventLogService.isEventProcessedForCusip("CUSIP1234") >> true
		controller.eventLogService = eventLogService
	}

	def "index doesnt show errors"() {
		when:
		controller.index()

		then:
		model.searchPool == null
		view == "/pool/collapsesearch"
	}

	@Unroll
	def "search validation works with cusip=#cusip, poolid=#poolid"(String cusip, String poolid) {
		given:
		PoolSearch poolSearch = new PoolSearch(cusipIdentifier: cusip, poolNumber: poolid)
		poolSearch.validate()

		when:
		controller.search(poolSearch)

		then:
		model.poolSearch != null
		model.poolSearch.hasErrors() == true
		view == "/pool/collapsesearch"

		where:
		cusip | poolid
		""    | ""
		"SSSSSSSSSSS" | ""
		"" | "111111111111"
		"SSSSSSSSSS"| "SSSSSSSSSS"
	}

	@Unroll
	def "search with valid params and pool available works (cusip=#cusip, poolid=#poolid)"(String cusip, String poolid, boolean collapsed) {
		given:
		PoolSearch poolSearch = new PoolSearch(cusipIdentifier: cusip, poolNumber: poolid)
		poolSearch.validate()

		when:
		controller.search(poolSearch)
		then:
		view == "/pool/collapsesearch"
		model.result != null
		model.isCollapsed == collapsed
		model.result.size == 3
		flash.error ==  null


		where:
		cusip | poolid || collapsed
		"CUSIP2222"    | "" || true
		"" | "POOL2222" || false
		"CUSIP1234" | "" || true
	}

	@Unroll
	void "valid cusip set but Pool Unavailable(cusip=#cusip, poolid=#poolid, err = #err)"(String cusip, String poolid, String err) {
		when:
		controller.search(new PoolSearch(cusipIdentifier: cusip, poolNumber: poolid))

		then:
		flash.error == err
		model.result == null

		where:
		cusip | poolid || err
		"ssss"    | "" ||  'Collapse.controller.search.error1'
		"" | "tttt" ||  'Collapse.controller.search.error2'
	}
	
	@Unroll
	void "collapse pool works with validation (cusip=#cusip, poolid=#poolid, err = #err)"(String cusip, String poolid, String pooltype, String err) {
		given:
		params.cusip = cusip
		params.poolid = poolid
		
		when:
		controller.collapse()
		
		then:
		flash.error == err
		
		where:
		cusip | poolid | pooltype || err
		""    | "" | "" || 'Collapse.controller.collapse.fail'
		"CCCCCC" | "POOL2222" | "CASH" ||  'Collapse.controller.collapse.fail'
		"CUSIP2222" | "PPPPPPP" | "CASH" ||  'Collapse.controller.collapse.fail'
                "CUSIP2222" | "" | "GUARANTOR" ||  'Collapse.controller.collapse.fail'
                "CUSIP2222" | "" | "GIANT" ||  'Collapse.controller.collapse.fail'
	}
}
