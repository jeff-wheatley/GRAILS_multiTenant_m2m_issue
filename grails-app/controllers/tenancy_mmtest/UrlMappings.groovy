package tenancy_mmtest

class UrlMappings {

    static mappings = {
        '/'(controller: 'farm')
        '500' (controller: 'farm', exception: org.grails.datastore.mapping.multitenancy.exceptions.TenantNotFoundException)
        "/farm/$id"(controller: 'farm', action: 'select')
        '/animals'(resources: 'animal')
        '/groups'(resources: 'animalGroup')

        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
