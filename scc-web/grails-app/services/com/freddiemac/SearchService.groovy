package com.freddiemac

import grails.transaction.Transactional

@Transactional
class SearchService {

    def searchPool(String cusip) {

        withSoap(serviceURL: 'http://www.holidaywebservice.com/Holidays/US/Dates/USHolidayDates.asmx') {
            def response = send(SOAPAction: 'http://www.27seconds.com/Holidays/US/Dates/GetMothersDay') {
                body {
                    GetMothersDay(xmlns: 'http://www.27seconds.com/Holidays/US/Dates/') {
                        year(2011)
                    }
                }
            }
            return response.GetMothersDayResponse.GetMothersDayResult.text()
        }

    }
}
