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
            if (!val && !obj.poolNumber) return 'PoolSearch.searchCriteria.atleastonerequired'
            if (val && obj.poolNumber) return 'PoolSearch.searchCriteria.bothentered'
            if (val!=null && val.length() > 10) return 'PoolSearch.searchCriteria.cusipIdentifier.size'
            if (obj.poolNumber!=null && obj.poolNumber.length() > 10) return 'PoolSearch.searchCriteria.poolNumber.size'
            //if (val!=null && val=`) return 'PoolSearch.searchCriteria.cusipIdentifier.size'
        }
    }
}

