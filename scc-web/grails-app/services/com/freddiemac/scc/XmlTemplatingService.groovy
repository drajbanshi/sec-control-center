package com.freddiemac.scc

import grails.gsp.PageRenderer

import java.text.SimpleDateFormat

class XmlTemplatingService {

	PageRenderer groovyPageRenderer

	String generateCollapseEvent(String poolIdentifer) {
		Calendar c = Calendar.getInstance()
		c.setTimeZone(TimeZone.getTimeZone("UTC"))
		String dtime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(c.getTime())
		return groovyPageRenderer.render([template: "/xmltemplates/collapse",model : [poolIdentifer: poolIdentifer,eventTimeStamp:dtime , messageTimeStamp: dtime, messageIdentifier: UUID.randomUUID().toString(), eventIdentifier: UUID.randomUUID().toString()]])
	}
}
