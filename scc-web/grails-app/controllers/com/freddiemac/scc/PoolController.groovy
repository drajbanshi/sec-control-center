package com.freddiemac.scc

import com.freddiemac.scc.model.PoolSearch
import com.freddiemac.scc.model.PropContainer
import com.freddiemac.scc.utils.PropertyRetriever

class PoolController {

    def searchService
    def dispatchService
    def grailsApplication
    def eventLogService


    def index() {
        render view: "/pool/index"
    }

    def search(PoolSearch poolSearch) {

        if(poolSearch.hasErrors()) {
            render view: "/pool/index", model: [poolSearch: poolSearch, poolid: params.poolNumber, cusip: params.cusipIdentifier]
            return
        }

        def m = searchService.searchPool(poolSearch.cusipIdentifier, poolSearch.poolNumber)
        if (!m.success) {
            if (m.errorMessage.equals(message(code: 'Collapse.controller.search.error3'))) {
                flash.error =  message(code: 'Collapse.controller.search.error3')
                render view: 'index', model: [poolSearch: poolSearch, poolid: params.poolNumber, cusip: params.cusipIdentifier]
                return
            }
            def poolErrorField = ""
            if (poolSearch.cusipIdentifier) {
                poolSearch.errors.rejectValue('cusipIdentifier', 'Collapse.controller.search.error1') 
                poolErrorField = "cusipIdentifier" 
            } else {
                poolSearch.errors.rejectValue('poolNumber', 'Collapse.controller.search.error2') 
                poolErrorField = "poolNumber"
            }
            render view: 'index', model: [poolSearch: poolSearch, poolid: params.poolNumber, cusip: params.cusipIdentifier, poolErrorField:poolErrorField]
        } else {
            String poolid = poolSearch.poolNumber ?:PropertyRetriever.getProp(grailsApplication.config.com.freddiemac.searchpool.result.poolid, m.result)
            String cusip = poolSearch.cusipIdentifier ?:PropertyRetriever.getProp(grailsApplication.config.com.freddiemac.searchpool.result.cusip, m.result)
            String secIssueDt = PropertyRetriever.getProp(grailsApplication.config.com.freddiemac.searchpool.result.securityissuedate, m.result)
            String secSettleDt = PropertyRetriever.getProp(grailsApplication.config.com.freddiemac.searchpool.result.securitysettledate, m.result)
            String poolType = PropertyRetriever.getProp(grailsApplication.config.com.freddiemac.searchpool.result.pooltype, m.result)
            def isCollapsed = (secIssueDt && !secIssueDt.isEmpty()) || (secSettleDt && !secSettleDt.isEmpty()) || eventLogService.isEventProcessedForCusip(cusip) 
            render view: 'index', model: ['result': generateModel(m.result), isCollapsed:isCollapsed, poolid: poolid, cusip: cusip,  poolSearch:poolSearch, poolType: poolType]
        }
    }


    def collapse() {
        if(params.poolid && params.cusip) {
            if(dispatchService.collapsePool(params.poolid, params.cusip, params.poolType)) {
                flash.message = message(code: 'Collapse.controller.collapse.success')
            } else {
                flash.error = message(code: 'Collapse.controller.collapse.error')
            }
        } else {
            flash.error = message(code: 'Collapse.controller.collapse.fail')
        }
        redirect action: "search", params:params
    }


    private def generateModel(def m) {
        def keys = grailsApplication.config.com.freddiemac.searchpool.result.elements
        def mm = []
        keys.each {
            mm.add(new PropContainer(key: it, value: PropertyRetriever.getProp(it, m)))
        }
        return mm
    }

    def dissolve() {
        //TODO: should be implemented depending on changes and with reference to Dissolve controller
    }
}
