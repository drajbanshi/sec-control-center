package com.freddiemac.scc

import grails.gsp.PageRenderer

import java.text.SimpleDateFormat

class XmlTemplatingService {

	PageRenderer groovyPageRenderer

	String generateCollapseEvent(String poolIdentifer, String poolType) {
		String dtime = getFormattedCurrentTime()
		return groovyPageRenderer.render([template: "/xmltemplates/collapse",model : [poolIdentifer: poolIdentifer, poolType: poolType, eventTimeStamp:dtime , messageTimeStamp: dtime, messageIdentifier: UUID.randomUUID().toString(), eventIdentifier: UUID.randomUUID().toString()]])
	}
	
	
	private def getFormattedCurrentTime() {
		Calendar c = Calendar.getInstance()
		c.setTimeZone(TimeZone.getTimeZone("UTC"))
		return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(c.getTime())
	}
	
	String generateDissolveEent(Map<String, String> model) {
		String dtime = getFormattedCurrentTime()
		model.putAll([ eventTimeStamp:dtime , messageTimeStamp: dtime, messageIdentifier: UUID.randomUUID().toString(), eventIdentifier: UUID.randomUUID().toString()])
		return groovyPageRenderer.render([template: "/xmltemplates/dissolve",model :model])
	}
}
