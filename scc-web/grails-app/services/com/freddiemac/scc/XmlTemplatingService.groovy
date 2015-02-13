package com.freddiemac.scc

import org.codehaus.groovy.grails.web.pages.GroovyPagesTemplateEngine;

import com.sun.xml.internal.ws.api.pipe.Engine;

import grails.gsp.PageRenderer;
import grails.transaction.Transactional
import groovy.text.XmlTemplateEngine;

class XmlTemplatingService {

	PageRenderer groovyPageRenderer

	def genearteCollapseEvent(String poolIdentifer) {
		Calendar c = Calendar.getInstance()
		c.setTimeZone(TimeZone.getTimeZone("UTC"))
		return groovyPageRenderer.render([template: "/xmltemplates/collapse_event",model : [poolIdentifer: poolIdentifer,eventTimeStamp: c.toString() , messageTimeStamp: c.toString(), messageIdentifer: UUID.randomUUID().toString()]])
	}
}
