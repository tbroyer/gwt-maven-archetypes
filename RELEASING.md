Each module can (and should) be released independently.

The process to cut a release is as follows:

 1. Check that the working directory is clean

 2. Switch to a 'detached HEAD' (or temporary branch)

        git checkout $(git rev-parse --verify HEAD)

 3. Update the version in the POM to a non-SNAPSHOT and commit it.  
    Do **not** update the version of the root POM as we don't want to deploy it anyway.

        git commit -m "Prepare release of ${module} ${version}" -- ${module}/pom.xml

 4. Make a last check that everything's OK

        JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64 mvn clean verify -pl ${module}

    (note the absence of `-am` in the command above)

 5. Create an annotated tag for the commit:

        git tag -a -m "Releasing ${module} ${version}" ${module}-${version}

 6. Deploy the module to The Central Repository

        JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64 mvn clean deploy -Prelease -pl ${module}

    (note the absence of `-am` in the command above, here again)

 7. Push the tag to GitHub

        git push origin tag ${module}-${version}

 8. Switch back to `master`; delete the temporary branch if needed.
