package com.freddiemac.scc.model
import grails.validation.Validateable

@Validateable
class PoolSearch {
	
String cusipIdentifier
String poolNumber
static constraints = {
    cusipIdentifier blank:true, nullable: true
    poolNumber blank:true, nullable: true
    cusipIdentifier validator: { val, obj ->
            if (!val && !obj.poolNumber) return 'atleastOneRequired'
            /*else {
                if (val)
            }*/
        
        }
    }
}

