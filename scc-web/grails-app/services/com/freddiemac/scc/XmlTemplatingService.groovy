package com.freddiemac.scc

import java.text.DateFormat;
import java.text.SimpleDateFormat

import javax.xml.datatype.DatatypeFactory;

import org.codehaus.groovy.grails.web.pages.GroovyPagesTemplateEngine;

import com.sun.xml.internal.ws.api.pipe.Engine;

import grails.gsp.PageRenderer;
import grails.transaction.Transactional
import groovy.text.XmlTemplateEngine;

class XmlTemplatingService {

	PageRenderer groovyPageRenderer

	String generateCollapseEvent(String poolIdentifer, String poolType) {
		Calendar c = Calendar.getInstance()
		c.setTimeZone(TimeZone.getTimeZone("UTC"))
		String dtime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(c.getTime())
		return groovyPageRenderer.render([template: "/xmltemplates/collapse",model : [poolIdentifer: poolIdentifer, poolType: poolType, eventTimeStamp:dtime , messageTimeStamp: dtime, messageIdentifier: UUID.randomUUID().toString(), eventIdentifier: UUID.randomUUID().toString()]])
	}
}
