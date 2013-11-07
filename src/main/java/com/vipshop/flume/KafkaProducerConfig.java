package com.vipshop.flume;

import org.apache.flume.Context;

public class KafkaProducerConfig {
	public static String getProducerType(Context context) {
		return context.getString(KafkaProducerConstants.CONFIG_ZK_CONNECT);
	}
	public static String getBrokerList(Context context) {
		return context.getString(KafkaProducerConstants.CONFIG_ZK_CONNECT);
	}
	public static String getZkConnect(Context context) {
		return context.getString(KafkaProducerConstants.CONFIG_ZK_CONNECT);
	}
	public static String getBufferSize(Context context) {
		return context.getString(KafkaProducerConstants.CONFIG_ZK_CONNECT);
	}
	public static String getConnectTimeoutMs(Context context) {
		return context.getString(KafkaProducerConstants.CONFIG_ZK_CONNECT);
	}
	public static String getSocketTimeoutMs(Context context) {
		return context.getString(KafkaProducerConstants.CONFIG_ZK_CONNECT);
	}
	public static String getReconnectInterval(Context context) {
		return context.getString(KafkaProducerConstants.CONFIG_ZK_CONNECT);
	}
	public static String getReconnectTimeIntervalMs(Context context) {
		return context.getString(KafkaProducerConstants.CONFIG_ZK_CONNECT);
	}
	public static String getMaxMessageSize(Context context) {
		return context.getString(KafkaProducerConstants.CONFIG_ZK_CONNECT);
	}
	public static String getCompressionCodec(Context context) {
		return context.getString(KafkaProducerConstants.CONFIG_ZK_CONNECT);
	}
	public static String getCompressedTopics(Context context) {
		return context.getString(KafkaProducerConstants.CONFIG_ZK_CONNECT);
	}
	public static String getZkReadNumRetries(Context context) {
		return context.getString(KafkaProducerConstants.CONFIG_ZK_CONNECT);
	}
	public static String getQueueTime(Context context) {
		return context.getString(KafkaProducerConstants.CONFIG_ZK_CONNECT);
	}
	public static String getQueueSize(Context context) {
		return context.getString(KafkaProducerConstants.CONFIG_ZK_CONNECT);
	}
	public static String getBatchSize(Context context) {
		return context.getString(KafkaProducerConstants.CONFIG_ZK_CONNECT);
	}

}
