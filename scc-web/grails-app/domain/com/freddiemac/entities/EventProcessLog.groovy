package com.freddiemac.entities

class EventProcessLog {
	
	String cusip
	Status status
	
	Date dateCreated
	Date lastUpdated
	
	 static constraints = {
		cusip blank:false , nullable: false
		status nullable: false 
	}
	

}

enum Status {
	INITIALIZED,DONE,CANCELLED
}
