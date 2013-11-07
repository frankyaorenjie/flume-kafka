package com.vipshop.flume;

import java.io.IOException;
import java.util.Properties;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.javaapi.producer.Producer;
import kafka.producer.ProducerConfig;

import org.apache.flume.Context;
import org.apache.zookeeper.KeeperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vipshop.flume.config.KafkaSinkConfig;
import com.vipshop.flume.config.KafkaSourceConfig;


public class KafkaUtil {
	private static final Logger log = LoggerFactory.getLogger(KafkaUtil.class);
	/**
	 * @param args
	 */
	public static Producer<String, String> getProducer(Context context) {
		log.info(context.toString());
		Producer<String, String> producer;
		Properties props = new Properties();
		props.put("serializer.class", "kafka.serializer.StringEncoder");
		props.put("zk.connect", KafkaSinkConfig.getZkConnect(context));
		props.put("producer.type", "async");
		props.put("batch.size", KafkaSinkConfig.getBatchSize(context));
		
		props.put("queue.size", "1000000");
		
		producer = new Producer<String, String>(new ProducerConfig(props));
		log.debug("-----------return producer");
		return producer;
	}
	public static ConsumerConnector getConsumer(Context context) throws IOException, KeeperException, InterruptedException {
		log.info(context.toString());
		Properties props = new Properties();
		props.put("zk.connect", KafkaSourceConfig.getZkConnect(context));
		props.put("zk.sessiontimeout.ms", "60000");
//		props.put("fetch.size", String.valueOf(Integer.parseInt((getBatchSize(context))) * 300 * 1024));
		props.put("groupid", KafkaSourceConfig.getGroupId(context));
		props.put("autocommit.enable", "false");
		props.put("queuedchunks.max", "1000");
		props.put("autooffset.reset", "largest");
		props.put("socket.buffersize", "10240000");
		props.put("socket.timeout.ms", "60000");
		ConsumerConfig consumerConfig = new ConsumerConfig(props);
		ConsumerConnector consumer = Consumer.createJavaConsumerConnector(consumerConfig);
		log.debug("-----------return consumer");
		return consumer;
	}
}

















