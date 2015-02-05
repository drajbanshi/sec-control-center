<%--
  Created by IntelliJ IDEA.
  User: dev
  Date: 1/29/15
  Time: 10:49 PM
--%>

<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<meta name="layout" content="main">
<title>Dissolve - Securitization Control Center</title>
</head>
<body>
	<h3>Dissolve Pool</h3>
	<g:if test="${flash.message}">
		<div class="alert alert-success" role="status">
			${flash.message}
		</div>
	</g:if>
	<g:form controller="search" action="search" class="form-inline">
		<label>Security CUSIP Identifier :</label>
		<input type="text" class="form-control" name="cusip"
			aria-label="Enter CUSIP Identifier..." value="${params.cusip}">
		<button class="btn btn-primary" type="submit">
			<i class="fa fa-search"></i> Search
		</button>
	</g:form>
	<g:if test="${result }">
		<div id="show-MBSData" class="content scaffold-show" role="main">

			<hr />
			<h3>Pool Details..</h3>
			<ol class="property-list mbsdata">

				<g:if test="${result?.SecurityCUSIPIdentifier}">
					<li class="fieldcontain"><span id="SecurityCUSIPIdentifier"
						class="property-label">Security CUSIPIdentifier :</span> <span
						class="property-value" aria-labelledby="SecurityCUSIPIdentifier">
							${
								result.AssetBackedSecurityType
							}
					</span></li>
				</g:if>
				<g:if test="${result?.ActualMaturityDate}">
					<li class="fieldcontain"><span id="ActualMaturityDate"
						class="property-label">Actual Maturity Date :</span> <span
						class="property-value" aria-labelledby="ActualMaturityDate">
							${result.ActualMaturityDate}
					</span></li>
				</g:if>
				<g:if test="${result?.AmortizationEndDate}">
					<li class="fieldcontain"><span id="AmortizationEndDate"
						class="property-label">Amortization End Date :</span> <span
						class="property-value" aria-labelledby="AmortizationEndDate">
							${result.AmortizationEndDate}
					</span></li>
				</g:if>
				<g:if test="${result?.AssetBackedSecurityType}">
					<li class="fieldcontain"><span id="AssetBackedSecurityType"
						class="property-label">Asset Backed Security Type :</span> <span
						class="property-value" aria-labelledby="AssetBackedSecurityType">
							${result.AssetBackedSecurityType}
					</span></li>
				</g:if>
				<g:if test="${result?.BusinessCalendarType}">
					<li class="fieldcontain"><span id="BusinessCalendarType"
						class="property-label">Business Calendar Type :</span> <span
						class="property-value" aria-labelledby="BusinessCalendarType">
							${result.BusinessCalendarType}
					</span></li>
				</g:if>
				<g:if test="${result?.ContractualMaturityDate}">
					<li class="fieldcontain"><span id="ContractualMaturityDate"
						class="property-label">Contractual Maturity Date :</span> <span
						class="property-value" aria-labelledby="ContractualMaturityDate">
							${result.ContractualMaturityDate}
					</span></li>
				</g:if>
				<g:if test="${result?.CurrencyType}">
					<li class="fieldcontain"><span id="CurrencyType"
						class="property-label">Currency Type :</span> <span
						class="property-value" aria-labelledby="CurrencyType">
							${result.CurrencyType}
					</span></li>
				</g:if>
				<g:if test="${result?.FederalReserveSecurityClassCode}">
					<li class="fieldcontain"><span
						id="FederalReserveSecurityClassCode" class="property-label">Federal
							Reserve Security Class Code :</span> <span class="property-value"
						aria-labelledby="FederalReserveSecurityClassCode">
							${result.FederalReserveSecurityClassCode}
					</span></li>
				</g:if>
				<g:if test="${result?.FederalReserveSecurityClassCode}">
					<li class="fieldcontain"><span
						id="FederalReserveSecurityDescription" class="property-label">Federal
							Reserve Security Description :</span> <span class="property-value"
						aria-labelledby="FederalReserveSecurityDescription">
							${result.FederalReserveSecurityDescription}
					</span></li>
				</g:if>
				<g:if test="${result?.FederalReserveSecurityClassCode}">
					<li class="fieldcontain"><span
						id="InternationalSecurityIdentifier" class="property-label">International
							Security Identifier :</span> <span class="property-value"
						aria-labelledby="InternationalSecurityIdentifier">
							${result.InternationalSecurityIdentifier}
					</span></li>
				</g:if>
				<g:if test="${result?.FederalReserveSecurityClassCode}">
					<li class="fieldcontain"><span id="MaturityReasonType"
						class="property-label">Maturity Reason Type :</span> <span
						class="property-value" aria-labelledby="MaturityReasonType">
							${result.MaturityReasonType}
					</span></li>
				</g:if>
				<g:if test="${result?.FederalReserveSecurityClassCode}">
					<li class="fieldcontain"><span id="PrincipalAmortizationType"
						class="property-label">Principal Amortization Type :</span> <span
						class="property-value" aria-labelledby="PrincipalAmortizationType">
							${result.PrincipalAmortizationType}
					</span></li>
				</g:if>
				<g:if test="${result?.FederalReserveSecurityClassCode}">
					<li class="fieldcontain"><span
						id="SecuritizationProgramIdentifier" class="property-label">Securitization
							Program Identifier:</span> <span class="property-value"
						aria-labelledby="SecuritizationProgramIdentifier">
							${result.SecuritizationProgramIdentifier}
					</span></li>
				</g:if>
				<g:if test="${result?.FederalReserveSecurityClassCode}">
					<li class="fieldcontain"><span
						id="SecurityBusinessDayAdjustmentType" class="property-label">Security
							BusinessDay Adjustment Type :</span> <span class="property-value"
						aria-labelledby="SecurityBusinessDayAdjustmentType">
							${result.SecurityBusinessDayAdjustmentType}
					</span></li>
				</g:if>
				<g:if test="${result?.FederalReserveSecurityClassCode}">
					<li class="fieldcontain"><span
						id="SecurityBusinessDayAdjustmentType" class="property-label">Security
							BusinessDay Adjustment Type :</span> <span class="property-value"
						aria-labelledby="SecurityBusinessDayAdjustmentType">
							${result.SecurityBusinessDayAdjustmentType}
					</span></li>
				</g:if>
				<g:if test="${result?.SecurityCouponType}">
					<li class="fieldcontain"><span id="SecurityCouponType"
						class="property-label">Security Coupon Type :</span> <span
						class="property-value" aria-labelledby="SecurityCouponType">
							${result.SecurityCouponType}
					</span></li>
				</g:if>
				<g:if test="${result?.SecurityCouponTypeConversionDate}">
					<li class="fieldcontain"><span
						id="SecurityCouponTypeConversionDate" class="property-label">Security
							Coupon Type Conversion Date :</span> <span class="property-value"
						aria-labelledby="SecurityCouponTypeConversionDate">
							${result.SecurityCouponTypeConversionDate}
					</span></li>
				</g:if>
				<g:if test="${result?.SecurityFirstInterestAccrualDate}">
					<li class="fieldcontain"><span
						id="SecurityFirstInterestAccrualDate" class="property-label">Security
							First Interest Accrual Date :</span> <span class="property-value"
						aria-labelledby="SecurityFirstInterestAccrualDate">
							${result.SecurityFirstInterestAccrualDate}
					</span></li>
				</g:if>
				<g:if test="${result?.SecurityFirstPaymentDate}">
					<li class="fieldcontain"><span id="SecurityFirstPaymentDate"
						class="property-label">Security First Payment Date :</span> <span
						class="property-value" aria-labelledby="SecurityFirstPaymentDate">
							${result.SecurityFirstPaymentDate}
					</span></li>
				</g:if>
				<g:if test="${result?.SecurityGuarantyContractIdentifier}">
					<li class="fieldcontain"><span
						id="SecurityGuarantyContractIdentifier" class="property-label">Security
							Guaranty Contract Identifier :</span> <span class="property-value"
						aria-labelledby="SecurityGuarantyContractIdentifier">
							${result.SecurityGuarantyContractIdentifier}
					</span></li>
				</g:if>
				<g:if test="${result?.SecurityIssuerIdentifier}">
					<li class="fieldcontain"><span id="SecurityIssuerIdentifier"
						class="property-label">Security Issuer Identifier :</span> <span
						class="property-value" aria-labelledby="SecurityIssuerIdentifier">
							${result.SecurityIssuerIdentifier}
					</span></li>
				</g:if>
				<g:if test="${result?.SecurityMaturityTermMonthCount}">
					<li class="fieldcontain"><span
						id="SecurityMaturityTermMonthCount" class="property-label">Security
							Maturity Term Month Count :</span> <span class="property-value"
						aria-labelledby="SecurityBusinessDayAdjustmentType">
							${result.SecurityMaturityTermMonthCount}
					</span></li>
				</g:if>
				<g:if test="${result?.SecurityMaturityType}">
					<li class="fieldcontain"><span id="SecurityMaturityType"
						class="property-label">Security Maturity Type :</span> <span
						class="property-value" aria-labelledby="SecurityMaturityType">
							${result.SecurityMaturityType}
					</span></li>
				</g:if>
				<g:if test="${result?.SecurityNotionalIndicator}">
					<li class="fieldcontain"><span id="SecurityNotionalIndicator"
						class="property-label">Security Notional Indicator :</span> <span
						class="property-value" aria-labelledby="SecurityNotionalIndicator">
							${result.SecurityNotionalIndicator}
					</span></li>
				</g:if>
				<g:if test="${result?.SecurityOwnershipMethodType}">
					<li class="fieldcontain"><span
						id="SecurityOwnershipMethodType" class="property-label">Security
							Ownership Method Type :</span> <span class="property-value"
						aria-labelledby="SecurityOwnershipMethodType">
							${result.SecurityOwnershipMethodType}
					</span></li>
				</g:if>
				<g:if test="${result?.SecurityMaturityTermMonthCount}">
					<li class="fieldcontain"><span
						id="SecurityPayingAgentIdentifier" class="property-label">Security
							Paying Agent Identifier :</span> <span class="property-value"
						aria-labelledby="SecurityPayingAgentIdentifier">
							${result.SecurityPayingAgentIdentifier}
					</span></li>
				</g:if>

			</ol>
		</div>
		<g:form controller="search" action="dissolve">
			<g:hiddenField name="cusip" value="${params.cusip}" />
			<button class="btn btn-danger" type="submit"
				onclick="return confirm('Are you sure you want to dissove this pool?');">
				<i class="fa fa-close"> </i> Dissolve
			</button>
		</g:form>
	</g:if>

</body>
</html>