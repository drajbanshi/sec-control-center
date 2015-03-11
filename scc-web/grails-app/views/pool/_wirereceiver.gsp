<table class="table table-striped table-hover table-compressed" style="width:80%">
	<g:each in="${items}" var="item">
		<tr>
			<td class="table-label" id="${item.key}"> 
				${message(code:item.key + '.label')}:		 
			</td>
			<td class="table-value" aria-labelledby="${item.key}"> 
				${item.value}
			</td>
		</tr>
	</g:each>
</table>