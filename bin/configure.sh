#!/bin/bash
# Configures Cloudera VM for the project

echo "Configuring..."
sudo yum update -y
(
  cd `dirname $0`
  cd ..
  rm -rf downloads/
  mkdir -p downloads/
  wget -O downloads/kafka.tgz http://www.eu.apache.org/dist/kafka/0.8.1/kafka_2.9.2-0.8.1.tgz
  (cd downloads && tar xvfz kafka.tgz)
  mv downloads/kafka_2.9.2-0.8.1/ downloads/kafka
)
echo "Done..."

