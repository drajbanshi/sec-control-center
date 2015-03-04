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
  <ul class="nav nav-tabs">
	 <li role="presentation"><a href="#">Dashboard</a></li>
	 <li role="presentation" class="active"><a href="#">Pool Detail</a></li>
	 <!--li role="presentation"><a href="#">Dissolve</a></li-->
  </ul>
     
    <div class="tabBody">
      <g:form controller="pool" action="search" class="form-inline">
		 
			
				<label>
					${message(code: 'PoolDetails.page.input.poolnumber')}:
				</label> 
                        <span class='value ${hasErrors(bean:poolSearch,field:'poolNumber','errors')}'>
                        <input type="text" id="poolNumber" maxlength="8" size="8" class="form-control" name="poolNumber"
				aria-label="Enter Pool Number"
				value="${fieldValue(bean:poolSearch,field:'poolNumber')}">
						</span> &nbsp; &nbsp;                  
				<label>
					${message(code: 'PoolDetails.page.input.securitycusipidentifier')}:
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
		 
	</g:form>
     
	 
	<g:if test="${flash.message}">
		<br>
		<div class="alert alert-success" role="status">
			${flash.message}
		</div>
	</g:if>
	<g:if test="${flash.error}">
		<br>
		<div class="alert alert-danger" role="status">
			${flash.error}
		</div>
	</g:if>
	<g:hasErrors bean="${poolSearch}">
		<br>
		<div class="alert alert-danger" role="status">
			<g:renderErrors bean="${poolSearch}"  />
		</div>
	</g:hasErrors>
	 
	<g:if test="${result }">
		 
		 
			<div class="row">
				<div class="col-xs-6">
					<h2>${message(code: 'PoolDetails.page.header.title')}</h2>
					
		 			<button type="button" class="btn btn-success" style="margin-top:4px" data-toggle="modal" data-target="#extraFieldsModal">
						<i class="fa fa-plus"> </i> Select Additional Elements
					</button>	
					
					
					<div id="show-MBSData" class="content scaffold-show" role="main">
						<g:render template="resultfields" model="[items: result]" />
					</div>
				</div>
				<div class="col-xs-6">
				
					 
					<div id="xfieldsContainer">
					  <g:render template="resultfields" model="[items: extras]" />
					</div>	 
				</div>
 			</div>
		 
		
		<g:if test="${!isCollapsed}">
			<g:form controller="pool" action="collapse">
			<g:hiddenField name="poolid" value="${poolid}" />
			<g:hiddenField name="cusip" value="${cusip}" />
			<g:hiddenField name="cusipIdentifier" value="${poolSearch.cusipIdentifier}" />
			<g:hiddenField name="poolNumber" value="${poolSearch.poolNumber}" />
            <g:hiddenField name="poolType" value="${poolType}" />
            <div class="button-cont">
			<button class="btn btn-danger" type="submit"
				onclick="return confirm('${message(code: 'PoolDetails.page.collapse.confirm', args: [poolid])}');">
				<i class="fa fa-close"> </i>
				${message(code: 'PoolDetails.page.collapse.submit')}
			</button>
			</div>
		</g:form>
			
		</g:if>
		
	</g:if>
</div><!--  close tabBody -->
 </div><!--  close containerFluid -->
<g:javascript>
  function extraFieldsAdded() {
      $("#extraFieldsModal").modal("hide");
  }
</g:javascript>

</body>
</html>
