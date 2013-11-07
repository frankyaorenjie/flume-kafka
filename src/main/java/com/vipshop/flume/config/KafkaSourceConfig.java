package com.vipshop.flume.config;

import org.apache.flume.Context;

public class KafkaSourceConfig {
	public static String getGroupId(Context context) {
		return context.getString(KafkaSourceConstants.CONFIG_GROUPID, "groupid");
	}
	public static String getSocketTimeoutMs(Context context) {
		return context.getString(KafkaSourceConstants.CONFIG_SOCKET_TIMEOUT_MS, "30000");
	}
	public static String getSocketBufferSize(Context context) {
		return context.getString(KafkaSourceConstants.CONFIG_SOCKET_BUFFER_SIZE, "65536"); // 64*1024
	}
	public static String getFetchSize(Context context) {
		return context.getString(KafkaSourceConstants.CONFIG_FETCH_SIZE, "307200"); // 300*1024
	}
	public static String getBackOffIncrementMs(Context context) {
		return context.getString(KafkaSourceConstants.CONFIG_BACKOFF_INCREMENT_MS, "1000");
	}
	public static String getQueuedChunksMax(Context context) {
		return context.getString(KafkaSourceConstants.CONFIG_QUEUEDCHUNKS_MAX, "100");
	}
	public static String getAutoCommitEnable(Context context) {
		return context.getString(KafkaSourceConstants.CONFIG_AUTOCOMMIT_ENABLE, "true");
	}
	public static String getAutoCommitIntervalMs(Context context) {
		return context.getString(KafkaSourceConstants.CONFIG_AUTOCOMMIT_INTERVAL_MS, "10000");
	}
	public static String getAutoOffsetReset(Context context) {
		return context.getString(KafkaSourceConstants.CONFIG_AUTOOFFSET_RESET, "smallest");
	}
	public static String getConsumerTimeoutMs(Context context) {
		return context.getString(KafkaSourceConstants.CONFIG_CONSUMER_TIMEOUT_MS, "-1");
	}
	public static String getRebalanceRetriesMax(Context context) {
		return context.getString(KafkaSourceConstants.CONFIG_REBALANCE_RETRIES_MAX, "4");
	}
	public static String getMirrorTopicsWhiteList(Context context) {
		return context.getString(KafkaSourceConstants.CONFIG_MIRROR_TOPICS_WHITELIST, "");
	}
	public static String getMirrorTopicsBlackList(Context context) {
		return context.getString(KafkaSourceConstants.CONFIG_MIRROR_TOPICS_BLACKLIST, "");
	}
	public static String getMirrorConsumerNumThreads(Context context) {
		return context.getString(KafkaSourceConstants.CONFIG_MIRROR_CONSUMER_NUMTHREADS, "4");
	}
	public static String getBrokerList(Context context) {
		return context.getString(KafkaSourceConstants.CONFIG_BROKER_LIST, null);
	}
	public static String getZkConnect(Context context) {
		return context.getString(KafkaSourceConstants.CONFIG_ZK_CONNECT, null);
	}
	public static String getTopic(Context context) {
		return context.getString(KafkaSourceConstants.CONFIG_TOPIC);
	}

}
