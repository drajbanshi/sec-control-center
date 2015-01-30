package com.freddiemac

class SearchController {
    def searchService

    def index() {


    }

    def search() {
      def m = searchService.searchPool(params.cusip)
      render view: 'search', model: ['result': m]
    }
}
