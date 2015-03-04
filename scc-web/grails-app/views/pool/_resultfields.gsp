<table class="table table-striped table-hover table-compressed" style="width:auto">
	<g:each in="${items}" var="item">
		<tr>
			<td class="fieldcontain" id="${item.key}"
				class="property-label"> ${message(code:item.key + '.label')}:		 
			</td>
			<td class="property-value" aria-labelledby="${item.key}"> 
				${item.value}
			</td>
		</tr>
	</g:each>
</table>