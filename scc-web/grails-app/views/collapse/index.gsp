<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<meta name="layout" content="main">
<title>Pool Search - Securitization Control Center</title>
</head>
<body>
	<h3>Pool Search</h3>
	
	<g:if test="${flash.message}">
		<div class="alert alert-success" role="status">
			${flash.message}
		</div>
	</g:if>
	<g:if test="${flash.error}">
		<div class="alert alert-danger" role="status">
			${flash.error}
		</div>
	</g:if>
        <g:hasErrors bean="${poolSearch}">
            <div class="error">
          <g:renderErrors bean="${poolSearch}" as="list"/> 
          </div>
        </g:hasErrors>      
        
        
	<g:form controller="collapse" action="search" class="form-inline">
            <div>
            <span class='value ${hasErrors(bean:poolSearch,field:'cusipIdentifier','errors')}'>
		<label>Security CUSIP Identifier :</label>
		<input type="text" class="form-control" name="cusipIdentifier"
			aria-label="Enter CUSIP Identifier..." value="${params.cusipIdentifier}">
            </span>
            <span class='value ${hasErrors(bean:poolSearch,field:'poolNumber','errors')}'>
			<label>Pool ID :</label>
		<input type="text" class="form-control" name="poolNumber"
			aria-label="Enter Pool ID ..." value="${params.poolNumber}">
                        <span>
                            <span><button class="btn btn-primary" type="submit">
			<i class="fa fa-search"></i> Search
		</button></span>
               </div> 
	</g:form>
	
	
        <g:if test="${result }">
		<div id="show-MBSData" class="content scaffold-show" role="main">

			<hr />
			<h3>Pool Details..</h3>
			<ol class="property-list mbsdata">
				<g:each in="${result}" var="item">
					
						<li class="fieldcontain"><span id="${item.key}"
							class="property-label">
								${message(code:item.key + '.label')} :
						</span> <span class="property-value" aria-labelledby="${item.key}">
								${item.value}
						</span></li>
				</g:each>				
			</ol>
		</div>
                <g:if test="${!isCollapsed}">
                    <g:form controller="collapse" action="collapse">
                            <g:hiddenField name="poolNumber" value="${params.poolNumber}" />
                            <g:hiddenField name="cusipIdentifier" value="${params.cusipIdentifer}" />
                            <g:hiddenField name="reqPoolNum" value="${poolid}" />
                            <g:hiddenField name="reqCUSIP" value="${cusip}" />
                            <button class="btn btn-danger" type="submit"
                                    onclick="return confirm('Are you sure you want to collapse this pool?');">
                                    <i class="fa fa-close"> </i> Collapse
                            </button>
                    </g:form>
                </g:if>
	
</g:if>
                        
                        

</body>
</html>