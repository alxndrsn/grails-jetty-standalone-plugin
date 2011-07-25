import grails.util.Environment

grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
//grails.project.war.file = "target/${appName}-${appVersion}.war"
grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // uncomment to disable ehcache
        // excludes 'ehcache'
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    repositories {
        grailsPlugins()
        grailsHome()
        grailsCentral()
    }
    dependencies {
        //we only want to do this in the standalone enviorement
        def jettyVersion = "7.3.1.v20110307"
        build("org.eclipse.jetty:jetty-http:${jettyVersion}",
                "org.eclipse.jetty:jetty-server:${jettyVersion}",
                "org.eclipse.jetty:jetty-webapp:${jettyVersion}",
                "org.eclipse.jetty:jetty-plus:${jettyVersion}",
                "org.eclipse.jetty:jetty-continuation:${jettyVersion}",
                "org.eclipse.jetty:jetty-websocket:${jettyVersion}") {
                    excludes 'commons-el', 'ant', 'sl4j-api', 'sl4j-simple', 'jcl104-over-slf4j', 'xmlParserAPIs'
                    excludes 'mail', 'commons-lang'
                }
        build("org.codehaus.groovy.modules.http-builder:http-builder:0.5.0") {
            		excludes "commons-logging", "xml-apis", "groovy"
        }
    }
}
