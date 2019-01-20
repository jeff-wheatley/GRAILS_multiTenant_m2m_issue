package tenancy_mmtest

import org.grails.datastore.mapping.multitenancy.web.SessionTenantResolver
import grails.gorm.transactions.ReadOnly
import groovy.transform.CompileStatic

@CompileStatic
class FarmController {

    @ReadOnly
    def index() {
		log.debug("in farm controller, farm has ${Farm.list().size()} items")
        render view: '/index', model: [farms: Farm.list()]
    }

    @ReadOnly
    def select(String id) {
        log.debug("in farm.select with id=$id")
        Farm f = Farm.where {
            name == id
        }.first()
        log.debug("Got farm $f")
        if ( f ) {
            log.debug("Setting tenandResolver.tenandId on session as ${f.name.toLowerCase()}")
            session.setAttribute(SessionTenantResolver.ATTRIBUTE, f.name.toLowerCase())
            redirect controller: 'animalGroup'
        }
        else {
            render status: 404
        }
    }

}
