	 <g:each in="${savedWireList}" var="wire">
		<div>
                    <input type="radio" name="wireName" value="${wire.wireInstructionsName}">${wire.wireInstructionsName}<br>
		</div>
	</g:each>