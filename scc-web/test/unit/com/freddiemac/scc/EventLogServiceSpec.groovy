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
		new EventProcessLog(cusip: "123456789", eventType: EventType.COLLAPSE, status: Status.INITIALIZED).save() 
		new EventProcessLog(cusip: "123456789", eventType: EventType.COLLAPSE, status: Status.CANCELLED).save() 

		new EventProcessLog(cusip: "123456789".reverse(), eventType: EventType.COLLAPSE, status: Status.INITIALIZED).save() 
		new EventProcessLog(cusip: "123456789".reverse(), eventType: EventType.COLLAPSE, status: Status.DONE).save() 
    }

   

    void "event is already sent and is in processing state"() {
		expect:
		service.isEventProcessedForCusip("123456789") == false
		service.isEventProcessedForCusip("123456789".reverse()) == true
		service.isEventProcessedForCusip("dfadfafafafasd") == false
		
    }
	
	void "relog event for cancelled event"() {
		when:
		def eventLog = service.logEvent("123456789", EventType.COLLAPSE)
		then:
		assert eventLog != null
	}
	
	
	void "don't allow logging for completed event"() {
		when:
		def eventLog = service.logEvent("123456789".reverse(), EventType.COLLAPSE)
		then:
		assert eventLog == null
	}
	
}
