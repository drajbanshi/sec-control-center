package com.freddiemac.scc.security

class WireInstructions {
    
    String abaRoutingNumber
    String finInstitutionSubAcctName
    String organizationName
    String finInstitutionTelegraphicAbbrName
    String finInstitutionAccountIdentifier

    static constraints = {
        abaRoutingNumber(blank:true, nullable: false)
        finInstitutionSubAcctName(blank:true, nullable: false)
        organizationName(blank:true, nullable: false)
        finInstitutionTelegraphicAbbrName(blank:true, nullable: false)
        finInstitutionAccountIdentifier(blank:true, nullable: false)
    }
}
