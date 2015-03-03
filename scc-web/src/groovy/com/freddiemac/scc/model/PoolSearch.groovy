package com.freddiemac.scc.model
import grails.validation.Validateable

@Validateable
class PoolSearch {
	
String cusipIdentifier
String poolNumber
String[] xfield

static constraints = {
    cusipIdentifier blank:true, nullable: true
	xfield  nullable: true
    poolNumber blank:true, nullable: true
    def expression = '^[A-Za-z0-9\\d]*$' 
    cusipIdentifier validator: { val, obj ->
            if (val!=null && val.length() > 9) return 'PoolSearch.searchCriteria.cusipIdentifier.size'
            if(val!=null && !val.matches(expression) && !obj.poolNumber) return 'PoolSearch.searchCriteria.cusipIdentifier.specialchar'      

        }
    poolNumber validator: { val, obj  ->
            if (!val && !obj.cusipIdentifier) return 'PoolSearch.searchCriteria.atleastonerequired'
            if (val && obj.cusipIdentifier) return 'PoolSearch.searchCriteria.bothentered'        
            if (val!=null && val.length() > 8) return 'PoolSearch.searchCriteria.poolNumber.size'
            if(val!=null && !val.matches(expression)) return 'PoolSearch.searchCriteria.poolNumber.specialchar'
        }  
        
        
    }
}

