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
	def eventLogService

	boolean dissolveSecurity(def evnts) {
		EventNotification en = new EventNotification()
		Events events = en.createEventFromXML(XmlUtil.serialize(evnts))
		en.notifyEvent(events)
		return true
	}

	boolean collapsePool(String poolId, String cusip) {
		def b= eventLogService.logEvent(cusip, EventType.COLLAPSE)
		if(!b) {
			return false
		}
		
		String eventXml = xmlTemplatingService.genearteCollapseEvent(poolId)
		EventNotification en = new EventNotification()
		Events events = en.createEventFromXML(eventXml)

		log.info("dipatching collapse event")
		try {
			en.notifyEvent(events)
		} catch (Exception ex) {
			log.error(ex)
			eventLogService.cancelEvent(cusip, EventType.COLLAPSE)
			return false
		}

		return true
	}
}
