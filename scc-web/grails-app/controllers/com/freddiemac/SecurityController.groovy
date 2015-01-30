package com.freddiemac

import grails.converters.XML
import groovy.xml.StreamingMarkupBuilder
import groovy.xml.MarkupBuilder
import groovy.xml.XmlUtil



class SecurityController {
	
	static scaffold = true

    def index() { 
		respond Security.list(params), model:[Security:Security]
	}
	
	def generateXML() {
		def securityInstance = Security.get(1)
		//render securityInstance as XML
		
		def builder = new groovy.xml.StreamingMarkupBuilder()
		//def xml = new MarkupBuilder().Events{
			
			/*EventMetaData1(a1:'one') {b(b1:'two')
				}
			}*/
		def rawData = builder.bind {
			
				   Events{
					   EventMetaData.each {}
						   EventPayload{
							   EventContainer{
								   EventIdentifier{}
								   
								   BusinessContext.each{}
									BusinessData{
										CspSecIssuanceReqContentSet{
											SecIssuanceReqContainer{
												CollateralGroupContainer{
													CollateralInstruments{
														CollateralInstrumentContainer{
															CollateralSecurityContainer{
																Security{ Security.getAll()
															}
														}
													}
												}
												
											}
										}
										}
									   
								   }
							   }
						   }
					   }
			
				   }
		println rawData
		def xml = XmlUtil.serialize(rawData)
		println "final XML: ${xml}"
		
		
			   }

	

	
	}
