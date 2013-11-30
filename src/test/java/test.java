
import java.util.Map;

import org.apache.flume.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class test {

	public static void main(String[] args) {
		Logger log = LoggerFactory.getLogger(test.class);
		// TODO Auto-generated method stub
		Context context = new Context();
		context.put("name", "frank");
		context.put("age", "24");
		context.put("sex", "male");
		String contextString = context.toString();
		for(final String kv : contextString.substring(14,contextString.length()-3).split(", ")) {
			String k = kv.trim().split("=")[0];
			String v = kv.trim().split("=")[1];
			log.info("Parse Parames: " + k + "=" + v);
			if (!k.equals("type") && !k.equals("channel")) {
				log.info("k={},v={}",k,v);
			}
		}
		Map<String, String> contextMap = context.getParameters();
		System.out.println(contextMap.keySet());
	}

}
