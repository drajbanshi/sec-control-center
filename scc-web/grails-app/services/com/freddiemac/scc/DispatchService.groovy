package com.freddiemac.scc

import com.freddiemac.service.event.api.EventNotification
import com.freddiemac.service.event.model.Events

import grails.transaction.Transactional
import groovy.xml.XmlUtil;

@Transactional
class DispatchService {
   def searchService
	
   boolean dissolveSecurity(def evnts) {
	   EventNotification en = new EventNotification()
	   Events events = en.createEventFromXML(XmlUtil.serialize(evnts))
	   en.notifyEvent(events)
	   return true
   }
}
