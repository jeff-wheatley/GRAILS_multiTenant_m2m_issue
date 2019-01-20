package tenancy_mmtest

import grails.validation.ValidationException
import grails.gorm.multitenancy.CurrentTenant
import static org.springframework.http.HttpStatus.*

class AnimalGroupController {

    AnimalGroupService animalGroupService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond animalGroupService.list(params), model:[animalGroupCount: animalGroupService.count()]
    }

    def show(Long id) {
        AnimalGroup group = id ? animalGroupService.find(id) : null
        log.warn("in show action, group=$group")
        respond group
    }

    @CurrentTenant
    def create() {
        respond new AnimalGroup(params)
    }

    def save(AnimalGroup animalGroup) {
        if (animalGroup == null) {
            notFound()
            return
        }

        try {
            animalGroupService.save(animalGroup)
        } catch (ValidationException e) {
            respond animalGroup.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'animalGroup.label', default: 'AnimalGroup'), animalGroup.id])
                redirect animalGroup
            }
            '*' { respond animalGroup, [status: CREATED] }
        }
    }

    @CurrentTenant
    def edit(Long id) {
        show id
    }

    def update(AnimalGroup animalGroup) {
        if (animalGroup == null) {
            notFound()
            return
        }

        try {
            animalGroupService.save(animalGroup)
        } catch (ValidationException e) {
            respond animalGroup.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'animalGroup.label', default: 'AnimalGroup'), animalGroup.id])
                redirect animalGroup
            }
            '*'{ respond animalGroup, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        animalGroupService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'animalGroup.label', default: 'AnimalGroup'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'animalGroup.label', default: 'AnimalGroup'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
