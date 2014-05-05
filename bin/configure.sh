#!/bin/bash
# Configures Cloudera VM for the project
KAFKA_URL=https://archive.apache.org/dist/kafka/0.8.1/kafka_2.9.2-0.8.1.tgz

echo "Configuring..."
sudo yum update -y
(
  cd `dirname $0`
  cd ..
  rm -rf downloads/
  mkdir -p downloads/
  wget -O downloads/kafka.tgz ${KAFKA_URL}
  (cd downloads && tar xvfz kafka.tgz)
  mv downloads/kafka_2.9.2-0.8.1/ downloads/kafka
)
echo "Done..."

