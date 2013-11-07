package com.vipshop.flume;

public class KafkaConsumerConstants {

	/**
	 * @param args
	 */
	public final static String CONFIG_GROUPID = "groupid";
	public final static String CONFIG_SOCKET_TIMEOUT_MS = "socket.timeout.ms";
	public final static String CONFIG_SOCKET_BUFFER_SIZE = "socket.buffersize"; 
	public final static String CONFIG_FETCH_SIZE = "fetch.size"; 
	public final static String CONFIG_BACKOFF_INCREMENT_MS = "backoff.increment.ms";
	public final static String CONFIG_QUEUEDCHUNKS_MAX = "queuedchunks.max";
	public final static String CONFIG_AUTOCOMMIT_ENABLE = "autocommit.enable";
	public final static String CONFIG_AUTOCOMMIT_INTERVAL_MS = "autocommit.interval.ms";
	public final static String CONFIG_AUTOOFFSET_RESET = "autooffset.rest";
	public final static String CONFIG_CONSUMER_TIMEOUT_MS = "consumer.timeout.ms";
	public final static String CONFIG_REBALANCE_RETRIES_MAX = "rebalance.retries.max";
	public final static String CONFIG_MIRROR_TOPICS_WHITELIST = "mirror.topics.whitelist";
	public final static String CONFIG_MIRROR_TOPICS_BLACKLIST = "mirror.topics.blacklist";
	public final static String CONFIG_MIRROR_CONSUMER_NUMTHREADS = "mirror.consumer.numthreads";
}
