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

<div class="container-fluid">
  <ul class="nav nav-tabs">
	 <li role="presentation"><a href="#">Dashboard</a></li>
	 <li role="presentation" class="active"><a href="#">Pool Detail</a></li>
	 <!--li role="presentation"><a href="#">Dissolve</a></li-->
  </ul>
  <div class="tabBody">
   <div class="row">
	<div class="col-xs-4">
		<h2>${message(code: 'PoolDetails.page.header.title')}</h2>
    </div>
    <div class="col-xs-8 text-right">
      <g:form controller="pool" action="search" class="form-inline">
		<div>
			
				<label>
					${message(code: 'PoolDetails.page.input.poolnumber')}:
				</label> 
                        <span class='value ${hasErrors(bean:poolSearch,field:'poolNumber','errors')}'>
                        <input type="text" id="poolNumber" maxlength="8" size="8" class="form-control" name="poolNumber"
				aria-label="Enter Pool ID"
				value="${fieldValue(bean:poolSearch,field:'poolNumber')}">
						</span>  &nbsp;                                
				<label>
					${message(code: 'PoolDetails.page.input.securitycusipidentifier')}:
				</label> 
                        <span class='value ${hasErrors(bean:poolSearch,field:'cusipIdentifier','errors')}'>
                        <input type="text" id="cusipIdentifier" maxlength="9" size="9" class="form-control" name="cusipIdentifier"
				aria-label="Enter CUSIP Identifier"
				value="${fieldValue(bean:poolSearch,field:'cusipIdentifier')}">
                        </span> <span><button class="btn btn-primary" id="searchBtn" type="submit" >
					<i class="fa fa-search"></i>
					${message(code: 'PoolDetails.page.search.submit')}
				</button> </span>
                        <g:hiddenField name="poolError" value="${poolErrorField}" />
		</div>
	</g:form>
    </div>
  </div>
</div> <!-- end container-fluid -->
	 
	<g:if test="${flash.message}">
		<div class="alert alert-success" role="status">
			${flash.message}
		</div>
	</g:if>
	<g:if test="${flash.error}">
		<div class="alert alert-danger" role="status">
			<h4>${flash.error}</h4>
		</div>
	</g:if>
	<g:hasErrors bean="${poolSearch}">
		<div class="alert alert-danger" role="status">
			<h4><g:renderErrors bean="${poolSearch}"  /></h4>
		</div>
	</g:hasErrors>
	<br />

	<g:if test="${result }">
		<div id="show-MBSData" class="content scaffold-show" role="main">

			<ol class="property-list mbsdata">
				<g:each in="${result}" var="item">

					<li class="fieldcontain"><span id="${item.key}"
						class="property-label"> ${message(code:item.key + '.label')}:
							 
					</span> <span class="property-value" aria-labelledby="${item.key}"> 
							&nbsp;${item.value}
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
                        <g:hiddenField name="poolType" value="${poolType}" />
			<button class="btn btn-danger btn-lg" type="submit"
				onclick="return confirm('${message(code: 'PoolDetails.page.collapse.confirm')}');">
				<i class="fa fa-close"> </i>
				${message(code: 'PoolDetails.page.collapse.submit')}
			</button>
		</g:form>
			
		</g:if>
		
	</g:if>

</div><!--  close tabBody -->
</div><!--  close containerFluid -->

</body>
</html>

