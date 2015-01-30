package com.freddiemac

class Security {

	Date    actualMaturityDate
	Date    amortizationEndDate
	String  assetBackedSecurityType
	String  businessCalendarType
	Date    contractualMaturityDate
	String  currencyType
	String  federalReserveSecurityClassCode
	String  federalReserveSecurityDescription
	String  internationalSecurityIdentifier
	String  maturityReasonType
	Double  minimumTradeDenominationAmount
	Double  minimumTradeIncrementAmount
	String  paymentFrequencyType
	String  principalAmortizationType
	String  registrationAgentIdentifier
	String  securitizationProgramIdentifier
	String  securityBusinessDayAdjustmentType
	String  securityCouponType
	Date    securityCouponTypeConversionDate
	String  securityCUSIPIdentifier
	Date    securityFirstInterestAccrualDate
	Date    securityFirstPaymentDate
	String  securityGuarantyContractIdentifier
	String  securityIssuerIdentifier
	Double  securityMaturityTermMonthCount
	String  securityMaturityType
	String  securityNotionalIndicator
	String  securityOwnershipMethodType
	String  securityPayingAgentIdentifier
	Double  securityPaymentDayCount
	String  securityPrincipalInterestType
	String  securityPrincipalRepaymentType
	String  securityPriorityType
	Double  securitySafeHarborRate
	String  settlementAgentIdentifier
	String  saxProvisionType
	String  saxablePercent
	String  underwriterIdentifier
	
	
	
    static constraints = {
		
		actualMaturityDate (blank:true, nullable:true)
		amortizationEndDate(blank:true, nullable:true)
		assetBackedSecurityType(blank:true, nullable:true)
		businessCalendarType(blank:true, nullable:true)
		contractualMaturityDate(blank:true, nullable:true)
		currencyType(blank:true, nullable:true)
		federalReserveSecurityClassCode(blank:true, nullable:true)
		federalReserveSecurityDescription(blank:true, nullable:true)
		internationalSecurityIdentifier(blank:true, nullable:true)
		maturityReasonType(blank:true, nullable:true)
		minimumTradeDenominationAmount(blank:true, nullable:true)
		minimumTradeIncrementAmount(blank:true, nullable:true)
		paymentFrequencyType(blank:true, nullable:true)
		principalAmortizationType(blank:true, nullable:true)
		registrationAgentIdentifier(blank:true, nullable:true)
		securitizationProgramIdentifier(blank:true, nullable:true)
		securityBusinessDayAdjustmentType(blank:true, nullable:true)
		securityCouponType(blank:true, nullable:true)
		securityCouponTypeConversionDate(blank:true, nullable:true)
		securityCUSIPIdentifier(blank:true, nullable:true)
		securityFirstInterestAccrualDate(blank:true, nullable:true)
		securityFirstPaymentDate(blank:true, nullable:true)
		securityGuarantyContractIdentifier(blank:true, nullable:true)
		securityIssuerIdentifier(blank:true, nullable:true)
		securityMaturityTermMonthCount(blank:true, nullable:true)
		securityMaturityType(blank:true, nullable:true)
		securityNotionalIndicator(blank:true, nullable:true)
		securityOwnershipMethodType(blank:true, nullable:true)
		securityPayingAgentIdentifier(blank:true, nullable:true)
		securityPaymentDayCount(blank:true, nullable:true)
		securityPrincipalInterestType(blank:true, nullable:true)
		securityPrincipalRepaymentType(blank:true, nullable:true)
		securityPriorityType(blank:true, nullable:true)
		securitySafeHarborRate(blank:true, nullable:true)
		settlementAgentIdentifier(blank:true, nullable:true)
		saxProvisionType(blank:true, nullable:true)
		saxablePercent(blank:true, nullable:true)
		underwriterIdentifier(blank:true, nullable:true)
		actualMaturityDate(blank:true, nullable:true)
    }
}
