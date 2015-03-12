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
        <button class="btn btn-primary" data-toggle="modal"  data-target="#modalEditWireSender">Edit</button>
		<button class="btn btn-success" data-toggle="modal"  data-target="#modalLoadWireSender">Load</button>
		<br><br>                       
		<div id="show-wireSenderData" class="content scaffold-show" role="main">
			<g:render template="wireinstructions" model="[wireinstructions: wireSender]"/>
		</div>
		 
        <h2>${message(code: 'PoolDetails.dissolve.wire.receiver.title')}</h2>
       	<button class="btn btn-primary" data-toggle="modal" data-target="#modalEditWireReceiver">Edit</button>
		<button class="btn btn-success" data-toggle="modal" data-target="#modalLoadWireReceiver">Load</button>
		<br><br>
		<div id="show-wireReceiverData" class="content scaffold-show" role="main">
			<g:render template="wireinstructions" model="[wireinstructions: wireReceiver]"/>
		</div>
		
		<h2>Dissolve Data</h2>
		<g:render template="resultfields" model="[items: result-2]" />               
		
		<g:if test="${!isDissolved}">
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


<!---- MODALS ------>


<div class="modal fade" id="modalWireSender" tabindex="-1" role="dialog" aria-labelledby="modalWireSender" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="modalWireSenderLabel">Select Previous Wire Sender</h4>
      </div>
      <div class="modal-body">
        <form class="form">
		
			<div class="radio">
			  <label>
				<input type="radio" name="optionsRadios" id="optionsRadios1" value="option1" checked>
				Institution One
			 </label>
			</div>
			<div class="radio">
			  <label>
				<input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">
				Institution Two
			 </label>
			</div>
			<div class="radio">
			  <label>
				<input type="radio" name="optionsRadios" id="optionsRadios3" value="option3"  >
				Institution Three
			 </label>
			</div>
		
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Select</button>
      </div>
    </div>
  </div>
</div>

<div class="modal fade" id="modalWireReceiver" tabindex="-1" role="dialog" aria-labelledby="modalWireReceiver" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="modalWireReceiverLabel">Select Previous Wire Receiver</h4>
      </div>
      <div class="modal-body">
		<form class="form">
			 
           <g:render template="loadsavedwire" model="[savedWireList: savedWireList]" />
              	 
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Select</button>
      </div>
    </div>
  </div>
</div>


<!---- MODALS ------>

<div class="modal fade" id="modalEditWireSender" tabindex="-1" role="dialog" aria-labelledby="modalEditWireSender" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Edit Wire Sender</h4>
      </div>
      <div class="modal-body">
        <form class="form">
		
		<table class="table table-striped table-compressed" style="width:100%">
		<tbody>
			<tr> 
				<td class="tableLabel">ABA Routing And Transit Identifier:</td> 
				<td class="dataBit"><input type="text" value="7890056435"></td>
			</tr>
			<tr>
				<td class="tableLabel">Financial Institution Subaccount Name:</td> 
				<td class="dataBit"><input type="text" value="Good N Plenty"></td>
 			</tr>
			<tr>
				<td class="tableLabel">Organization Name:</td> 
				<td class="dataBit"><input type="text" value="Freddie Mac"></td>
			</tr>
			<tr>
				<td class="tableLabel">Financial Institution Telegraphic Reverberation Name:</td> 
				<td class="dataBit"><input type="text" value="FMAC"></td>
 			</tr>
			 
		</tbody>
	</table>
		
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save</button>
      </div>
    </div>
  </div>
</div>

<div class="modal fade" id="modalEditWireReceiver" tabindex="-1" role="dialog" aria-labelledby="modalEditWireReceiver" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Edit Wire Receiver</h4>
      </div>
      <div class="modal-body">
        <form class="form">
		
		<table class="table table-striped table-compressed" style="width:100%">
		<tbody>
			<tr> 
				<td class="tableLabel">ABA Routing And Transit Identifier:</td> 
				<td class="dataBit"><input type="text" value="7890056435"></td>
			</tr>
			<tr>
				<td class="tableLabel">Financial Institution Subaccount Name:</td> 
				<td class="dataBit"><input type="text" value="Good N Plenty"></td>
 			</tr>
			<tr>
				<td class="tableLabel">Organization Name:</td> 
				<td class="dataBit"><input type="text" value="Freddie Mac"></td>
			</tr>
			<tr>
				<td class="tableLabel">Financial Institution Telegraphic Reverberation Name:</td> 
				<td class="dataBit"><input type="text" value="FMAC"></td>
 			</tr>
			 
		</tbody>
	</table>
		
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
        <button type="button" class="btn btn-primary">Save</button>
      </div>
    </div>
  </div>
</div>



<div class="modal fade" id="modalLoadWireSender" tabindex="-1" role="dialog" aria-labelledby="modalLoadWireSender" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Load Previous Wire Receiver</h4>
      </div>
      <div class="modal-body">
        <form class="form">
			 
           <g:render template="loadsavedwire" model="[savedWireList: savedWireList]" />
              	 
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
        <button type="button" class="btn btn-primary">Select</button>
      </div>
    </div>
  </div>
</div>

<div class="modal fade" id="modalLoadWireReceiver" tabindex="-1" role="dialog" aria-labelledby="modalLoadWireReceiver" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Load Previous Wire Receiver</h4>
      </div>
      <div class="modal-body">
		<form class="form">
			 
           <g:render template="loadsavedwire" model="[savedWireList: savedWireList]" />
              	 
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
        <button type="button" class="btn btn-primary">Select</button>
      </div>
    </div>
  </div>
</div>


<!---- MODALS ------>


</body>
</html>
