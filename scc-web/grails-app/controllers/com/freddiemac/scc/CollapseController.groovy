package com.freddiemac.scc

class CollapseController {

    def index() { }
	
	
	def search() {
		if (!params.cusip) {
			flash.error =  "Enter a valid CUSIP ID"
			render view: 'index'
			return
		}
		
	}
	
	def collapse() {
		if (!params.cusip) {
			flash.error =  "Invalid cusip"
			redirect action: 'index'
			return
		}
	}
}
