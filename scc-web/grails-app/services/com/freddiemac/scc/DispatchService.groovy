package com.freddiemac.scc

import groovy.xml.XmlUtil

import com.freddiemac.scc.entities.EventType
import com.freddiemac.service.event.api.EventNotification
import com.freddiemac.service.event.model.Events


class DispatchService {
	def xmlTemplatingService
	def eventLogService

	boolean dissolveSecurity(def evnts) {
		EventNotification en = new EventNotification()
		Events events = en.createEventFromXML(XmlUtil.serialize(evnts))
		en.notifyEvent(events)
		return true
	}

	boolean collapsePool(String poolId, String cusip, String poolType) {
		if(!eventLogService.logEvent(cusip, EventType.COLLAPSE)) {
			log.error("Collapse event already initiated for " + cusip)
			return false
		}

		String eventXml = xmlTemplatingService.generateCollapseEvent(poolId, poolType)
		EventNotification en = new EventNotification()
		Events events = en.createEventFromXML(eventXml)
		log.info("dispatching collapse event")
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
