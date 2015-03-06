package com.freddiemac.scc

import groovy.xml.XmlUtil

import com.freddiemac.scc.entities.EventType
import com.freddiemac.service.event.api.EventNotification
import com.freddiemac.service.event.model.Events


class DispatchService {
	def xmlTemplatingService
	def eventLogService

	boolean dissolveSecurity(def evnts) {
                long start = System.currentTimeMillis();
		EventNotification en = new EventNotification()
		Events events = en.createEventFromXML(XmlUtil.serialize(evnts))
		en.notifyEvent(events)
                log.info("Time taken for dissolveSecurity() : " + (System.currentTimeMillis() - start)+ " ms")                
		return true
	}

	boolean collapsePool(String poolId, String cusip, String poolType) {
                long start = System.currentTimeMillis();
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
		}finally {
                    log.info("Time taken for collapsePool() : " + (System.currentTimeMillis() - start)+ " ms")
                }

		return true
	}
}
