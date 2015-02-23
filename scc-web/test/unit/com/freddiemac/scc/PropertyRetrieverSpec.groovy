
package com.freddiemac.scc

import groovy.util.slurpersupport.GPathResult;

import com.freddiemac.scc.utils.PropertyRetriever;

import spock.lang.Specification;

class PropertyRetrieverSpec extends Specification {

	void "test valid values"() {
		when:
		GPathResult result = new XmlSlurper().parseText("<test><abcd><e>Hello</e></abcd></test>")
		then:
		PropertyRetriever.getProp("abc", null) == null
		PropertyRetriever.getProp("abc", result) == null
		PropertyRetriever.getProp("abcd.e", result) == "Hello"
		
	}
}
