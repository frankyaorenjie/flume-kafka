package com.vipshop.flume.config;

public class KafkaSinkConstants {

	/**
	 * @param args
	 */
	public final static String CONFIG_PRODUCER_TYPE = "producer.type";
	public final static String CONFIG_BROKER_LIST = "broker.list";
	public final static String CONFIG_ZK_CONNECT = "zk.connect";
	public final static String CONFIG_BUFFER_SIZE = "buffer.size";
	public final static String CONFIG_CONNECT_TIMEOUT_MS = "connect.timeout.ms";
	public final static String CONFIG_SOCKET_TIMEOUT_MS = "socket.timeout.ms";
	public final static String CONFIG_RECONNECT_INTERVAL = "reconnect.interval";
	public final static String CONFIG_RECONNECT_TIME_INTERVAL_MS = "reconnect.time.interval.ms";
	public final static String CONFIG_MAX_MESSAGE_SIZE = "max.message.size";
	public final static String CONFIG_COMPRESSION_CODEC = "compression.codec";
	public final static String CONFIG_COMPRESSED_TOPICS = "compressed.topics";
	public final static String CONFIG_ZK_READ_NUM_RETRIES = "zk.read.num.retries";
	// Options for Async Producers
	public final static String CONFIG_QUEUE_TIME = "queue.time";
	public final static String CONFIG_QUEUE_SIZE = "queue.size";
	public final static String CONFIG_BATCH_SIZE = "batch.size";
	public final static String CONFIG_TOPIC = "topic";
}
