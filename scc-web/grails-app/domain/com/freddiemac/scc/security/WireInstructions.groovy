package com.freddiemac.scc.security

class WireInstructions {
    
    String wireInstructionsName
    String abaRoutingNumber
    String finInstitutionSubAcctName
    String organizationName
    String finInstitutionTelegraphicAbbrName

    static constraints = {
        wireInstructionsName(blank:true, nullable: true)
        abaRoutingNumber(blank:true, nullable: true)
        finInstitutionSubAcctName(blank:true, nullable: true)
        organizationName(blank:true, nullable: true)
        finInstitutionTelegraphicAbbrName(blank:true, nullable: true)
    }
}
