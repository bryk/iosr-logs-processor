#!/bin/bash
# Runs logs producer

(
  cd "`dirname $0`/..";
  java -jar generator/target/*-with-dependencies.jar $@
)

