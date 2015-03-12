
import com.freddiemac.scc.security.WireInstructions

class BootStrap {

    def init = { servletContext ->

        new WireInstructions(wireInstructionsName: "Freddie Mac", abaRoutingNumber: "10202010", finInstitutionSubAcctName: "Good N Plenty", organizationName: "Freddie Mac", finInstitutionTelegraphicAbbrName: "FMAC").save()
        new WireInstructions(wireInstructionsName: "Last National Bank and Trust", abaRoutingNumber: "10103889", finInstitutionSubAcctName: "Good N Plenty", organizationName: "Last National Bank and Trust", finInstitutionTelegraphicAbbrName: "LNBAT").save()
    }
    def destroy = {
    }
}
