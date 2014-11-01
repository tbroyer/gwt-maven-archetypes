if [ "$TRAVIS_REPO_SLUG" == "tbroyer/gwt-maven-archetypes" ] && \
   [ "$TRAVIS_PULL_REQUEST" == "false" ] && \
   [ "$TRAVIS_BRANCH" == "master" ]; then

  mvn -s ci/settings.xml deploy -DskipTests
fi
