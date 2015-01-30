<%--
  Created by IntelliJ IDEA.
  User: dev
  Date: 1/29/15
  Time: 10:49 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<style >
	body {
    background-image: url("paper.gif");
    background-color: #cccccc;
}		
		
			h2 {
				margin-top: 1em;
				margin-bottom: 0.3em;
				font-size: 1em;
			}

			p {
				line-height: 1.5;
				margin: 0.25em 0;
			}
	
			 h1 {
					margin-top: 0;
					text-align: center;
					
				}
			}
		</style>

    <title> </title>
    <h1 > Search SCC Data </h1>
</head>

<body>
  <g:form controller="search" action="search">
      <g:textField name="cusip" ></g:textField>
      <g:submitButton name="submit" value="Submit">Submit</g:submitButton>
  </g:form>
</body>
</html>