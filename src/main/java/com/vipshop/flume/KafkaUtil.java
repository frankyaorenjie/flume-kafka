package com.vipshop.flume;

import java.util.Properties;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.javaapi.producer.Producer;
import kafka.producer.ProducerConfig;

import org.apache.flume.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KafkaUtil {
	private static final Logger log = LoggerFactory.getLogger(KafkaUtil.class);
	/**
	 * @param args
	 */
	public static String getZkConnect(Context context) {
		return context.getString(KafkaConstants.CONFIG_ZK_CONNECT);
	}
	public static String getTopic(Context context) {
		return context.getString(KafkaConstants.CONFIG_TOPIC);
	}
	public static String getBatchSize(Context context) {
		return context.getString(KafkaConstants.CONFIG_BATCH_SIZE, "200");
	}
	public static String getGroup(Context context) {
		return context.getString(KafkaConstants.CONFIG_GROUP);
	}
	public static Producer<String, String> getProducer(Context context) {
		Producer<String, String> producer;
		Properties props = new Properties();
		props.put("serializer.class", "kafka.serializer.StringEncoder");
		props.put("zk.connect", getZkConnect(context));
		props.put("producer.type", "async");
		props.put("batch.size", getBatchSize(context));
		
		producer = new Producer<String, String>(new ProducerConfig(props));
		log.debug("-----------return producer");
		return producer;
	}
	public static ConsumerConnector getConsumer(Context context) {
		Properties props = new Properties();
		props.put("zk.connect", getZkConnect(context));
		props.put("groupid", getGroup(context));
		props.put("autooffset", "largest");
		props.put("socket.buffersize", "102400000");
		ConsumerConfig consumerConfig = new ConsumerConfig(props);
		ConsumerConnector consumer = Consumer.createJavaConsumerConnector(consumerConfig);
		return consumer;
	}
}

























