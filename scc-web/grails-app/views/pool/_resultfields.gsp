<table class="table table-striped table-hover table-compressed" style="width:auto">
	<g:each in="${items}" var="item">
		<tr>
			<td class="table-label" id="${item.key}">
				${message(code:item.key + '.label')}:		 
                                <g:hiddenField name="${item.key}" value="${item.value}" />                                
			</td>
			<td class="table-data" aria-labelledby="${item.key}"> 
				${item.value}
			</td>
		</tr>
	</g:each>
</table>