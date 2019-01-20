package tenancy_mmtest

import grails.gorm.*

class Animal implements MultiTenant<Animal> {
    String name
    String farm

    static hasMany = [ groups: AnimalGroup ]

static belongsTo = AnimalGroup // allows cascading saves via AnimalGroup, but not deletes.

    static constraints = {
name  blank: false
farm  blank: false
    }

    static mapping = {
        tenantId name: 'farm'
    }

    }
