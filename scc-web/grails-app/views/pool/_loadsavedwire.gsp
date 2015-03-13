	 <g:each in="${savedWireList}" var="wire">
		<div class="radio">
			<label>
				<input type="radio" name="wireName" value="${wire.abaRoutingNumber}|${wire.finInstitutionSubAcctName}|${wire.organizationName}|${wire.finInstitutionTelegraphicAbbrName}">${wire.wireInstructionsName}
			</label>
		</div>
	</g:each>