
		 
			
				<label>
					${message(code: 'PoolDetails.page.input.poolnumber')} 
				</label> 
                        <span class='value ${hasErrors(bean:poolSearch,field:'poolNumber','errors')}'>
                        <input type="text" id="poolNumber" maxlength="8" size="8" class="form-control" name="poolNumber"
				aria-label="Enter Pool Number"
				value="${fieldValue(bean:poolSearch,field:'poolNumber')}">
						</span> &nbsp; &nbsp;                  
				<label>
					${message(code: 'PoolDetails.page.input.securitycusipidentifier')} 
				</label> 
                        <span class='value ${hasErrors(bean:poolSearch,field:'cusipIdentifier','errors')}'>
                        <input type="text" id="cusipIdentifier" maxlength="9" size="9" class="form-control" name="cusipIdentifier"
				aria-label="Enter CUSIP ID"
				value="${fieldValue(bean:poolSearch,field:'cusipIdentifier')}">
                        </span> &nbsp; &nbsp; <span><button class="btn btn-primary" id="searchBtn" type="submit" >
					<i class="fa fa-search"></i>
					${message(code: 'PoolDetails.page.search.submit')}
				</button></span>
                <g:hiddenField name="poolError" value="${poolErrorField}" /> 
