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
			"PoolInstrument.PoolIdentifier" : "asdf",
			"Pool.PoolType" : "asdf",
			'Sender.FinancialInstitution.FinancialInstitutionTelegraphicAbbreviationName': "asdf",
			'Sender.FinancialInstitution.ABARoutingAndTransitIdentifier' : "asdf",
			'Sender.Organization.OrganizationName' : "asdf",
			'Sender.FinancialInstitutionAccount.FinancialInstitutionAccountSubaccountName' : "asdf",
			'Receiver.FinancialInstitution.FinancialInstitutionTelegraphicAbbreviationName' : "asdf",
			'Receiver.FinancialInstitution.ABARoutingAndTransitIdentifier': "asdf",
			'Receiver.Organization.OrganizationName': "asdf",
			'Receiver.FinancialInstitutionAccount.FinancialInstitutionAccountSubaccountName': "asdf",
			'SecurityWire.SecurityWireFaceValueAmount': "asdf",
			'TransferFee.TransferFeeAmount': "asdf",
			'PartyRole.PartyRoleType': "asdf",
			'SecurityIssuer.SecurityIssuerIdentifier': "asdf",
			'WireTransferTransaction.WireTotalFeesAmount': "asdf",
			'WireTransferTransaction.WireInstructionExecutionDate': "asdf",
			'CashTransferInstruction.CashTransferInstructionIdentifier': "asdf",
			'FinancialInstrument.FinancialInstrumentType': "asdf",
			'Loan.LoanIdentifier': "asdf"
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
