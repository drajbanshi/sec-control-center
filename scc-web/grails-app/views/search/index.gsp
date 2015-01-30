<%--
  Created by IntelliJ IDEA.
  User: dev
  Date: 1/29/15
  Time: 10:49 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>

<body>
  <g:form controller="search" action="search">
      <g:textField name="cusip" ></g:textField>
      <g:submitButton name="submit" value="Submit">Submit</g:submitButton>
  </g:form>
</body>
</html>