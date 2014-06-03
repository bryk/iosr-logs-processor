#!/bin/bash
# Rebuilds all components needed for the project

echo "Rebuilding..."
(
  cd `dirname $0`;
  cd ../generator;
  mvn clean package;
)
(
  cd `dirname $0`;
  cd ../analyzer;
  mvn clean package;
)
(
  cd `dirname $0`;
  cd ../consumer;
  mvn clean package;
)
echo "Done..."

