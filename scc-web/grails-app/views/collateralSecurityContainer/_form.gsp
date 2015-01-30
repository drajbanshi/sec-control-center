<%@ page import="com.freddiemac.CollateralSecurityContainer" %>



<div class="fieldcontain ${hasErrors(bean: collateralSecurityContainerInstance, field: 'security', 'error')} required">
	<label for="security">
		<g:message code="collateralSecurityContainer.security.label" default="Security" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="security" name="security.id" from="${com.freddiemac.Security.list()}" optionKey="id" required="" value="${collateralSecurityContainerInstance?.security?.id}" class="many-to-one"/>

</div>

