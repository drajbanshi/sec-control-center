package com.freddiemac.scc

import com.freddiemac.service.event.api.EventNotification
import com.freddiemac.service.event.model.BusinessDataType
import com.freddiemac.service.event.model.EventContainerType;
import com.freddiemac.service.event.model.Events

import grails.transaction.Transactional
import groovy.xml.XmlUtil;

@Transactional
class DispatchService {
   def searchService
	
   boolean dissolveSecurity(def evnts) {
	   EventNotification en = new EventNotification()
	   Events events = en.createEventFromXML(XmlUtil.serialize(evnts))
	   events.getEventPayload().getEventContainer()
	   EventContainerType typ 
	   typ.businessData = new BusinessDataType()
	  
	   
	   return true
   }
}
