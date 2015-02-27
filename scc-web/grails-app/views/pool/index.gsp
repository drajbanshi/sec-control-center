<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<meta name="layout" content="main">
<script type="text/javascript" src="/scc-web/assets/poolsearch.js"></script>
<title>
	${message(code: 'PoolDetails.page')}
</title>
</head>
<body>

    <g:if test="${result}">
	    <g:render template="select_fields_dialog" />
    </g:if>

<div class="container-fluid">
	<div class="row">
		<div class="col-xs-4">
			<h2>
				${message(code: 'PoolDetails.page.header.title')}
				<g:if test="${poolType}"> - ${poolType}
				</g:if>
			</h2>
		</div>
		<div class="col-xs-8 text-right">
	 
		   <g:form controller="pool" action="search" class="form-inline">
			<div>
				<label> ${message(code: 'PoolDetails.page.input.poolnumber')}:
				</label> <span
					class='value ${hasErrors(bean:poolSearch,field:'poolNumber','errors')}'>
					<input type="text" id="poolNumber" maxlength="10" size="10"
					class="form-control" name="poolNumber"
					aria-label="Enter Pool ID ..."
					value="${fieldValue(bean:poolSearch,field:'poolNumber')}">
				</span> 
				<label> ${message(code: 'PoolDetails.page.input.securitycusipidentifier')}:</label> 
				<span class='value ${hasErrors(bean:poolSearch,field:'cusipIdentifier','errors')}'>
					<input type="text" id="cusipIdentifier" maxlength="10" size="10"
					class="form-control" name="cusipIdentifier"
					aria-label="Enter CUSIP Identifier..."
					value="${fieldValue(bean:poolSearch,field:'cusipIdentifier')}">
				</span> <span><button class="btn btn-primary" id="searchBtn"
						type="submit">
						<i class="fa fa-search"></i>
						${message(code: 'PoolDetails.page.search.submit')}
					</button> </span>
				<g:hiddenField name="poolError" value="${poolErrorField}" />
	
				<g:if test="${result}">
					<br><button type="button" class="btn btn-xs btn-success" style="margin-top:4px" data-toggle="modal" data-target="#extraFieldsModal">
						<i class="fa fa-plus"> </i> Show More Fields
					</button>
				</g:if>
			</div>
		</g:form>
	</div>
</div>

<div class="container"> 
	 
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
		<div class="alert alert-danger" role="status">
			<g:renderErrors bean="${poolSearch}" />
		</div>
	</g:hasErrors>
	<br />

	


	<g:if test="${result }">

		<div id="show-MBSData" class="content scaffold-show" role="main">
			<g:render template="resultfields" model="[items: result]" />
			<div id="xfieldsContainer">
			  <g:render template="resultfields" model="[items: extras]" />
			</div>
		</div>

		<g:if test="${!isCollapsed}">
			<g:form controller="pool" action="collapse">
				<g:hiddenField name="poolid" value="${poolid}" />
				<g:hiddenField name="cusip" value="${cusip}" />
				<g:hiddenField name="cusipIdentifier"
					value="${poolSearch.cusipIdentifier}" />
				<g:hiddenField name="poolNumber" value="${poolSearch.poolNumber}" />
				<g:hiddenField name="poolType" value="${poolType}" />
				<button class="btn btn-danger" type="submit"
					onclick="return confirm('${message(code: 'PoolDetails.page.collapse.confirm')}');">
					<i class="fa fa-close"> </i>
					${message(code: 'PoolDetails.page.collapse.submit')}
				</button>
			</g:form>
		</g:if>

	</g:if>

<g:javascript>
  function extraFieldsAdded() {
      $("#extraFieldsModal").modal("hide");
  }
</g:javascript>

</body>
</html>

