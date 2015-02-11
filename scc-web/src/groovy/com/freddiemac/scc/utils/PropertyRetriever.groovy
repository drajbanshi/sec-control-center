package com.freddiemac.scc.utils

class PropertyRetriever {

	static def getProp(String propx, def o) {
		if(!o) {
			return null
		}
		def r = o
		for(def p : propx.split("\\.")) {
			r = r[p]
			if(!r) {
				return null
			}
		}
		return r
	}
	
}
