package com.freddiemac

class PropertyRetriever {
	
    static def getProp(String propx, def o) {
		def r = o
		for(def p : propx.split("\\.")) {
			r = r."${p}"
		}
		return r
	}

}
