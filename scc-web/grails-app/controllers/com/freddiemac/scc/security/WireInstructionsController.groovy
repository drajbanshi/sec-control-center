package com.freddiemac.scc.security



import static org.springframework.http.HttpStatus.*

class WireInstructionsController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond WireInstructions.list(params), model:[wireInstructionsInstanceCount: WireInstructions.count()]
    }

    def show(WireInstructions wireInstructionsInstance) {
        respond wireInstructionsInstance
    }
    
    def getWireInstructions(String id) {
        WireInstructions wireInstructionsInstance = WireInstructions.get(id)
        respond wireInstructionsInstance
    }    

    def create() {
        respond new WireInstructions(params)
    }

    def save = {
        WireInstructions wireInstructionsInstance = new WireInstructions(params)
        if (wireInstructionsInstance == null) {
            notFound()
            return
        }

        if (wireInstructionsInstance.hasErrors()) {
            respond wireInstructionsInstance.errors, view:'create'
            return
        }
                        
        wireInstructionsInstance.save flush:true
        render view: 'index'
    }

    def edit(WireInstructions wireInstructionsInstance) {
        respond wireInstructionsInstance
    }

    def update(WireInstructions wireInstructionsInstance) {
        if (wireInstructionsInstance == null) {
            notFound()
            return
        }

        if (wireInstructionsInstance.hasErrors()) {
            respond wireInstructionsInstance.errors, view:'edit'
            return
        }

        wireInstructionsInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'WireInstructions.label', default: 'WireInstructions'), wireInstructionsInstance.id])
                redirect wireInstructionsInstance
            }
            '*'{ respond wireInstructionsInstance, [status: OK] }
        }
    }

    def delete(WireInstructions wireInstructionsInstance) {

        if (wireInstructionsInstance == null) {
            notFound()
            return
        }

        wireInstructionsInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'WireInstructions.label', default: 'WireInstructions'), wireInstructionsInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'wireInstructions.label', default: 'WireInstructions'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
