package com.freddiemac.scc.entities

import groovy.transform.EqualsAndHashCode;

@EqualsAndHashCode
class EventProcessLog {
	
	String cusip
	Status status
	EventType eventType
	
	String message
	
	Date dateCreated
	Date lastUpdated
	
	 static constraints = {
		cusip blank:false , nullable: false
		status nullable: false 
		eventType nullable:false 
		message blank:true, nullable: true
	}
	 
	String toString() {
		return "${cusip},${status},${eventType}"
	}
	

}


enum Status {
	INITIALIZED,DONE,CANCELLED
}

enum EventType {
	DISSOLVE, COLLAPSE
}
