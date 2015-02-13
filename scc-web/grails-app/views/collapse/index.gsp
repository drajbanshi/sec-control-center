<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<meta name="layout" content="main">
<title>Collapse - Securitization Control Center</title>
</head>
<body>
	<h3>Collapse Pool</h3>
	
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
	<g:form controller="collapse" action="search" class="form-inline">
		<label>Security CUSIP Identifier :</label>
		<input type="text" class="form-control" name="cusip"
			aria-label="Enter CUSIP Identifier..." value="${params.cusip}">
			<label>Pool ID :</label>
		<input type="text" class="form-control" name="pool"
			aria-label="Enter Pool ID ..." value="${params.pool}">
		<button class="btn btn-primary" type="submit">
			<i class="fa fa-search"></i> Search
		</button>
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
		<g:form controller="collapse" action="collapse">
				<g:hiddenField name="cusip" value="${params.cusip}" />
				<button class="btn btn-danger" type="submit"
					onclick="return confirm('Are you sure you want to collapse this pool?');">
					<i class="fa fa-close"> </i> Collapse
				</button>
			</g:form>
	
</g:if>
                        
                        

</body>
</html>