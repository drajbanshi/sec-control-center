package com.freddiemac.scc

import com.freddiemac.scc.model.PoolSearch
import com.freddiemac.scc.model.PropContainer
import com.freddiemac.scc.utils.DateUtils;
import com.freddiemac.scc.utils.PropertyRetriever
import com.freddiemac.scc.security.WireInstructions

class PoolController {

	def searchService
	def dispatchService
	def grailsApplication
	def eventLogService
	def wireInstructionsService


	def index() {
		render view: "/pool/collapsesearch"
	}

	def collapsesearch() {
		render view: "/pool/collapsesearch"
	}

	def dissolvesearch() {
		render view: "/pool/dissolvesearch"
	}

	def addExtraFields(PoolSearch poolSearch) {
		def m = searchService.searchPool(poolSearch.cusipIdentifier, poolSearch.poolNumber)
		render template: 'resultfields', model: [items: m.success?  generateModel(m.result, poolSearch.xfield) : []]
	}



	def search(PoolSearch poolSearch) {
		long start = System.currentTimeMillis();
		def poolErrorField = ""

		boolean searchForDissolve =  "Dissolve".equalsIgnoreCase(params.pageFunction)

		if(poolSearch.hasErrors()) {
			poolSearch.errors.each {
				if (it.toString().indexOf('cusipIdentifier') > -1) {
					poolErrorField="cusipIdentifier"
				}
				if (it.toString().indexOf('poolNumber') > -1) {
					poolErrorField="poolNumber"
				}
			}

			render view: searchForDissolve ? "/pool/dissolvesearch" : "/pool/collapsesearch" , model: [poolSearch: poolSearch, poolid: params.poolNumber, cusip: params.cusipIdentifier, poolErrorField:poolErrorField]
			return
		}

		def m = searchService.searchPool(poolSearch.cusipIdentifier, poolSearch.poolNumber)
		if (poolSearch.cusipIdentifier) {
			poolErrorField = "cusipIdentifier"
		} else if (poolSearch.poolNumber) {
			poolErrorField = "poolNumber"
		}
		if (!m.success) {
			if (m.errorMessage.equals(message(code: 'Collapse.controller.search.error3'))) {
				flash.error =  message(code: 'Collapse.controller.search.error3')
				render view: searchForDissolve ? "/pool/dissolvesearch" : "/pool/collapsesearch" , model: [poolSearch: poolSearch, poolid: params.poolNumber, cusip: params.cusipIdentifier, poolErrorField:poolErrorField]

				return
			}

			if (poolSearch.cusipIdentifier) {
				flash.error =  message(code: 'Collapse.controller.search.error1', args: [poolSearch.cusipIdentifier])
			} else {
				flash.error =  message(code: 'Collapse.controller.search.error2', args: [poolSearch.poolNumber])
			}
			render view: searchForDissolve ? "/pool/dissolvesearch" : "/pool/collapsesearch" , model: [poolSearch: poolSearch, poolid: params.poolNumber, cusip: params.cusipIdentifier, poolErrorField:poolErrorField]
		} else {
			String poolid = poolSearch.poolNumber ?:PropertyRetriever.getProp(grailsApplication.config.com.freddiemac.searchpool.result.poolid, m.result)
			String cusip = poolSearch.cusipIdentifier ?:PropertyRetriever.getProp(grailsApplication.config.com.freddiemac.searchpool.result.cusip, m.result)
			String secIssueDt = PropertyRetriever.getProp(grailsApplication.config.com.freddiemac.searchpool.result.securityissuedate, m.result)
			String poolType = PropertyRetriever.getProp(grailsApplication.config.com.freddiemac.searchpool.result.pooltype, m.result)
			def isCollapsed 
                        def isDissolved
			if (searchForDissolve) {
                                isDissolved = (!(DateUtils.isPastDate(secIssueDt))) || eventLogService.isEventProcessedForCusip(cusip)
				render view: 'dissolvesearch', model: ['result': generateModel(m.result), 'fieldsDissolve': generateModel(m.result, grailsApplication.config.com.freddiemac.searchpool.result.dissolve.elements), isDissolved:isDissolved, poolid: poolid, cusip: cusip,  poolSearch:poolSearch, poolType: poolType, poolErrorField: poolErrorField,  wireSender: WireInstructions.findByWireInstructionsName("Freddie Mac"), wireReceiver: WireInstructions.findByWireInstructionsName("Last National Bank and Trust"), savedWireList: WireInstructions.list()]
                            }
			else {
                                isCollapsed = DateUtils.isPastDate(secIssueDt) || eventLogService.isEventProcessedForCusip(cusip)
				render view: 'collapsesearch', model: ['result': generateModel(m.result), isCollapsed:isCollapsed, poolid: poolid, cusip: cusip,  poolSearch:poolSearch, poolType: poolType, poolErrorField: poolErrorField,  xfields: grailsApplication.config.com.freddiemac.searchpool.result.xfields]
                            }
		}

		log.info("Time taken for search() : " + (System.currentTimeMillis() - start)+ " ms")
	}


	def collapse() {
		long start = System.currentTimeMillis();
		def poolErrorField = ""
		if(params.poolid && params.cusip && params.poolType) {
			if (params.cusipIdentifier) {
				poolErrorField = params.cusipIdentifier
			} else {
				poolErrorField = params.poolNumber
			}
			if (!eventLogService.isEventProcessedForCusip(params.cusip)) {
				if(dispatchService.collapsePool(params.poolid, params.cusip, params.poolType)) {
					flash.message = message(code: 'Collapse.controller.collapse.success', args: [poolErrorField])
				} else {
					flash.error = message(code: 'Collapse.controller.collapse.fail', args: [poolErrorField])
				}
			} else {
				flash.error = message(code: 'Collapse.controller.collapse.duplicate', args: [params.poolid])
			}
		} else {
			flash.error = message(code: 'Collapse.controller.collapse.fail')
		}
		log.info("Time taken for collapse() : " + (System.currentTimeMillis() - start)+ " ms")
		redirect action: "search", params:params
	}


	private def generateModel(def m, def keys = grailsApplication.config.com.freddiemac.searchpool.result.elements) {
		long start = System.currentTimeMillis();
		def mm = []
		keys.each {
			mm.add(new PropContainer(key: it, value: PropertyRetriever.getProp(it, m)))
		}
		log.info("Time taken for generateModel() : " + (System.currentTimeMillis() - start)+ " ms")
		return mm
	}

	def dissolve() {
		if(eventLogService.isEventProcessedForCusip(params.cusip)) {
			flash.error = message(code: 'dissolve.pool.alreadydone', args: [params.poolid])
			render view : '/pool/dissolvesearch', model : [poolSearch: new PoolSearch(cusipIdentifier: params.cusipIdentifer, poolNumber: params.poolNumber)]
			return
		}

		def m = searchService.searchPool(params.cusip, params.poolid)
		if(!m.success) {
			flash.error = message(code: 'dissolve.pool.notfound', args: [params.poolid])
			render view : '/pool/dissolvesearch', model : [poolSearch: new PoolSearch(cusipIdentifier: params.cusipIdentifer, params.poolNumber)]
			return
		}

		def dissolveKeys = grailsApplication.config.com.freddiemac.onedotfive.fields
		def map = [:]
		dissolveKeys.each { String key ->
			String modifiedKey = key.replace(".", "_")
			String val  = params.get(key)
			if(!val) {
				log.info("couldnt get value from params for " + key)
				val = PropertyRetriever.getProp(key, m.result)
			}
			map.put(modifiedKey, val)
		}
		def isWireSenderExists = false
		def isWireReceiverExists = false
		WireInstructions.list().each{
			if (it.wireInstructionsName.equalsIgnoreCase(params.Sender.Organization.OrganizationName)){
				isWireSenderExists = true
			} else if (it.wireInstructionsName.equalsIgnoreCase(params.Receiver.Organization.OrganizationName)){
				isWireReceiverExists = true
			}
		}

		if(dispatchService.dissolveSecurity(params.poolid, params.cusip, map)) {
			flash.message = message(code:"dissolve.pool.success", args: [params.poolid])
			if (!isWireSenderExists)
				wireInstructionsService.saveWireInstructions(params.Sender.Organization.OrganizationName,params.Sender.FinancialInstitution.ABARoutingAndTransitIdentifier, params.Sender.FinancialInstitutionAccount.FinancialInstitutionAccountSubaccountName, params.Sender.Organization.OrganizationName, params.Sender.FinancialInstitution.FinancialInstitutionTelegraphicAbbreviationName)
			if (!isWireReceiverExists)
				wireInstructionsService.saveWireInstructions(params.Receiver.Organization.OrganizationName,params.Receiver.FinancialInstitution.ABARoutingAndTransitIdentifier, params.Receiver.FinancialInstitutionAccount.FinancialInstitutionAccountSubaccountName, params.Receiver.Organization.OrganizationName, params.Receiver.FinancialInstitution.FinancialInstitutionTelegraphicAbbreviationName)
		} else {
			flash.error = message(code: "dissolve.pool.fail", args: [params.poolid])
		}
		render view: 'dissolvesearch', model: ['result': generateModel(m.result), 'fieldsDissolve': generateModel(m.result, grailsApplication.config.com.freddiemac.searchpool.result.dissolve.elements), isDissolved:eventLogService.isEventProcessedForCusip(params.cusip), poolid: params.poolid, cusip: params.cusip,  poolSearch:new PoolSearch(cusipIdentifier: params.cusipIdentifer, poolNumber:  params.poolNumber), wireSender: WireInstructions.findByWireInstructionsName("Freddie Mac"), wireReceiver: WireInstructions.findByWireInstructionsName("Last National Bank and Trust"), savedWireList: WireInstructions.list()]
	}
}
