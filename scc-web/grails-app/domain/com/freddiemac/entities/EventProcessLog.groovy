package com.freddiemac.entities

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
	

}

enum Status {
	INITIALIZED,DONE,CANCELLED
}
enum EventType {
	DISSOLVE, COLLAPSE
}
