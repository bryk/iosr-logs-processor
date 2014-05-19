#!/bin/bash
# Runs logs analyzer

java -jar `dirname $0`/../analyzer/target/analyzer-1.0.0-jar-with-dependencies.jar $@

