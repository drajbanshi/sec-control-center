<ol class="property-list mbsdata">
<g:each in="${items}" var="item">
	<li class="fieldcontain"><span id="${item.key}"
		class="property-label"> ${message(code:item.key + '.label')}: 
				</span> <span class="property-value" aria-labelledby="${item.key}">  
					&nbsp;${item.value} </span></li> 
</g:each>
</ol>