<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<meta name="layout" content="main">
<title>
	${message(code: 'PoolDetails.page')}
</title>
</head>
<body>
	<h2>
		${message(code: 'PoolDetails.page.header.title')}
	</h2>
	<br />
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
			<g:renderErrors bean="${poolSearch}" as="list" />
		</div>
	</g:hasErrors>
	<br />

	<g:form controller="pool" action="search" class="form-inline">
		<div>
			<span
				class='value ${hasErrors(bean:poolSearch,field:'poolNumber','errors')}'>
				<label>
					${message(code: 'PoolDetails.page.input.poolnumber')} :
			</label> <input type="text" class="form-control" name="poolNumber"
				aria-label="Enter Pool ID ..."
				value="${fieldValue(bean:poolSearch,field:'poolNumber')}">
			</span> <span
				class='value ${hasErrors(bean:poolSearch,field:'cusipIdentifier','errors')}'>
				<label>
					${message(code: 'PoolDetails.page.input.securitycusipidentifier')} :
			</label> <input type="text" class="form-control" name="cusipIdentifier"
				aria-label="Enter CUSIP Identifier..."
				value="${fieldValue(bean:poolSearch,field:'cusipIdentifier')}">
			</span> <span><button class="btn btn-primary" type="submit">
					<i class="fa fa-search"></i>
					${message(code: 'PoolDetails.page.search.submit')}
				</button> </span>
		</div>
	</g:form>


	<g:if test="${result }">
		<div id="show-MBSData" class="content scaffold-show" role="main">

			<hr />
			<h3>
				${message(code: 'PoolDetails.section.pooldetails.title')}
			</h3>
			<ol class="property-list mbsdata">
				<g:each in="${result}" var="item">

					<li class="fieldcontain"><span id="${item.key}"
						class="property-label"> ${message(code:item.key + '.label')}
							:
					</span> <span class="property-value" aria-labelledby="${item.key}">
							${item.value}
					</span></li>
				</g:each>
			</ol>
		</div>
		<g:if test="${!isCollapsed}">
			<g:form controller="pool" action="collapse">
			<g:hiddenField name="poolid" value="${poolid}" />
			<g:hiddenField name="cusip" value="${cusip}" />
			<g:hiddenField name="cusipIdentifier"
				value="${poolSearch.cusipIdentifier}" />
			<g:hiddenField name="poolNumber" value="${poolSearch.poolNumber}" />
			<button class="" type="submit"
				onclick="return confirm('${message(code: 'PoolDetails.page.collapse.confirm')}');">
				<i class="fa fa-close"> </i>
				${message(code: 'PoolDetails.page.collapse.submit')}
			</button>
		</g:form>
			
		</g:if>
		
	</g:if>



</body>
</html>

