package com.freddiemac.scc

import com.freddiemac.scc.entities.EventProcessLog;
import com.freddiemac.scc.entities.EventType;
import com.freddiemac.scc.entities.Status;

import grails.transaction.Transactional

@Transactional
class EventLogService {

	def logEvent(String cusip, EventType eventType) {
		if(isEventProcessedForCusip(cusip)) {
			return null
		}
		return new EventProcessLog(cusip: cusip, status: Status.INITIALIZED, eventType: eventType).save()
	}


	def isEventProcessedForCusip(String cusip) {
		def criteria = EventProcessLog.createCriteria()
		EventProcessLog eventLog = criteria.get {
			eq("cusip", cusip)
			maxResults(1)
			order("dateCreated", "desc")
		}
		return eventLog && !eventLog.status.equals(Status.CANCELLED)
	}

	def cancelEvent(String cusip,EventType eventType) {
		return new EventProcessLog(cusip: cusip, status: Status.CANCELLED, eventType: eventType).save()
	}
}
