<%--
  Created by IntelliJ IDEA.
  User: dev
  Date: 1/29/15
  Time: 10:49 PM
--%>

<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<meta name="layout" content="main">
<title>Dissolve - Securitization Control Center</title>
</head>
<body>
	<h3>Dissolve Pool</h3>
<g:set var="itemList" value="${grailsApplication.config.com.freddiemac.security.businessdata}" />		
	
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
	<g:form controller="search" action="search" class="form-inline">
		<label>Security CUSIP Identifier :</label>
		<input type="text" class="form-control" name="cusip"
			aria-label="Enter CUSIP Identifier..." value="${params.cusip}">
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
	<g:if test="${itemList.contains(item.key)}">					  
    					<li class="fieldcontain">
    					<span id="${item.key}" class="property-label">${item.key} :</span>	
						<span class="property-value" aria-labelledby="${item.key}">
							${item.value}
					</span></li>
					</g:if>
	</g:each>
			
			</ol>
		</div>
		<g:if test="${!isDissolve}">
		<g:form controller="search" action="dissolve">
			<g:hiddenField name="cusip" value="${params.cusip}" />
			<button class="btn btn-danger" type="submit"
				onclick="return confirm('Are you sure you want to dissove this pool?');">
				<i class="fa fa-close"> </i> Dissolve
			</button>
		</g:form>
		</g:if>
	</g:if>

</body>
</html>