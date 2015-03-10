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
	 <li role="presentation"><a href="/scc/pool/collapsesearch">Pool Detail</a></li>
         <li role="presentation" class="active"><a href="/scc/pool/dissolvesearch">Pool Dissolve</a></li>
  </ul>
     
    <div class="tabBody">
           <g:form controller="pool" action="search" class="form-inline">
                <g:render template="searchinput" />     
                 <g:hiddenField name="pageFunction" value="Dissolve" />
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
	
		<h2>${message(code: 'PoolDetails.dissolve.pooldetails.title')} <g:if test="${poolid}"> ${poolid}</g:if></h2>
		<div id="show-MBSData" class="content scaffold-show" role="main">
			<g:render template="resultfields" model="[items: result]" />
		</div>
		 
        <h2>${message(code: 'PoolDetails.dissolve.wire.sender.title')}</h2>                                
		<div id="show-wireSenderData" class="content scaffold-show" role="main">
			<g:render template="wiresender" model="[items: wireSender]"/>
		</div>
		 
        <h2>${message(code: 'PoolDetails.dissolve.wire.receiver.title')}</h2>
		<div id="show-wireReceiverData" class="content scaffold-show" role="main">
			<g:render template="wirereceiver" model="[items: wireReceiver]"/>
		</div>
		
		<g:if test="${!isCollapsed}">
			<g:form controller="pool" action="dissolve">
			<g:hiddenField name="poolid" value="${poolid}" />
			<g:hiddenField name="cusip" value="${cusip}" />
			<g:hiddenField name="cusipIdentifier" value="${poolSearch.cusipIdentifier}" />
			<g:hiddenField name="poolNumber" value="${poolSearch.poolNumber}" />
                        <g:hiddenField name="pageFunction" value="Dissolve" />
            <g:hiddenField name="poolType" value="${poolType}" />
            <div class="button-cont">
			<button class="btn btn-danger" type="submit" >
				<%-- onclick="return confirm('${message(code: 'PoolDetails.page.dissolve.confirm', args: [poolid])}');"> --%>
				<i class="fa fa-close"> </i>
				${message(code: 'PoolDetails.page.dissolve.submit')}
			</button>
			</div>
		</g:form>
			
		</g:if>
		
	</g:if>
</div><!--  close tabBody -->
 </div><!--  close containerFluid -->


</body>
</html>
