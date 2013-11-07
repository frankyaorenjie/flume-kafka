package com.vipshop.flume;

import org.apache.flume.Context;

public class KafkaProducerConfig {
	public static String getGroupId(Context context) {
		return context.getString(KafkaProducerConstants.CONFIG_ZK_CONNECT);
	}
	public static String getSocketTimeoutMs(Context context) {
		return context.getString(KafkaProducerConstants.CONFIG_ZK_CONNECT);
	}
	public static String getSocketBufferSize(Context context) {
		return context.getString(KafkaProducerConstants.CONFIG_ZK_CONNECT);
	}
	public static String getFetchSize(Context context) {
		return context.getString(KafkaProducerConstants.CONFIG_ZK_CONNECT);
	}
	public static String getBackOffIncrementMs(Context context) {
		return context.getString(KafkaProducerConstants.CONFIG_ZK_CONNECT);
	}
	public static String getQueuedChunksMax(Context context) {
		return context.getString(KafkaProducerConstants.CONFIG_ZK_CONNECT);
	}
	public static String getAutoCommitEnable(Context context) {
		return context.getString(KafkaProducerConstants.CONFIG_ZK_CONNECT);
	}
	public static String getAutoCommitIntervalMs(Context context) {
		return context.getString(KafkaProducerConstants.CONFIG_ZK_CONNECT);
	}
	public static String getAutoOffsetReset(Context context) {
		return context.getString(KafkaProducerConstants.CONFIG_ZK_CONNECT);
	}
	public static String getConsumerTimeoutMs(Context context) {
		return context.getString(KafkaProducerConstants.CONFIG_ZK_CONNECT);
	}
	public static String getRebalanceRetriesMax(Context context) {
		return context.getString(KafkaProducerConstants.CONFIG_ZK_CONNECT);
	}
	public static String getMirrorTopicsWhiteList(Context context) {
		return context.getString(KafkaProducerConstants.CONFIG_ZK_CONNECT);
	}
	public static String getMirrorTopicsBlackList(Context context) {
		return context.getString(KafkaProducerConstants.CONFIG_ZK_CONNECT);
	}
	public static String getMirrorConsumerNumThreads(Context context) {
		return context.getString(KafkaProducerConstants.CONFIG_ZK_CONNECT);
	}

}
