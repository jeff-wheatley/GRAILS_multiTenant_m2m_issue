package tenancy_mmtest

import grails.gorm.multitenancy.CurrentTenant
import grails.gorm.transactions.Transactional
import grails.gorm.services.Service

@Service(AnimalGroup)
@CurrentTenant
abstract class AnimalGroupService {

    abstract AnimalGroup find(Long id)

    abstract List<AnimalGroup> list(Map args)

    abstract Long count()

    abstract void delete(Serializable id)

    abstract AnimalGroup save(String name)
    abstract AnimalGroup save(AnimalGroup animalGroup)

    @Transactional
    AnimalGroup update( Serializable id, String name ) {
        AnimalGroup group = find(id)
        if (group != null) {
            group.name = name
            group.save(failOnError:true)
        }
        group
    }

}