package com.freddiemac

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification
import grails.test.mixin.TestFor

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(SearchService)
class SearchServiceSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "Service_Positive"() {
		
	
		when:
		def result = service.searchPool("CUSIP1111")

		then:
		assert result["success"] == true

    }
	
	
	void "Service_negative"() {
		
	
		when:
		def result = service.searchPool("dfasdfasd")
		

		then:
		assert result["success"] == false
		assert result["message"] == 'Pool Details Unavailable'
			

	}
	
	void "Cusip_null"() {
		
	
		when:
		def result = service.searchPool(null)
		println result

		then:
		assert result["success"] == false
		assert result["message"] == 'CUSIP ID was null, please correct value'
			
}
	
	void "Cusip_Blank"() {
		
	
		when:
		def result = service.searchPool()
		println result

		then:
		assert result["success"] == false
		assert result["message"] == 'CUSIP ID was null, please correct value'
			
}

}
