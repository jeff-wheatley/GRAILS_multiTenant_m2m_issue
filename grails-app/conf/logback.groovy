import grails.util.BuildSettings
import grails.util.Environment
import org.springframework.boot.logging.logback.ColorConverter
import org.springframework.boot.logging.logback.WhitespaceThrowableProxyConverter

import java.nio.charset.Charset

conversionRule 'clr', ColorConverter
conversionRule 'wex', WhitespaceThrowableProxyConverter

// See http://logback.qos.ch/manual/groovy.html for details on configuration
def basicPattern = "%level %logger - %msg%n "
def defaultPattern = " %level %logger - %msg%n"

// For production, set the log directory in ~home/ls/logs, otherwise, use the setting from BuildSettings
def targetDir = BuildSettings.TARGET_DIR
def grails_env = System.getProperty("grails.env")
println("Configuring logging for $grails_env environment")
if(grails_env == "prod") {
    def userHome = System.getProperty("user.home")
    targetDir = "$userHome/ls/logs"
}
println("Setting the log directory to $targetDir")

appender('STDOUT', ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        charset = Charset.forName('UTF-8')
        pattern = basicPattern
    }
}

appender('WARNFILE', FileAppender) {
    file = "${targetDir}/warn.log"
    append = true
    encoder(PatternLayoutEncoder) {
        pattern = defaultPattern
    }
}

appender('DEBUGFILE', FileAppender) {
    file = "${targetDir}/debug.log"
    append = true
    encoder(PatternLayoutEncoder) {
        pattern = defaultPattern
    }
}

logger 'org.hibernate.type.descriptor.sql.BasicBinder', TRACE, ['DEBUGFILE']
logger 'org.hibernate.SQL', TRACE, ['DEBUGFILE']
logger 'tenancy_mmtest', debug, ['DEBUGFILE']

root(warn, ['WARNFILE'])

println("Log configuration complete")
