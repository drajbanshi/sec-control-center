package com.freddiemac.scc.entities

import groovy.transform.EqualsAndHashCode;

@EqualsAndHashCode
class EventProcessLog {
	
	String cusip
	Status status
	EventType eventType
	
	Date dateCreated
	Date lastUpdated
	
	 static constraints = {
		cusip blank:false , nullable: false, unique:true
		status nullable: false 
		eventType nullable:false 
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
