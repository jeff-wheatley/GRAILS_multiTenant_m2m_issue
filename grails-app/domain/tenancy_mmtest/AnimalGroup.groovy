package tenancy_mmtest

import grails.gorm.*

class AnimalGroup implements MultiTenant<AnimalGroup> {
    String name
    String farm

    static hasMany = [ animals: Animal ]

    static constraints = {
name  blank: false
farm  blank: false
    }

    static mapping = {
        tenantId name: 'farm'
    }

	@Override
	String toString() { "$name @ $farm" }
    }
