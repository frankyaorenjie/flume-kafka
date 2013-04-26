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
	private Producer<String, String> producer;
	
	public Status process() throws EventDeliveryException {
		log.info("------------------------------------proccessing...");
		Status status = Status.READY;
		Channel channel = getChannel();
		Transaction tx = channel.getTransaction();
		try {
			tx.begin();
			Event e = channel.take();
			if(e==null) {
				tx.rollback();
				return Status.BACKOFF;
			}
			try {
				log.info("----------------------send");
				producer.send(new ProducerData<String, String>(this.topic, e.getBody().toString()));
				tx.commit();
				return Status.READY；
			} catch(Exception ex) {
				log.info("------------------------------------", ex);
				throw ex;
			}
			return Status.READY;
		} catch(Exception e) {
			tx.rollback();
			log.info("---------------------------------", e);
			return Status.BACKOFF;
		} finally {
			tx.close();
		}
	}

	public void configure(Context context) {
		this.topic = KafkaUtil.getTopic(context);
		this.producer = KafkaUtil.getProducer(context);
		log.info("-------Init producer done-----------");
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
