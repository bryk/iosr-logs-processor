#!/bin/bash
# Runs zookeper server

ZOO_CONF=conf/zoo.conf

echo "Starting..."
(
  sudo zookeeper-server stop
  cd `dirname $0`/..;
  sudo /usr/java/jdk1.6.0_32/bin/java -Dzookeeper.datadir.autocreate=false -Dzookeeper.log.dir="/var/log/zookeeper" -Dzookeeper.root.logger="INFO,ROLLINGFILE" -cp "/usr/lib/zookeeper/bin/../build/classes:/usr/lib/zookeeper/bin/../build/lib/*.jar:/usr/lib/zookeeper/bin/../lib/slf4j-log4j12-1.6.1.jar:/usr/lib/zookeeper/bin/../lib/slf4j-api-1.6.1.jar:/usr/lib/zookeeper/bin/../lib/netty-3.2.2.Final.jar:/usr/lib/zookeeper/bin/../lib/log4j-1.2.15.jar:/usr/lib/zookeeper/bin/../lib/jline-0.9.94.jar:/usr/lib/zookeeper/bin/../zookeeper-3.4.5-cdh4.6.0.jar:/usr/lib/zookeeper/bin/../src/java/lib/*.jar:/etc/zookeeper/conf::/etc/zookeeper/conf:/usr/lib/zookeeper/*:/usr/lib/zookeeper/lib/*" -Dzookeeper.log.threshold=INFO -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.local.only=false org.apache.zookeeper.server.quorum.QuorumPeerMain "${ZOO_CONF}" 
)
echo "Stopped..."

