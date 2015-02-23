package com.freddiemac.scc.utils

class XsdGenerator {


	public static void main(String[] args) {
		XsdGenerator g= new XsdGenerator()
		g.generateFromXsd("../stub-server/CDM.xsd", [
			"LoanAmortizationType" : "Loan",
			"LoanGrossUPBAmount" : "LoanPosition",
			"PoolIdentifier":"Pool",
			"MBSMortgageType": "MBS",
			"MBSPoolPrefixCode": "MBS",
			"FinancialInstitutionAccountSubaccountName": "FinancialInstitutionAccount",
			"OrganizationName":"Organization",
			"ABARoutingAndTransitIdentifier":"FinancialInstitution",
			"FinancialInstitutionTelegraphicAbbreviationName":"FinancialInstitution",
			"SecurityWireFaceValueAmount":"SecurityWire",
			"SecurityCUSIPIdentifier":"Security",
			"SecurityIssueDate":"SecurityIssuance",
			"ContractualMaturityDate":"Security",
			"SecurityCouponRate":"SecurityActivity",
			"FinancialInstitutionAccountIdentifier":"FinancialInstitutionAccount",
			"SecurityBeneficiaryDate":"SecurityActivity",
			"SecurityStatusType":"SecurityActivity"
		])
	}


	def generateFromXsd(String file,def elements) {
		def gpath = new XmlSlurper().parse(new File(file))
		def parents = []
		def notfounds = []
		def keys = [:]
		elements.each { element, val ->

			def ent = gpath.complexType.find { it['@name'] == val }

			if(ent) {
				def e = ent.all.element.find { it['@name'] == element }

				if(e) {
					def p = e.parent().parent()
					def ptype = p['@name']

					keys.put("${ptype}.${element}", element)
					if(!parents.contains(ptype)) {
						println "<xs:element name=\"${ptype}\" type=\"${ptype}\"></xs:element>"
						parents.add(ptype)
					}
				} else {
					notfounds.add(element)
				}
			} else {
				notfounds.add(element)
			}
		}
		println  "-----NOt founds -----------"
		notfounds.each { println "key not fouund for ${it}." }

		println "===keys===="
		StringBuffer sb = new StringBuffer()


		keys.each { key,val ->
			if(sb.length() == 0) {
				sb.append("[")
			} else {
				sb.append(",")
			}
			sb.append("\"${key}\"")

			println "${key}=${val}"
		}

		sb.append("]")
		
		println "----config-----"
		println sb.toString()
	}
}
