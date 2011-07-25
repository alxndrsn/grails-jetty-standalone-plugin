import org.codehaus.groovy.grails.commons.GrailsApplication
import grails.util.Environment

grailsEnv="standalone"

includeTargets << grailsScript("Init")
includeTargets << grailsScript("War")



target(main: "This script creates a standalone war, which has an integrated jetty7 server") {

    grailsEnv="standalone"

    war()
}

setDefaultTarget(main)
