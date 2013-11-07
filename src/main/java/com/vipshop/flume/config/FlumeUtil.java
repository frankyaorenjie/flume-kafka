package com.vipshop.flume.config;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.flume.Context;

public class FlumeUtil {
	public static Map<String, String> getFlumeConfig(Context context) {
		String parameters = context.toString();
		Map<String, String> flumeParameters = Collections.synchronizedMap(new HashMap<String, String>());
		for(final String kv : parameters.substring(13,parameters.length()-2).split(", ")) {
			flumeParameters.put(kv.trim().split("=")[0], kv.trim().split("=")[1]);
		}
		return flumeParameters;
	}
}
