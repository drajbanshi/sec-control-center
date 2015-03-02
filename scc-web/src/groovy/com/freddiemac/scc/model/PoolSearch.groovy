package com.freddiemac.scc.model
import grails.validation.Validateable

@Validateable
class PoolSearch {
	
String cusipIdentifier
String poolNumber

static constraints = {
    cusipIdentifier blank:true, nullable: true
    poolNumber blank:true, nullable: true
    def expression = '^[A-Za-z0-9\\d]*$' 
    cusipIdentifier validator: { val ->
            if (val!=null && val.length() > 9) return 'PoolSearch.searchCriteria.cusipIdentifier.size'
            if(val!=null && !val.matches(expression)) return 'PoolSearch.searchCriteria.cusipIdentifier.specialchar'            

        }
    poolNumber validator: { val, obj  ->
            if (!val && !obj.cusipIdentifier) return 'PoolSearch.searchCriteria.atleastonerequired'
            if (val && obj.cusipIdentifier) return 'PoolSearch.searchCriteria.bothentered'        
            if (val!=null && val.length() > 8) return 'PoolSearch.searchCriteria.poolNumber.size'
            if(val!=null && !val.matches(expression)) return 'PoolSearch.searchCriteria.poolNumber.specialchar'
        }  
        
        
    }
}

