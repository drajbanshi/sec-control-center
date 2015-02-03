<%--
  Created by IntelliJ IDEA.
  User: dev
  Date: 1/29/15
  Time: 10:49 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Dissolve - Securitization Control Center</title>
</head>

<body>
<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if> 
  <g:form controller="search" action="search">
  	<h1>Dissolve</h1>	
      CUSIP identifier : <g:textField name="cusip" ></g:textField>
      <g:submitButton name="submit" value="Submit" />
  </g:form>
</body>
</html>