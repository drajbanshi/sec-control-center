class UrlMappings {

	static mappings = {
		"/"(controller:"pool" , action:"collapsesearch")
		"/index.gsp"(controller:"pool" , action:"collapsesearch")
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }
       
        "500"(view:'/error')
	}
}
