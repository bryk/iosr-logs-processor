package pl.agh.edu.iosr.logs.reader.kafka;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

public class KafkaHelper {
	
	private String zookeeper = "127.0.0.1:2181";
	private String groupId = "test-consumer-group";
	private String topic;
	
	private static Logger logger = Logger.getLogger(KafkaHelper.class);
	
	Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap;
	ConsumerConnector consumer;
	
	private static ConsumerConfig createConsumerConfig(String a_zookeeper,
			String a_groupId) {
		
		logger.info("Creating consumer group config");
		
		Properties props = new Properties();
		props.put("zookeeper.connect", a_zookeeper);
		props.put("group.id", a_groupId);
		props.put("zookeeper.session.timeout.ms", "400");
		props.put("zookeeper.sync.time.ms", "200");
		props.put("auto.commit.interval.ms", "1000");
		return new ConsumerConfig(props);
	}
	
	public KafkaHelper(String topic) {
		
		logger.info("Creating message streams");
		this.topic = topic;
		consumer = kafka.consumer.Consumer
				.createJavaConsumerConnector(createConsumerConfig(zookeeper,
						groupId));

		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
		topicCountMap.put(topic, new Integer(1));
		consumerMap = consumer
				.createMessageStreams(topicCountMap);
		
		logger.info("Message streams created");
	}
	
	public void shutdown() {
		logger.info("Shutting down kafka");
		if (consumer != null) consumer.shutdown();
	}

	public List<KafkaStream<byte[], byte[]>> getStreams() {
		return consumerMap.get(topic);
	}
	
}
