#!/bin/bash
# Initializes test data

(
  cd `dirname $0`;
  DIR="/user/cloudera/kafka"
  #DIR="/tmp/testdb"
  DATA="../conf/test_data.csv"
  TABLE_NAME="iosr"

  #hadoop fs -rm -r -f $DIR
  #hadoop fs -mkdir $DIR
  #hadoop fs -put $DATA $DIR

  BEE="beeline -u jdbc:hive2://localhost:10000 -n cloudera -p cloudera -d org.apache.hive.jdbc.HiveDriver"

  $BEE -e "DROP TABLE ${TABLE_NAME};"
  $BEE -e "create external table ${TABLE_NAME} (timestamp_millis TIMESTAMP COMMENT \"Timestamp of this log entry\", class STRING COMMENT \"Class issuing log\", level STRING COMMENT \"Log level\", message STRING COMMENT \"Log message\") row format delimited fields terminated by \"\001\" stored as textfile location \"${DIR}\";";
)

