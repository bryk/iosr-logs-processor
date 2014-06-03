#!/bin/bash
# Runs logs consumer

java -jar `dirname $0`/../consumer/target/consumer-1.0.0-jar-with-dependencies.jar $@

