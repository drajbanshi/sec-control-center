<table class="table table-striped table-hover table-compressed" style="width:auto">
	<g:each in="${items}" var="item">
		<tr>
			<td class="table-label" id="${item.key}">
				${message(code:item.key + '.label')}:		 
			</td>
			<td class="table-data" aria-labelledby="${item.key}"> 
				${item.value}
			</td>
		</tr>
	</g:each>
</table>