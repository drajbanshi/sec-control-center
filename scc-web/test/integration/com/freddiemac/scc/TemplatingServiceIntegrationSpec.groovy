package com.freddiemac.scc

import grails.test.spock.IntegrationSpec

class TemplatingServiceIntegrationSpec extends IntegrationSpec {

	def xmlTemplatingService

	void "test collapse event xml"() {
		when:
		String poolId = "abcdef"
		String poolType ="CASH"
		String xml = xmlTemplatingService.generateCollapseEvent(poolId,poolType)
		print xml
		then:
		assert xml != null && !xml.isEmpty()
		assert xml.contains(poolId)
		assert xml.contains(poolType)
	}

	void "generate dissolve event xml"() {
		given:
		def model = [
			"PoolInstrument_PoolIdentifier": "VSSSS",
			"Pool_PoolType" : "Cash",
			'FinancialInstitution_ABARoutingAndTransitIdentifier': "SSSS",
			'FinancialInstitutionAccount_FinancialInstitutionAccountSubaccountName': "SSSS",
			'Organization_OrganizationName': "TEST",
			//'PartyFinancialInstitution_FinancialInstitutionTelegraphicAbbreviationName',
			'FinancialInstitutionAccount_FinancialInstitutionAccountSubaccountName': 'SSS',
			'FinancialInstitution_FinancialInstitutionTelegraphicAbbreviationName': "FFFF",
			'SecurityWire_SecurityWireFaceValueAmount': "DDDD",
			'CashWire_WireAmount': "FFFf",
			'Security_SecurityCUSIPIdentifier' : "SSSS",
			'PartyRole_PartyRoleType':'PPPP', //No PartyRole -> multiple common fields
			'SecurityIssuer_SecurityIssuerIdentifier': 'SSSs',
			'WireParty_AdviceInformationText': "SSDSD",
			'WireParty_WirePartyInformationText': "GSDFS",
			'MBSDisclosure_MBSUPBAmount': "SSSS",
			'SecurityWire_SecurityWireFaceValueAmount' :  "SSS",
			'CashWire_WireAmount':  "SSSS",
			'SecurityWire_SecurityWireCUSIPIdentifier' : "AAAA",
			'WireParty_AdviceInformationText' :  "SSSS",
			'WireParty_WirePartyInformationText' : "AASSD",
			'WireTransferTransaction_WireInstructionExecutionDate' : "ASDFA",
			'CashTransferInstruction_CashTransferInstructionIdentifier' : "ASDDD",
			'FinancialInstrument_FinancialInstrumentType' : "ASDSD",
			'Loan_LoanIdentifier' :  "SSS"
		]
		
		when:
		String xml = xmlTemplatingService.generateDissolveEent(model)
		println xml
		
		then:
		xml != null
		xml.contains('$') == false
		def gpath = new XmlSlurper().parseText(xml)
		gpath.EventMetaData.EventName == "DISSOLVE_EVENT"
	}
}
