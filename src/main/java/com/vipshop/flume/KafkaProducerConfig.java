package com.vipshop.flume;

import org.apache.flume.Context;

public class KafkaProducerConfig {
	public static String getProducerType(Context context) {
		return context.getString(KafkaProducerConstants.CONFIG_PRODUCER_TYPE, "sync");
	}
	public static String getBrokerList(Context context) {
		return context.getString(KafkaProducerConstants.CONFIG_BROKER_LIST, null);
	}
	public static String getZkConnect(Context context) {
		return context.getString(KafkaProducerConstants.CONFIG_ZK_CONNECT, null);
	}
	public static String getBufferSize(Context context) {
		return context.getString(KafkaProducerConstants.CONFIG_BUFFER_SIZE, "102400");
	}
	public static String getConnectTimeoutMs(Context context) {
		return context.getString(KafkaProducerConstants.CONFIG_CONNECT_TIMEOUT_MS, "5000");
	}
	public static String getSocketTimeoutMs(Context context) {
		return context.getString(KafkaProducerConstants.CONFIG_SOCKET_TIMEOUT_MS, "30000");
	}
	public static String getReconnectInterval(Context context) {
		return context.getString(KafkaProducerConstants.CONFIG_RECONNECT_INTERVAL, "30000");
	}
	public static String getReconnectTimeIntervalMs(Context context) {
		return context.getString(KafkaProducerConstants.CONFIG_RECONNECT_TIME_INTERVAL_MS, "10000000"); // 10*1000*1000
	}
	public static String getMaxMessageSize(Context context) {
		return context.getString(KafkaProducerConstants.CONFIG_MAX_MESSAGE_SIZE, "1000000");
	}
	public static String getCompressionCodec(Context context) {
		return context.getString(KafkaProducerConstants.CONFIG_COMPRESSION_CODEC, "0");
	}
	public static String getCompressedTopics(Context context) {
		return context.getString(KafkaProducerConstants.CONFIG_COMPRESSED_TOPICS, null);
	}
	public static String getZkReadNumRetries(Context context) {
		return context.getString(KafkaProducerConstants.CONFIG_ZK_READ_NUM_RETRIES, "3");
	}
	// For Async
	public static String getQueueTime(Context context) {
		return context.getString(KafkaProducerConstants.CONFIG_QUEUE_TIME, "5000");
	}
	public static String getQueueSize(Context context) {
		return context.getString(KafkaProducerConstants.CONFIG_QUEUE_SIZE, "10000");
	}
	public static String getBatchSize(Context context) {
		return context.getString(KafkaProducerConstants.CONFIG_BATCH_SIZE, "200");
	}

}
