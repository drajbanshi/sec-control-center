package com.freddiemac.scc.utils

class XsdGenerator {


	public static void main(String[] args) {
		XsdGenerator g= new XsdGenerator()

		def extras = ['LoanAmortizationType':'LoanRepaymentRule',
			'LoanRoleType':'LoanRelationship',
			'ContractGuarantyFeeRate':'LoanRepaymentRule',
			'LoanGrossUPBAmount':'LoanPosition',
			'MBSAmortizationType':'MBS',
			'MBSBalloonTermCount':'MBSDisclosure',
			'PoolIdentifier':'Pool',
			'InterestOnlyTermCount':'MBS',
			'MBSPoolPrefixCode':'MBS',
			'MBSExecutionMethodType':'MBS',
			'ContractExecutionMethodType':'CollateralGroup',
			'SecurityCUSIPIdentifier':'Security',
			'FederalReserveSecurityClassCode':'DebtSecurity',
			'FederalReserveSecurityDescription':'DebtSecurity',
			'SecurityIssueDate':'SecurityIssuance',
			'SecurityIssueFaceAmount':'SecurityIssuance',
			'SecurityFirstPaymentDate':'Security',
			'ContractualMaturityDate':'Security',
			'DebtSecurityCouponRate':'SecurityActivity',
			'MinimumTradeDenominationAmount':'Security',
			'FinancialInstitutionAccountIdentifier':'FinancialInstitutionAccount',
			'SecurityFirstEligibleTradeDate':'SecurityIssuance',
			'AssetBackedSecurityType':'Security',
			'ProgramIdentifier':'SecuritizationProgram',
			'LoanInvestorPassThroughRate':'LoanPaymentRate',
			'SecurityRecordDate':'SecurityActivity',
			'MBSInterestPaymentFactorRate':'MBSDisclosure',
			'SecuritizationTrustIdentifier':'SecuritizationTrust',
			'SecurityPayingAgentIdentifier':'Security',
			'SecurityBeneficiaryDate':'SecurityActivity',
			'SecurityTaxRecordDate':'SecurityActivity',
			'DebtSecurityStatusType':'SecurityActivity',
			'SecurityIssuerIdentifier':'SecurityIssuer',
			'PartyRoleType':'PartyRole',
			'LoanIdentifier':'Loan',
			'CashTransferInstructionIdentifier':'CashTransferInstruction',
			'FinancialInstrumentType':'FinancialInstrument',
			'LoanInvestorRemittanceType':'LoanServicingTerms']
		
		def p = [
			"LoanAmortizationType" : "Loan",
			"LoanGrossUPBAmount" : "LoanPosition",
			"PoolIdentifier":"Pool",
			"MBSMortgageType": "MBS",
			"MBSPoolPrefixCode": "MBS",
		//	"FinancialInstitutionAccountSubaccountName": "FinancialInstitutionAccount",
		//	"OrganizationName":"Organization",
		//	"ABARoutingAndTransitIdentifier":"FinancialInstitution",
		//	"FinancialInstitutionTelegraphicAbbreviationName":"FinancialInstitution",
			"SecurityWireFaceValueAmount":"SecurityWire",
			"SecurityCUSIPIdentifier":"Security",
			"SecurityIssueDate":"SecurityIssuance",
			"ContractualMaturityDate":"Security",
			"SecurityCouponRate":"SecurityActivity",
		//	"FinancialInstitutionAccountIdentifier":"FinancialInstitutionAccount",
			"SecurityBeneficiaryDate":"SecurityActivity",
			"SecurityStatusType":"SecurityActivity"
		]
		
		
		p.each {
			 println "${it.key}:${it.value} = "
		}
		
		println "--------"
		
		p.putAll(extras)
		p.each { 
		    println "${it.key}:${it.value} = "
		}


		g.generateFromXsd("../stub-server/CDM.xsd", p,extras)
	}


	def generateFromXsd(String file,Map<String, String> elements, Map<String,String> extras) {
		def gpath = new XmlSlurper().parse(new File(file))
		def parents = []
		def notfounds = []
		def keys = [:]

		def map = [:]
		map.putAll(elements)
		map.putAll(extras)

		def notinjims = [:]

		map.each { element, val ->


			
				def e = gpath.complexType.all.element.find { it['@name'] == element }

				if(e) {
					def p = e.parent().parent()
					def ptype = p['@name']
					if(!ptype.equals(val)) {
						
					}
					if(!elements.containsKey(element)) {
						notinjims.put("${ptype}.${element}", element)
						
					} else {
						keys.put("${ptype}.${element}", element)
					
					}

					if(!parents.contains(ptype)) {
						println "<xs:element name=\"${ptype}\" type=\"${ptype}\"></xs:element>"
						parents.add(ptype)
					}
				} else {
					notfounds.add(element)
				}
			
		}



		println  "-----NOt founds -----------"
		notfounds.each { println "key not fouund for ${it}." }

		println "===keys===="
		println generateConfig(keys)

	
		println "----not in jms-----"
		println generateConfig(notinjims)
	}
	
	private def generateConfig(Map<String,String> keys) {
		StringBuilder sb = new StringBuilder()
		keys.each { key,val ->
			if(sb.length() == 0) {
				sb.append("[")
			} else {
				sb.append(",")
			}
			sb.append("\"${key}\"")

			println "${key}.label=${convertCamelCase(val)}"
		}
		
		return sb.toString()

	}
	
	private def convertCamelCase(String inp) {
		String regex = "([a-z])([A-Z])"
		String replacement = '$1 $2'
		return inp.replaceAll(regex, replacement)
	}
}
