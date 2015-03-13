<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<Events xmlns="http://www.freddiemac.com/enterpriseeventdatamodel/v1.0">
<EventMetaData> 
	<MessageIdentifier>${messageIdentifier}</MessageIdentifier> 
	<EventType>BUSINESS</EventType>
	<EventName>DISSOLVE_EVENT</EventName>
	<CorrelationIdentifier>str1234</CorrelationIdentifier>
	<EventClassification>NOTIFICATION</EventClassification>
	<ProducerName>str1234</ProducerName> 
	<MessageTimestamp>${eventTimeStamp}</MessageTimestamp> 
	<EventCount>1</EventCount> 
</EventMetaData>
<EventPayload>
<EventContainer> <EventIdentifier> ${eventIdentifier}
</EventIdentifier> <BusinessContext> <EntityIdentifier>str1234</EntityIdentifier>
<EntityType>SECURITY</EntityType> <ProductType>str1234</ProductType> <RequestActionType>NEW</RequestActionType>
<RelatedEventIdentifier> ${eventIdentifier} </RelatedEventIdentifier> <PayloadByReference>true</PayloadByReference>
<PayloadReferenceType>FILE</PayloadReferenceType> <RequestEffectiveDateTime>
${eventTimeStamp} </RequestEffectiveDateTime> <BusinessEventTimestamp> ${eventTimeStamp}
</BusinessEventTimestamp> <PoolType> ${Pool_PoolType} </PoolType> </BusinessContext> <BusinessData> <p:CspSecIssuanceReqContentSet
	xmlns:p="http://www.freddiemac.com/cdm"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.freddiemac.com/cdm">
	<p:SecIssuanceReqContainer>
		<p:SecurityContainer>
			<p:Security>
				<p:SecurityCUSIPIdentifier>
					${Security_SecurityCUSIPIdentifier}
				</p:SecurityCUSIPIdentifier>
			</p:Security>
		</p:SecurityContainer>
		<p:MBSContainer>
		</p:MBSContainer>
		
		<p:SecurityIssuerContainer>
			<p:SecurityIssuer>
				<p:SecurityIssuerIdentifier>
					${SecurityIssuer_SecurityIssuerIdentifier}
				</p:SecurityIssuerIdentifier>
			</p:SecurityIssuer>
			
		</p:SecurityIssuerContainer>
		
		<p:CollateralGroupContainer>
			
			<p:CollateralInstruments>
				<p:CollateralInstrumentContainer>
					<p:Pool>
						<p:PoolIdentifier>
							${Pool_PoolIdentifier}
						</p:PoolIdentifier>
					</p:Pool>
					<p:CollateralLoanContainer>
						<p:Loan>
							<p:LoanIdentifier>${Loan_LoanIdentifier}</p:LoanIdentifier>
						</p:Loan>
					</p:CollateralLoanContainer>
					<p:CollateralSecurityContainer>
						<p:Security>
							<p:SecurityCUSIPIdentifier>${Security_SecurityCUSIPIdentifier}</p:SecurityCUSIPIdentifier>
						</p:Security>
						<p:PoolInstrument>
							<p:PoolIdentifier>${PoolInstrument_PoolIdentifer}</p:PoolIdentifier>
						</p:PoolInstrument>
					</p:CollateralSecurityContainer>
				</p:CollateralInstrumentContainer>
			</p:CollateralInstruments>
			<p:WireTransferTransactions>
				<p:WireTransferTransactionContainer>
					<p:WireTransferTransaction>
						<p:WireInstructionExecutionDate>${WireTransferTransaction_WireInstructionExecutionDate}</p:WireInstructionExecutionDate>
						<p:WireTotalFeesAmount>${WireTransferTransaction_WireTotalFeesAmount}</p:WireTotalFeesAmount>
					</p:WireTransferTransaction>
				
					<p:SecurityWire>
						<p:SecurityWireFaceValueAmount>${SecurityWire_SecurityWireFaceValueAmount}</p:SecurityWireFaceValueAmount>
					</p:SecurityWire>
					<p:TransferFee>
		            	<p:TransferFeeAmount>${TransferFee_TransferFeeAmount}</p:TransferFeeAmount>
		          	</p:TransferFee>
					<p:WireParties>
						<p:WirePartyContainer>
							<p:WireParty>
								<p:WirePartyRoleType>SENDER</p:WirePartyRoleType>
							</p:WireParty>
							<p:FinancialInstitution>
							    <p:FinancialInstitutionTelegraphicAbbreviationName>${Sender_FinancialInstitution_FinancialInstitutionTelegraphicAbbreviationName}</p:FinancialInstitutionTelegraphicAbbreviationName>
								<p:ABARoutingAndTransitIdentifier>${Sender_FinancialInstitution_ABARoutingAndTransitIdentifier}</p:ABARoutingAndTransitIdentifier>
							</p:FinancialInstitution>
							<p:Organization>
								<p:OrganizationName>${Sender_Organization_OrganizationName}</p:OrganizationName>
							</p:Organization>
							<p:FinancialInstitutionAccount>
								<p:FinancialInstitutionAccountSubaccountName>${Sender_FinancialInstitutionAccount_FinancialInstitutionAccountSubaccountName}</p:FinancialInstitutionAccountSubaccountName>
							</p:FinancialInstitutionAccount>
						</p:WirePartyContainer>
						<p:WirePartyContainer>
							<p:WireParty>
								<p:WirePartyRoleType>RECEIVER</p:WirePartyRoleType>
							</p:WireParty>
							<p:FinancialInstitution>
							    <p:FinancialInstitutionTelegraphicAbbreviationName>${Sender_FinancialInstitution_FinancialInstitutionTelegraphicAbbreviationName}</p:FinancialInstitutionTelegraphicAbbreviationName>
								<p:ABARoutingAndTransitIdentifier>${Sender_FinancialInstitution_ABARoutingAndTransitIdentifier}</p:ABARoutingAndTransitIdentifier>
							</p:FinancialInstitution>
							<p:Organization>
								<p:OrganizationName>${Sender_Organization_OrganizationName}</p:OrganizationName>
							</p:Organization>
							<p:FinancialInstitutionAccount>
								<p:FinancialInstitutionAccountSubaccountName>${FinancialInstitutionAccount_FinancialInstitutionAccountSubaccountName}</p:FinancialInstitutionAccountSubaccountName>
							</p:FinancialInstitutionAccount>
						</p:WirePartyContainer>
					</p:WireParties>
					<p:CashTransferInstruction>
						<p:CashTransferInstructionIdentifier>${CashTransferInstruction_CashTransferInstructionIdentifier}</p:CashTransferInstructionIdentifier>
					</p:CashTransferInstruction>
					<p:CashWire>
						<p:WireAmount>${CashWire_WireAmount}</p:WireAmount>
					</p:CashWire>			
				</p:WireTransferTransactionContainer>
			</p:WireTransferTransactions>
		</p:CollateralGroupContainer>
	</p:SecIssuanceReqContainer>
</p:CspSecIssuanceReqContentSet> </BusinessData> </EventContainer> </EventPayload> </Events>

