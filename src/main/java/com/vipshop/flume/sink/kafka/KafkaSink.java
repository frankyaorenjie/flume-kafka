package com.vipshop.flume.sink.kafka;

import kafka.javaapi.producer.Producer;
import kafka.javaapi.producer.ProducerData;

import org.apache.flume.Channel;
import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.EventDeliveryException;
import org.apache.flume.Transaction;
import org.apache.flume.conf.Configurable;
import org.apache.flume.sink.AbstractSink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vipshop.flume.KafkaUtil;

public class KafkaSink extends AbstractSink implements Configurable{
	private static final Logger log = LoggerFactory.getLogger(KafkaSink.class);
	private String topic;
	private Integer batchSize;
	private Producer producer;
	
	public Status process() throws EventDeliveryException {
		log.debug("proccessing...");
		Status status = Status.READY;
		Channel channel = getChannel();
		Transaction tx = channel.getTransaction();
		tx.begin();
		Event e = channel.take();
		producer.send(new ProducerData<String, String>(this.topic, e.getBody().toString()));
		return null;
	}

	public void configure(Context context) {
		this.topic = KafkaUtil.getTopic(context);
		this.batchSize = Integer.parseInt(KafkaUtil.getBatchSize(context));
		this.producer = KafkaUtil.getProducer(context);
		log.info("Init producer done");
	}

	@Override
	public synchronized void start() {
		super.start();
	}

	@Override
	public synchronized void stop() {
		producer.close();
		super.stop();
	}
}
