package com.freddiemac.scc

import com.freddiemac.scc.entities.EventProcessLog;
import com.freddiemac.scc.entities.EventType;
import com.freddiemac.scc.entities.Status;
import com.freddiemac.service.event.api.EventNotification
import com.freddiemac.service.event.model.BusinessDataType
import com.freddiemac.service.event.model.EventContainerType;
import com.freddiemac.service.event.model.Events

import grails.transaction.Transactional
import groovy.xml.XmlUtil;


class DispatchService {
	def xmlTemplatingService

	boolean dissolveSecurity(def evnts) {
		EventNotification en = new EventNotification()
		Events events = en.createEventFromXML(XmlUtil.serialize(evnts))
		en.notifyEvent(events)
		return true
	}

	@Transactional
	boolean collapsePool(String poolId, String cusip) {
		String eventXml = xmlTemplatingService.genearteCollapseEvent(poolId)
		EventNotification en = new EventNotification()
		Events events = en.createEventFromXML(eventXml)

		def criteria = EventProcessLog.createCriteria()
		def eventLogs =  criteria.list { 
			eq("eventType" , EventType.COLLAPSE)
			eq("cusip", cusip)
			ne("status", Status.CANCELLED)
		}

		if(eventLogs && eventLogs.size() > 0) {
			return false
		}

		def eventLog = new EventProcessLog(cusip: cusip, eventType: EventType.COLLAPSE, status: Status.INITIALIZED).save()
		log.info("dipatching collapse event")
		try {
			en.notifyEvent(events)
		} catch (Exception ex) {
			log.error(ex)
			eventLog.status = Status.CANCELLED
			eventLog.message = ex.getMessage()
			eventLog.save()

			return false
		}

		return true
	}
}
