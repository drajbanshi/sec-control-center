package com.freddiemac

import grails.transaction.Transactional
import wslite.soap.SOAPClient
import groovy.io.FileType


class SearchService {

    def searchPool(String cus) {

      /*  SOAPClient client = new SOAPClient('http://172.20.17.7:9999/freddiemac/services/searchload.asmx')
        def response = client.send(SOAPAction:'FindLoan') {
            body {
                FindLoan('xmlns':'http://www.freddiemac.com/search') {
                    cusip('adfadf')
                }
            }
        }
		  //def responseXML = new File('C:/Users/c36429/git/sec-control-center/stub-server/__files/cusp_output.xml').getText('CUSP_1')
		 // return responseXML
		//println responseXML
       
*/		
		def inputfile;
	def list = []
	def isExist
		def dir = new File('C:/Users/c36429/git/sec-control-center/stub-server/__files/')
		dir.eachFileRecurse (FileType.FILES) { file ->
		  list << file
	} 
		  list.each {
			   def filename= it.getName()
			 	   
			   inputfile= cus+".xml"
			   if(filename == inputfile)
			   {
					 isExist = "Available"
				 
			   }
			  }
		  
		  if(isExist!="Available")
		  
		  {	   
			  isExist = "Not available"
			  return isExist
		 }
		  def filePath = "C:/Users/c36429/git/sec-control-center/stub-server/__files/"+inputfile
		  println "filepath : "+filePath
		  def responseXML = new File(filePath).getText('UTF-8')
		  return responseXML
    }
}
