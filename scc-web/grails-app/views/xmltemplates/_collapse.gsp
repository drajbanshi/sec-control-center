<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<Events xmlns="http://www.freddiemac.com/enterpriseeventdatamodel/v1.0">
				  <EventMetaData>
				    <MessageIdentifier>${messageIdentifier}</MessageIdentifier>
				    <EventType>BUSINESS</EventType>
				    <EventName>COLLAPSE_EVENT</EventName>
				    <CorrelationIdentifier>str1234</CorrelationIdentifier>
				    <EventClassification>NOTIFICATION</EventClassification>
				    <ProducerName>str1234</ProducerName>
				    <MessageTimestamp>${eventTimeStamp}</MessageTimestamp>
				    <EventCount>1</EventCount>
				  </EventMetaData>
				  <EventPayload>
				    <EventContainer>
				      <EventIdentifier>${eventIdentifier}</EventIdentifier>
				      <BusinessContext>
				        <EntityIdentifier>str1234</EntityIdentifier>
				        <EntityType>SECURITY</EntityType>
				        <ProductType>str1234</ProductType>
				        <RequestActionType>NEW</RequestActionType>
				        <RelatedEventIdentifier>${eventIdentifier}</RelatedEventIdentifier>
				        <PayloadByReference>true</PayloadByReference>
				        <PayloadReferenceType>FILE</PayloadReferenceType>
				        <RequestEffectiveDateTime>${eventTimeStamp}</RequestEffectiveDateTime>
				        <BusinessEventTimestamp>${eventTimeStamp}</BusinessEventTimestamp>
				      </BusinessContext>
					    <BusinessData>
							   <CspSecIssuanceReqContentSet:CspSecIssuanceReqContentSet xmlns:CspSecIssuanceReqContentSet="http://www.freddiemac.com/cdm">
				                    <CspSecIssuanceReqContentSet:SecIssuanceReqContainer>
				                        <CspSecIssuanceReqContentSet:CollateralGroupContainer>
				                            <CspSecIssuanceReqContentSet:CollateralInstruments>
				                                <CspSecIssuanceReqContentSet:CollateralInstrumentContainer>
				                                    <CspSecIssuanceReqContentSet:CollateralSecurityContainer>
				                                        <CspSecIssuanceReqContentSet:PoolInstrument>
				                                            <CspSecIssuanceReqContentSet:CashPoolingRuleIdentifier xsi:nil="true" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"/>
				                                            <CspSecIssuanceReqContentSet:InstrumentRemovalInvestorPaymentDate xsi:nil="true" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"/>
				                                            <CspSecIssuanceReqContentSet:InstrumentRemovalPrincipalPaymentAmount xsi:nil="true" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"/>
				                                            <CspSecIssuanceReqContentSet:InstrumentRemovalReasonType xsi:nil="true" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"/>
				                                            <CspSecIssuanceReqContentSet:LoanOriginatorIdentifier xsi:nil="true" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"/>
				                                            <CspSecIssuanceReqContentSet:PoolIdentifier>${poolIdentifer}</CspSecIssuanceReqContentSet:PoolIdentifier>
				                                            <CspSecIssuanceReqContentSet:PoolInstrumentEffectiveDate xsi:nil="true" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"/>
				                                            <CspSecIssuanceReqContentSet:PoolInstrumentParticipationFaceAmount xsi:nil="true" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"/>
				                                            <CspSecIssuanceReqContentSet:PoolInstrumentParticipationRate xsi:nil="true" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"/>
				                                            <CspSecIssuanceReqContentSet:PoolInstrumentParticipationUPBAmount xsi:nil="true" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"/>
				                                            <CspSecIssuanceReqContentSet:PoolInstrumentTerminationDate xsi:nil="true" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"/>
				                                            <CspSecIssuanceReqContentSet:ServicerIdentifier xsi:nil="true" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"/>
				                                            <CspSecIssuanceReqContentSet:WrappedIndicator xsi:nil="true" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"/>
				                                        </CspSecIssuanceReqContentSet:PoolInstrument>
				                                    </CspSecIssuanceReqContentSet:CollateralSecurityContainer>
				                                </CspSecIssuanceReqContentSet:CollateralInstrumentContainer>
				                            </CspSecIssuanceReqContentSet:CollateralInstruments>
				                        </CspSecIssuanceReqContentSet:CollateralGroupContainer>
				                    </CspSecIssuanceReqContentSet:SecIssuanceReqContainer>
				                </CspSecIssuanceReqContentSet:CspSecIssuanceReqContentSet>
						</BusinessData>
				    </EventContainer>
				  </EventPayload>
</Events>

