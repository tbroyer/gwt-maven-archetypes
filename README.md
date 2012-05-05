gwt-maven-archetypes
====================

description

How to use
----------

In order to generate a modular GWT project from the available archetypes, first you need to clone and install this project locally:

    git clone https://github.com/tbroyer/gwt-maven-archetypes.git
    cd gwt-maven-archetypes && maven clean install

### Generate a project

    mvn archetype:generate \
       -DarchetypeGroupId=net.ltgt.gwt \
       -DarchetypeArtifactId=<artifactId> \
       -DarchetypeVersion=1.0-SNAPSHOT

where the available `<artifactIds>` are:

* `gwt-maven-archetypes-modular-webapp`
* `gwt-maven-archetypes-modular-requestfactory`


### Start the development mode

Change directory to your generated project and issue the following commands:

1. `mvn clean install -Ddraft`
2. In one terminal window: `cd server && mvn jetty:start -Ddev`
3. In another terminal window: `cd client && mvn gwt:run -Ddev`
