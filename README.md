gwt-maven-archetypes
====================

This project contains Maven archetypes for modular GWT projects.

[![Build Status](https://secure.travis-ci.org/tbroyer/gwt-maven-archetypes.png?branch=master)](http://travis-ci.org/tbroyer/gwt-maven-archetypes)

How to use
----------

### Generate a project

    mvn archetype:generate \
       -DarchetypeCatalog=https://oss.sonatype.org/content/repositories/snapshots/ \
       -DarchetypeGroupId=net.ltgt.gwt.archetypes \
       -DarchetypeArtifactId=<artifactId> \
       -DarchetypeVersion=1.0-SNAPSHOT

where the available `<artifactIds>` are:

* `modular-webapp`
* `modular-requestfactory`
* `guice-rf-activities`

This uses the snapshot deployed to Sonatype OSS. Alternatively, and/or if you want to
hack on / contribute to the archetypes, you can clone and install the project locally:

    git clone https://github.com/tbroyer/gwt-maven-archetypes.git
    cd gwt-maven-archetypes && mvn clean install

You'll then use the `mvn archetype:generate` command from above, except for the
`-DarchetypeCatalog` argument which you'll remove, as you now want to use your local
catalog.

Note that tests are sensitive to line endings, so if you're on Windows make sure
your clone has Windows (CRLF) end of lines. In case it's not enough, you can simply
delete the `src/test` folder thus bypassing tests (provided the tests pass on the
continuous integration platform, it's not a big _risk_).


### Use SuperDevMode

Change directory to your generated project and issue the following commands:

1. `mvn clean install -Dgwt.draftCompile`
2. In one terminal window: `cd *-client && mvn gwt:run-codeserver -Ddev`
3. In another terminal window: `mvn tomcat7:run -Ddev`

The same is available with `tomcat6` instead of `tomcat7`.

Or if you'd rather use Jetty than Tomcat, use `cd *-server && mvn jetty:start -Ddev` instead of `mvn tomcat7:run`.

Note that you only need to `install` once so that `gwt:run-codeserver` and `jetty:start`
can find the other modules. This is currently needed because neither `gwt:run`
nor `jetty:start` support running in reactor builds, contrary to `tomcat7:run`.

The `-Dgwt.draftCompile` in the first step is not required, it's only to speed up the
GWT compilation by disabling optimizations.

### Start the development mode

This is similar to using SuperDevMode, except you can use `-Dgwt.compiler.skip`
instead of `-Dgwt.draftCompile` to speed up the first step (it only has to be done once
though so it's probably no big deal), and more importantly you'll use `mvn gwt:run`
instead of `mvn gwt:run-codeserver`.

Steps therefore become:

1. `mvn clean install -Dgwt.compiler.skip`
2. In one terminal window: `cd *-client && mvn gwt:run -Ddev`
3. In another terminal window: `mvn tomcat7:run -Ddev`


### Profiles

There's a special profile defined in the POM file of client and server modules:
`dev`, which is used only when developping. It configures the Tomcat and Jetty
plugins and speeds up development with `gwt:run-codeserver`, `gwt:run` and
`jetty:start` by not requiring a restart when a change to the
`${rootArtifactId}-shared` is made.

To activate the `dev` profile you can provide the `-Ddev` system property, or
use `-Pdev`.

### Productivity tips

When working on the server-side code exclusively, you don't need GWT's DevMode.
You can then compile the GWT app using `mvn package` or `mvn package -Dgwt.draftCompile`
and then `mvn tomcat7:run` or `cd *-server && mvn jetty:start -Ddev`. The
webapp will be redeployed automatically when you change a class (either
compiled by your IDE, or by `mvn compile`) in either the
`${rootArtifactId}-server` or `${rootArtifactId}-shared` module (be careful
though when changing classes in `shared` that you do not break the GWT client
code, particularly when using GWT-RPC).

When working on the client-side code exclusively, to quickly test it in a
browser in production mode, use `mvn package -Dgwt.draftCompile`. You can use
`mvn package -Dgwt.draftCompile -pl :${rootArtifactId}-client -am` while the
Tomcat or Jetty server is running (launched by `mvn tomcat7:run` or
`cd *-server && mvn jetty:start -Ddev`), and then simply hit `F5` in your browser.

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
