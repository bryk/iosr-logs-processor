#!/bin/bash
# Runs logs analyzer

echo "Rebuilding..."
(
  cd `dirname $0`;
  cd ../analizer;
  java -jar target/analyzer-1.0.0-jar-with-dependencies.jar
)
echo "Done..."

