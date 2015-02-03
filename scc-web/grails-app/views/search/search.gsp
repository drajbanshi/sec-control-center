<%--
  Created by IntelliJ IDEA.
  User: dev
  Date: 1/29/15
  Time: 10:50 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>

<body>
<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if> 
  result:  ${result.ActualMaturityDate}
</body>
</html>