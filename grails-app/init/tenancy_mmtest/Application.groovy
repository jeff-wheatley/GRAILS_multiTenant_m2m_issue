package tenancy_mmtest

import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.ApplicationArguments
import grails.gorm.transactions.Transactional

class Application extends GrailsAutoConfiguration implements ApplicationRunner {
    static void main(String[] args) {
        GrailsApp.run(Application, args)
    }


    @Override
    @Transactional
    void run(ApplicationArguments args) throws Exception {
        Farm.saveAll(
                new Farm(name: 'HCF'),
                new Farm(name: 'JKW')
        )
    }

}