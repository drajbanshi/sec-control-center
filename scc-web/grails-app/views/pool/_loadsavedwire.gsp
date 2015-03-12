	 <g:each in="${savedWireList}" var="wire">
		<div class="radio">
			<label>
				<input type="radio" name="wireName" value="${wire.wireInstructionsName}">${wire.wireInstructionsName}
			</label>
		</div>
	</g:each>