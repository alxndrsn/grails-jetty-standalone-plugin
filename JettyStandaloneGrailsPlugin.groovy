class JettyStandaloneGrailsPlugin {
    // the plugin version
    def version = "0.2"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "1.3.5 > *"
    // the other plugins this plugin depends on
    def dependsOn = [:]
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
            "grails-app/views/error.gsp"
    ]

    def author = "Gert Wohlgemuth"
    def authorEmail = "berlinguyinca@gmail.com"
    def title = "Jetty7 - Standalone application"
    def description = '''

this plugin allows you to generate standalone application, which can be execute with java -jar APP_NAME.war

Internally a jetty 7 application server is used,
'''

    // URL to the plugin's documentation
    def documentation = "http://grails.org/plugin/jetty-standalone"

    def doWithWebDescriptor = { xml ->
    }

    def doWithSpring = {
    }

    def doWithDynamicMethods = { ctx ->
    }

    def doWithApplicationContext = { applicationContext ->
    }

    def onChange = { event ->
    }

    def onConfigChange = { event ->
    }
}
