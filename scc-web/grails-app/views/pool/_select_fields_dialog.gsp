
<div class="modal fade" id="extraFieldsModal" tabindex="-1"
	role="dialog" aria-labelledby="extraFieldsModalLabel"
	aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="extraFieldsModalLabel">Select more
					fields to display</h4>
			</div>
			<g:formRemote name="extrafields_form"
				url="[controller:'pool', action: 'addExtraFields']"
				update="xfieldsContainer" onComplete="extraFieldsAdded();">
				<g:hiddenField name="cusipIdentifier"
					value="${poolSearch.cusipIdentifier}" />
				<g:hiddenField name="poolNumber" value="${poolSearch.poolNumber}" />
				<div class="modal-body">
					<div>
					   <g:checkBox id="selectAll" name="selectAll" checked="false" />
					   <label for="selectAll">Select All</label>
					</div>

					<g:each in="${xfields}" var="f">
						<div class="check-field">
						 
							<g:checkBox class="chk" id="${f}" value="${f}" name="xfield"
								checked="${poolSearch.xfield?.contains(f)}" />
							 <label for="${f}">
							<g:message code="${f}.label" />
						  </label>
						</div>
					</g:each>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="submit" class="btn btn-primary">Add</button>
				</div>
			</g:formRemote>
		</div>
	</div>
</div>

<g:javascript>
$(document).ready(function() {
  $("#selectAll").change(function(){
 //  alert($(this).prop("checked"));
    $(".chk").prop("checked",$(this).prop("checked"));
});
});

</g:javascript>