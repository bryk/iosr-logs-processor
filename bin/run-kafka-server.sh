#!/bin/bash
# Runs kafka instances

echo "Starting..."
(
  cd `dirname $0`/..;
  ./downloads/kafka/bin/kafka-server-start.sh downloads/kafka/config/server.properties
)
echo "Stopped..."

