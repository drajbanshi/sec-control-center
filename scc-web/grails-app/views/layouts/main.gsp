<!DOCTYPE html>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
	
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
		<title><g:layoutTitle default="Securitization Control Center"/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="shortcut icon" href="${assetPath(src: 'favicon.ico')}" type="image/x-icon">
		<link rel="apple-touch-icon" href="${assetPath(src: 'apple-touch-icon.png')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${assetPath(src: 'apple-touch-icon-retina.png')}">
  		<asset:stylesheet src="application.css"/>
		<asset:javascript src="application.js"/>
    <g:layoutHead/>
	</head>
	<body>
    
 	<header>
		<div id="user" class="container-fluid">
			<div class="row">
				<div class="col-xs-6"><a href="#">Home</a></div>
				<div class="col-xs-6 text-right">Logged as James P. Wolfson</div>
			</div>
		</div>

		<div id="brand">
			<a href="http://freddiemac.com"><span id="logo"> </span></a><h1>Securitization Control Center</h1>
		</div>
		<hr />
	</header>

    <div class="container-fluid mainbody">
       
		  <ul class="nav nav-tabs">
			 <li role="presentation"><a href="#">Dashboard</a></li>
			 <li role="presentation" class="${params.action=='collapsesearch' || "Collapse".equalsIgnoreCase(params.pageFunction) ? 'active' : '' }">
			  <g:link controller="pool" action="collapsesearch">Pool Detail</g:link>
			 <li role="presentation" class="${params.action=='dissolvesearch' || "Dissolve".equalsIgnoreCase(params.pageFunction) ? 'active' : '' }">
			  <g:link controller="pool" action="dissolvesearch">Pool Dissolve</g:link>
			</li>
		  </ul>
	     
	    <div class="tabBody">
	    	<g:layoutBody/>
	    </div>
    </div>


	<footer role="contentinfo">
        &copy; Freddie Mac 2015    
	</footer>

	<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
	</body>
</html>
