package com.freddiemac.scc


import grails.test.mixin.TestMixin;
import grails.test.mixin.gorm.Domain;
import grails.test.mixin.hibernate.HibernateTestMixin;
import spock.lang.Specification

import com.freddiemac.scc.entities.EventProcessLog
import com.freddiemac.scc.entities.EventType
import com.freddiemac.scc.entities.Status

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(EventLogService)
@TestMixin(HibernateTestMixin)
@Domain(EventProcessLog)
class EventLogServiceSpec extends Specification {

    def setup() {
		service.logEvent("123456789", EventType.COLLAPSE)
		service.cancelEvent("123456789", EventType.COLLAPSE)
		
		service.logEvent("987654321", EventType.COLLAPSE)
		new EventProcessLog(cusip: "987654321", eventType: EventType.COLLAPSE, status: Status.DONE).save() 
    }

   

    void "event is already sent and is in processing state"() {
		expect:
		service.isEventProcessedForCusip("123456789") == false
		service.isEventProcessedForCusip("987654321") == true
		service.isEventProcessedForCusip("dfadfafafafasd") == false
		
    }
	
	void "relog event for cancelled event"() {
		when:
		def eventLog = service.logEvent("2222222222", EventType.COLLAPSE)
		then:
		assert eventLog != null
	}
	
	
	void "don't allow logging for completed event"() {
		when:
		def eventLog = service.logEvent("987654321", EventType.COLLAPSE)
		then:
		assert eventLog == null
	}
	
}
