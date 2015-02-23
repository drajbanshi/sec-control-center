package com.freddiemac.scc.utils

import wslite.soap.SOAPResponse;
import groovy.util.slurpersupport.GPathResult;

class PropertyRetriever {

	static def getProp(String propx, GPathResult o) {
		if(!o) {
			return null
		}
		def r = o
		for(def p : propx.split("\\.")) {
			r = r[p]
			if(!r || r.isEmpty()) {
				return null
			}
		}
		return r
	}
	
	
	
}
