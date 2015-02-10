package com.freddiemac.scc.utils

class PropertyRetriever {
	
    static def getProp(String propx, def o) {
		if(!o) {
			return null
		}
		def r = o
		for(def p : propx.split("\\.")) {
			if(r.hasProperty("${p}")) {
				r = r."${p}"
			} else {
			    return null
			}
			
		}
		return r
	}

}
