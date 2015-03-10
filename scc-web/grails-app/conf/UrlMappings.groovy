class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller:"pool" , action:"collapsesearch")
        "500"(view:'/error')
	}
}
