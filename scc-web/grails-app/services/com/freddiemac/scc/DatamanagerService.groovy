package com.freddiemac.scc

import grails.transaction.Transactional
import groovy.sql.Sql
import groovy.sql.GroovyRowResult

@Transactional(readOnly = true)
class DatamanagerService {

	Sql groovySql

	def findByRequestId = { requestId->
		final String query = '''\
           select id_srce_evnt, name_rqst_type from rqst  
            where id_rqst = '
        '''
		final results = groovySql.rows(query+ requestId +"'")
		results
	}
	
	def findEventPayload(String eventId) {
		String query = "select * from evnt_msg where id_evnt='" + eventId + "'"
		def results = groovySql.rows(query)
		if(results && results.size() > 0) {
			def payload = results[0][4]
			return new XmlSlurper().parseText(payload)
		}
		return null
		
	}
}
