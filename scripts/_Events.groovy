import grails.util.Environment

unJarTask = {jarName, includePattern ->

    def localDir = "$stagingDir/tmpStandlone/"
    ant.mkdir(dir: localDir)

//copy all dependcies to this dir

    grailsSettings.runtimeDependencies?.each { File f ->
        ant.copy(todir: "${localDir}") {
            fileset(dir: f.parent, includes: f.name)
        }
    }

    grailsSettings.providedDependencies?.each { File f ->
        ant.copy(todir: "${localDir}") {
            fileset(dir: f.parent, includes: f.name)
        }
    }


    grailsSettings.compileDependencies?.each { File f ->
        ant.copy(todir: "${localDir}") {
            fileset(dir: f.parent, includes: f.name)
        }
    }

    grailsSettings.buildDependencies?.each { File f ->
        ant.copy(todir: "${localDir}") {
            fileset(dir: f.parent, includes: f.name)
        }
    }




    new File("${localDir}").listFiles().each {File file ->
        if (file.getName().endsWith(".jar")) {
            ant.unjar(src: "${localDir}/${file.getName()}", dest: "${localDir}")
        }
    }


    ant.move(todir: "$stagingDir") {
        fileset(dir: "${localDir}") {
	    exclude(name: "**/*.jar")
            include(name: "$includePattern/**")
            exclude(name: "META-INF/**")
        }
    }
    ant.delete(dir: "${localDir}")
}
eventCreateWarStart = {warName, stagingDir ->
    if (Environment.current.name == "standalone") {

        ant.mkdir(dir: "$stagingDir/${grailsAppName}/")
        ant.move(file: "$stagingDir/WEB-INF/classes/standalone/Start.class", todir: "$stagingDir/standalone/")
        unJarTask("*", "*")
    }
}


eventCreateWarEnd = {warName, stagingDir ->
    if (Environment.current.name == "standalone") {

        def libPath = ""
        File f = new File("$stagingDir/WEB-INF/lib")
        if (f.exists()) {
            f.eachFile { libPath += "WEB-INF/lib/${it.name} " }
        }
        ant.jar(destfile: warName, update: true) {
            manifest { attribute(name: "Main-Class", value: "standalone.Start")}
            manifest { attribute(name: "Class-Path", value: "$libPath") }
        }
    }
}
