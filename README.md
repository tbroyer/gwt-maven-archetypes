gwt-maven-archetypes
====================

This project contains Maven archetypes for modular GWT projects.

How to use
----------

### Generate a project

    mvn archetype:generate \
       -DarchetypeGroupId=net.ltgt.gwt.archetypes \
       -DarchetypeVersion=LATEST \
       -DarchetypeArtifactId=<artifactId>

where the available `<artifactIds>` are:

* `modular-webapp`
* `modular-requestfactory`
* `dagger-guice-rf-activities`

This should use the latest release from the Central Repository.
Alternatively, and/or if you want to hack on / contribute to the archetypes,
you can clone and install the project locally:

    git clone https://github.com/tbroyer/gwt-maven-archetypes.git
    cd gwt-maven-archetypes && mvn clean install

You'll then use the `mvn archetype:generate` command from above, except for the
`-DarchetypeVersion` argument which you'll replace with `HEAD-SNAPSHOT`.


### Start the development mode

Change directory to your generated project and issue the following commands:

1. In one terminal window: `mvn gwt:codeserver -pl *-client -am`
2. In another terminal window: `mvn jetty:run -pl *-server -am -Denv=dev`

Or if you'd rather use Tomcat than Jetty, use `mvn tomcat7:run` instead of `mvn jetty:run`.

Note that the `-pl` and `-am` are not strictly necessary, they just tell Maven not to
build the client module when you're dealing with the server one, and vice versa.


### Profiles

There's a special profile defined in the POM file of server modules:
`env-dev`, which is used only when developping. It configures the Jetty and Tomcat
plugins and removes the dependency on the client module (declared in the `env-prod`
profile, active by default.)

To activate the `env-dev` profile you can provide the `-Denv=dev` system property, or
use `-Penv-dev`.

Compatibility
-------------

To use variable interpolation in parameters during `mvn archetype:generate`,
you need at least version 2.2 of the maven-archetype-plugin. Archetypes use
`${module.toLowerCase()}` as the default value for the `module-short-name`
parameter, so if you don't use version 2.2 or above of the
maven-archetype-plugin, make sure you provide a value and do not use the
default one for that parameter. You can also make sure you use version 2.2 of
the plugin by using `mvn
org.apache.maven.plugins:maven-archetype-plugin:2.2:generate` instead of `mvn
archetype:generate`. It should be noted that variable interpolation also does
not work in M2Eclipse's wizard, despite using recent versions of Maven thus
(probably) a recent-enough version of the maven-archetype-plugin.
