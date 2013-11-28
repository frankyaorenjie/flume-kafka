package com.vipshop.flume;

import java.io.IOException;
import java.util.Map;
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

public class KafkaUtil {
	private static final Logger log = LoggerFactory.getLogger(KafkaUtil.class);

	/**
	 * @param args
	 */
	public static String getKafkaConfigParameter(Context context, String key) {
		return context.getString(key);
	}

	public static Properties getKafkaConfigProperties(Context context) {
		Properties props = new Properties();
		Map<String, String> parameters = context.getParameters();
		for (String k : parameters.keySet()) {
			String v = parameters.get(k).trim();
			log.info("Parse Parames: " + k + "=" + v);
			if (!k.equals("type") && !k.equals("channel")) {
				props.put(k, v);
			}
		}
		log.info("PROPS:" + props);
		return props;
	}

	public static Producer<String, String> getProducer(Context context) {
		log.info(context.toString());
		Producer<String, String> producer;
		producer = new Producer<String, String>(new ProducerConfig(
				getKafkaConfigProperties(context)));
		return producer;
	}

	public static ConsumerConnector getConsumer(Context context)
			throws IOException, KeeperException, InterruptedException {
		log.info(context.toString());
		ConsumerConfig consumerConfig = new ConsumerConfig(
				getKafkaConfigProperties(context));
		ConsumerConnector consumer = Consumer
				.createJavaConsumerConnector(consumerConfig);
		return consumer;
	}
}
