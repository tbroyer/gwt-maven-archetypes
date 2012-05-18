gwt-maven-archetypes
====================

This project contains Maven archetypes for modular GWT projects.

How to use
----------

In order to generate a modular GWT project from the available archetypes, first you need to clone and install this project locally:

    git clone https://github.com/tbroyer/gwt-maven-archetypes.git
    cd gwt-maven-archetypes && maven clean install

### Generate a project

    mvn archetype:generate \
       -DarchetypeGroupId=net.ltgt.gwt.archetypes \
       -DarchetypeArtifactId=<artifactId> \
       -DarchetypeVersion=1.0-SNAPSHOT

where the available `<artifactIds>` are:

* `modular-webapp`
* `modular-requestfactory`
*  guice-rf-activities

### Start the development mode

Change directory to your generated project and issue the following commands:

1. `mvn clean install -Ddraft`
2. In one terminal window: `cd server && mvn jetty:start`
3. In another terminal window: `cd client && mvn gwt:run -Ddev`

### Profiles

There are two profiles defined in the POM file of client module:

* `dev` is to speed-up development with gwt:run and jetty:start by not requiring a restart when a change to shared is made
* `draft` is to speed-up GWT compilation and will only compile (by default) for WebKit-based browsers (Safari, Chrome)

To activate `dev` or `draft` you have to provide the `-Ddev` or `-Ddraft` system properties respectively.
