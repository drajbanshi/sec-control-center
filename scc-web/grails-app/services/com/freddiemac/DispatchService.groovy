package com.freddiemac

import com.freddiemac.service.event.api.EventNotification
import com.freddiemac.service.event.model.Events

import grails.transaction.Transactional
import groovy.xml.XmlUtil;

@Transactional
class DispatchService {
   def searchService
	
   boolean dissolvePool(String cusip) {
	   def ret = searchService.searchPool(cusip)
	   if(!ret.success) {
		   return false
	   }
	   EventNotification en = new EventNotification()
	   Events events = en.createEventFromXML(XmlUtil.serialize(ret.events))
	   en.notifyEvent(events)
	   return true
   }
}
