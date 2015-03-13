<g:if test="${wireinstructions}">
<table class="table table-striped table-hover table-compressed" style="width:80%">
		<tr>
			<td class="table-label property-label"> 
				${message(code:'FinancialInstitution.ABARoutingAndTransitIdentifier.label')}:		 
                                <g:hiddenField name="Receiver.FinancialInstitution.ABARoutingAndTransitIdentifier" value="${wireinstructions.abaRoutingNumber}" />                                                    
			</td>
			<td id="updRecAba" class="table-data" aria-labelledby="${wireinstructions.abaRoutingNumber}"> 
				${wireinstructions.abaRoutingNumber}
			</td>
		</tr>                
		<tr>
			<td class="table-label property-label"> 
				${message(code:'FinancialInstitutionAccount.FinancialInstitutionAccountSubaccountName.label')}:		 
                                <g:hiddenField name="Receiver.FinancialInstitutionAccount.FinancialInstitutionAccountSubaccountName" value="${wireinstructions.finInstitutionSubAcctName}" />                                
			</td>
			<td id="updRecFinSubAcct" class="table-data" aria-labelledby="${wireinstructions.finInstitutionSubAcctName}"> 
				${wireinstructions.finInstitutionSubAcctName}
			</td>
		</tr>                
		<tr>
			<td class="table-label property-label" >
				${message(code:'Organization.OrganizationName.label')}:		 
                                <g:hiddenField name="Receiver.Organization.OrganizationName" value="${wireinstructions.organizationName}" />                                
			</td>
			<td id="updRecOrgName" class="table-data" aria-labelledby="${wireinstructions.organizationName}"> 
				${wireinstructions.organizationName}
			</td>
		</tr>                
		<tr>
			<td class="table-label property-label" >
				${message(code:'FinancialInstitution.FinancialInstitutionTelegraphicAbbreviationName.label')}:		 
                                <g:hiddenField name="Receiver.FinancialInstitution.FinancialInstitutionTelegraphicAbbreviationName" value="${wireinstructions.finInstitutionTelegraphicAbbrName}" />                                
			</td>
			<td id="updRecFinTelAbbr" class="table-data" aria-labelledby="${wireinstructions.finInstitutionTelegraphicAbbrName}"> 
				${wireinstructions.finInstitutionTelegraphicAbbrName}
			</td>
		</tr>                
</table>
</g:if>