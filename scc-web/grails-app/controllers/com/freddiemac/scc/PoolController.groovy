package com.freddiemac.scc

import com.freddiemac.scc.model.PoolSearch
import com.freddiemac.scc.model.PropContainer
import com.freddiemac.scc.utils.DateUtils;
import com.freddiemac.scc.utils.PropertyRetriever

class PoolController {

	def searchService
	def dispatchService
	def grailsApplication
	def eventLogService


	def index() {
		render view: "/pool/index"
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
		if(poolSearch.hasErrors()) {
			poolSearch.errors.each {
				if (it.toString().indexOf('cusipIdentifier') > -1) {
					poolErrorField="cusipIdentifier"
				}
				if (it.toString().indexOf('poolNumber') > -1) {
					poolErrorField="poolNumber"
				}
			}
                        if (params.pageFunction.equalsIgnoreCase("Collapse")) 
                            render view: "/pool/collapsesearch", model: [poolSearch: poolSearch, poolid: params.poolNumber, cusip: params.cusipIdentifier, poolErrorField:poolErrorField]
                        else if (params.pageFunction.equalsIgnoreCase("Dissolve")) 
                            render view: "/pool/dissolvesearch", model: [poolSearch: poolSearch, poolid: params.poolNumber, cusip: params.cusipIdentifier, poolErrorField:poolErrorField]
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
                                if (params.pageFunction.equalsIgnoreCase("Collapse")) 
                                    render view: 'collapsesearch', model: [poolSearch: poolSearch, poolid: params.poolNumber, cusip: params.cusipIdentifier, poolErrorField: poolErrorField]
                                else if (params.pageFunction.equalsIgnoreCase("Dissolve")) 
                                    render view: 'dissolvesearch', model: [poolSearch: poolSearch, poolid: params.poolNumber, cusip: params.cusipIdentifier, poolErrorField: poolErrorField]
				return
			}

			if (poolSearch.cusipIdentifier) {
				flash.error =  message(code: 'Collapse.controller.search.error1', args: [poolSearch.cusipIdentifier])
			} else {
				flash.error =  message(code: 'Collapse.controller.search.error2', args: [poolSearch.poolNumber])
			}
                        if (params.pageFunction.equalsIgnoreCase("Collapse")) 
                            render view: 'collapsesearch', model: [poolSearch: poolSearch, poolid: params.poolNumber, cusip: params.cusipIdentifier, poolErrorField:poolErrorField]
                        else if (params.pageFunction.equalsIgnoreCase("Dissolve")) 
                            render view: 'dissolvesearch', model: [poolSearch: poolSearch, poolid: params.poolNumber, cusip: params.cusipIdentifier, poolErrorField:poolErrorField]
		} else {
			String poolid = poolSearch.poolNumber ?:PropertyRetriever.getProp(grailsApplication.config.com.freddiemac.searchpool.result.poolid, m.result)
			String cusip = poolSearch.cusipIdentifier ?:PropertyRetriever.getProp(grailsApplication.config.com.freddiemac.searchpool.result.cusip, m.result)
			String secIssueDt = PropertyRetriever.getProp(grailsApplication.config.com.freddiemac.searchpool.result.securityissuedate, m.result)
			String poolType = PropertyRetriever.getProp(grailsApplication.config.com.freddiemac.searchpool.result.pooltype, m.result)
			def isCollapsed = DateUtils.isPastDate(secIssueDt) || eventLogService.isEventProcessedForCusip(cusip)
                        // Stubbed for now, needs to be replaced with the an actual DB call or properties file
                        def wireSender = ["FinancialInstitution.ABARoutingAndTransitIdentifier":"000101010","FinancialInstitutionAccount.FinancialInstitutionAccountSubaccountName":"Good N Plenty", "Organization.OrganizationName":"Freddie Mac", "FinancialInstitution.FinancialInstitutionTelegraphicAbbreviationName":"FMAC", "FinancialInstitutionAccount.FinancialInstitutionAccountIdentifier":"DLFJKD" ]
                        def wireReceiver= ["FinancialInstitution.ABARoutingAndTransitIdentifier":"000101010","FinancialInstitutionAccount.FinancialInstitutionAccountSubaccountName":"Good N Plenty", "Organization.OrganizationName":"Last National Bank and Trust", "FinancialInstitution.FinancialInstitutionTelegraphicAbbreviationName":"LNBAT", "FinancialInstitutionAccount.FinancialInstitutionAccountIdentifier":"LLSDSD" ]
                        if (params.pageFunction.equalsIgnoreCase("Collapse")) 
                            render view: 'collapsesearch', model: ['result': generateModel(m.result), isCollapsed:isCollapsed, poolid: poolid, cusip: cusip,  poolSearch:poolSearch, poolType: poolType, poolErrorField: poolErrorField,  xfields: grailsApplication.config.com.freddiemac.searchpool.result.xfields]
                        else if (params.pageFunction.equalsIgnoreCase("Dissolve"))     
                            render view: 'dissolvesearch', model: ['result': generateModel(m.result), isCollapsed:isCollapsed, poolid: poolid, cusip: cusip,  poolSearch:poolSearch, poolType: poolType, poolErrorField: poolErrorField,  wireSender: wireSender, wireReceiver: wireReceiver]
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
		//TODO: should be implemented depending on changes and with reference to Dissolve controller
	}
}
