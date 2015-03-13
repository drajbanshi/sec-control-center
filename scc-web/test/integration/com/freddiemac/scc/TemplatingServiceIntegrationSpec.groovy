package com.freddiemac.scc

import com.freddiemac.service.event.api.EventNotification;
import com.freddiemac.service.event.model.Events

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
			"PoolInstrument_PoolIdentifier" : "asdf",
			"Pool_PoolType" : "asdf",
			'Sender_FinancialInstitution_FinancialInstitutionTelegraphicAbbreviationName': "asdf",
			'Sender_FinancialInstitution_ABARoutingAndTransitIdentifier' : "asdf",
			'Sender_Organization_OrganizationName' : "asdf",
			'Sender_FinancialInstitutionAccount_FinancialInstitutionAccountSubaccountName' : "asdf",
			'Receiver_FinancialInstitution_FinancialInstitutionTelegraphicAbbreviationName' : "asdf",
			'Receiver_FinancialInstitution_ABARoutingAndTransitIdentifier': "asdf",
			'Receiver_Organization_OrganizationName': "asdf",
			'Receiver_FinancialInstitutionAccount_FinancialInstitutionAccountSubaccountName': "asdf",
			'SecurityWire_SecurityWireFaceValueAmount': "asdf",
			'TransferFee_TransferFeeAmount': "asdf",
			'PartyRole_PartyRoleType': "asdf",
			'SecurityIssuer_SecurityIssuerIdentifier': "asdf",
			'WireTransferTransaction_WireTotalFeesAmount': "asdf",
			'WireTransferTransaction_WireInstructionExecutionDate': "asdf",
			'CashTransferInstruction_CashTransferInstructionIdentifier': "asdf",
			'FinancialInstrument_FinancialInstrumentType': "asdf",
			'Loan_LoanIdentifier': "asdf"
		]

		when:
		String xml = xmlTemplatingService.generateDissolveEent(model)
		EventNotification en = new EventNotification()
		Events events = en.createEventFromXML(xml)

		then:
		xml != null
		xml.contains('$') == false
		def gpath = new XmlSlurper().parseText(xml)
		gpath.EventMetaData.EventName == "DISSOLVE_EVENT"

		events != null
		events.getEventMetaData().getEventName() == "DISSOLVE_EVENT"
		
		model.keySet().each { String key ->
			xml.contains(key) == false
		}
	}
}
