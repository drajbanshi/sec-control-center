<table class="table table-striped table-hover table-compressed" style="width:80%">
	<%-- <g:each in="${items}" var="item">
		<tr>
			<td class="table-label" id="${item.key}">
				${message(code:item.key + '.label')}:		 
			</td>
			<td class="table-value" aria-labelledby="${item.key}"> 
				${item.value}
			</td>
		</tr>
	</g:each> --%>
		<tr>
			<td class="table-label property-label"> 
				${message(code:'FinancialInstitution.ABARoutingAndTransitIdentifier.label')}:		 
			</td>
			<td class="table-data" aria-labelledby="${wireinstructions.abaRoutingNumber}"> 
				${wireinstructions.abaRoutingNumber}
			</td>
		</tr>                
		<tr>
			<td class="table-label property-label"> 
				${message(code:'FinancialInstitutionAccount.FinancialInstitutionAccountSubaccountName.label')}:		 
			</td>
			<td class="table-data" aria-labelledby="${wireinstructions.finInstitutionSubAcctName}"> 
				${wireinstructions.finInstitutionSubAcctName}
			</td>
		</tr>                
		<tr>
			<td class="table-label property-label" >
				${message(code:'Organization.OrganizationName.label')}:		 
			</td>
			<td class="table-data" aria-labelledby="${wireinstructions.organizationName}"> 
				${wireinstructions.organizationName}
			</td>
		</tr>                
		<tr>
			<td class="table-label property-label" >
				${message(code:'FinancialInstitution.FinancialInstitutionTelegraphicAbbreviationName.label')}:		 
			</td>
			<td class="table-data" aria-labelledby="${wireinstructions.finInstitutionTelegraphicAbbrName}"> 
				${wireinstructions.finInstitutionTelegraphicAbbrName}
			</td>
		</tr>                
</table>
