#!/bin/bash

set -eu

readonly VERSION=$(date +%Y.%-m.%-d)

pushd $(dirname $0) &>/dev/null

if [ $# -eq 0 ]; then
  echo "Usage: $0 module1 [module2 ...]" 
  exit 2
fi

declare MODULES=()
for module in "$@"; do
  relmodule=$(realpath --relative-base=. "$module")
  relmodule=${relmodule%/pom.xml}
  if [[ $relmodule = */* ]] || [ ! -d "$relmodule" ] || [ ! -f "$relmodule/pom.xml" ]; then
    echo "ERROR: $module is not a module of the project"
    exit 1
  fi
  MODULES+=("$relmodule")
done
unset relmodule

if [[ -n $(git status --porcelain) ]]; then
  echo "ERROR: Working tree is dirty"
  exit 1
fi

git switch --detach $(git rev-parse --verify HEAD)

for module in "${MODULES[@]}"; do
  mvn versions:set -DgenerateBackupPoms=false -DnewVersion="$VERSION" -pl "$module"
  git add "$module/pom.xml"
done
git commit -m "Prepare release of ${MODULES[*]} $VERSION"

if [[ -n $(git status --porcelain) ]]; then
  echo "ERROR: Working tree is dirty after version updates"
  exit 1
fi

declare TAGS=()
for module in "${MODULES[@]}"; do
  mvn clean verify -pl "$module"
  git tag -a -m "Releasing $module $VERSION" "$module-$VERSION"
  TAGS+=("tag" "$module-$VERSION")
done
mvn clean deploy -Prelease -pl $(IFS=, ; echo "${MODULES[*]}")
git push origin "${TAGS[@]}"

git switch -

popd &>/dev/null
