package com.freddiemac.scc.security

class WireInstructionsService {
    
    def saveWireInstructions(String wiName, String abaRouting, String finInstitutionSubAcctName, String organizationName, String finInstitutionTelegraphicAbbrName) {
        WireInstructions wireInstructionsInstance = new WireInstructions()
        wireInstructionsInstance.wireInstructionsName = wiName
        wireInstructionsInstance.abaRoutingNumber = abaRouting
        wireInstructionsInstance.finInstitutionSubAcctName = finInstitutionSubAcctName
        wireInstructionsInstance.organizationName = organizationName
        wireInstructionsInstance.finInstitutionTelegraphicAbbrName = finInstitutionTelegraphicAbbrName
        wireInstructionsInstance.save()
        return wireInstructionsInstance
    }
    
    def getPreviousWireInstructions() {
        respond WireInstructions.list()
    }    

    def getWireInstructions(String id) {
        WireInstructions wireInstructionsInstance = WireInstructions.get(id)
        respond wireInstructionsInstance
    }        
    
}
