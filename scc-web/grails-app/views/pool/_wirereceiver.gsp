<table class="table table-striped table-hover table-compressed" style="width:80%">
		<tr>
			<td class="table-label property-label"> 
				${message(code:'FinancialInstitution.ABARoutingAndTransitIdentifier.label')}:		 
			</td>
			<td class="table-data" aria-labelledby="${wireinstructions.abaRoutingNumber}"> 
				${wireinstructions.abaRoutingNumber}
                                <g:hiddenField name="Receiver.FinancialInstitution.ABARoutingAndTransitIdentifier" value="${wireinstructions.abaRoutingNumber}" />                    
			</td>
		</tr>                
		<tr>
			<td class="table-label property-label"> 
				${message(code:'FinancialInstitutionAccount.FinancialInstitutionAccountSubaccountName.label')}:		 
			</td>
			<td class="table-data" aria-labelledby="${wireinstructions.finInstitutionSubAcctName}"> 
				${wireinstructions.finInstitutionSubAcctName}
                                <g:hiddenField name="Receiver.FinancialInstitutionAccount.FinancialInstitutionAccountSubaccountName" value="${wireinstructions.finInstitutionSubAcctName}" />
			</td>
		</tr>                
		<tr>
			<td class="table-label property-label" >
				${message(code:'Organization.OrganizationName.label')}:		 
			</td>
			<td class="table-data" aria-labelledby="${wireinstructions.organizationName}"> 
				${wireinstructions.organizationName}
                                <g:hiddenField name="Receiver.Organization.OrganizationName" value="${wireinstructions.organizationName}" />
			</td>
		</tr>                
		<tr>
			<td class="table-label property-label" >
				${message(code:'FinancialInstitution.FinancialInstitutionTelegraphicAbbreviationName.label')}:		 
			</td>
			<td class="table-data" aria-labelledby="${wireinstructions.finInstitutionTelegraphicAbbrName}"> 
				${wireinstructions.finInstitutionTelegraphicAbbrName}
                                <g:hiddenField name="Receiver.FinancialInstitution.FinancialInstitutionTelegraphicAbbreviationName" value="${wireinstructions.finInstitutionTelegraphicAbbrName}" />
			</td>
		</tr>                
</table>
