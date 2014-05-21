#!/bin/bash
# Runs integration tests for logs analyzer

(
  cd `dirname $0`;
  echo "Seeding test data ..."
  ../bin/init-test-data.sh
  echo "Running test ..."
  rm -rf .tmp/
  mkdir .tmp
  (
    cd .tmp
    ../../bin/run-analyzer.sh -t iosr -u cloudera -p cloudera
    find -name '*.png' -exec sh -c "sha512sum {} > {}.dat" \;
    find -name '*.dat' -exec sh -c "diff {} ../golden/{}" \;
  )
  rm -rf .tmp/
)

