package com.lm.jbm.utils;

import java.util.HashMap;
import java.util.Map;

public class UserIPUtil {

	public static Map<String, String> USERIPMAP = null;

	public static String getIP(String userId) {
		if(USERIPMAP == null) {
			init();
		}
		return USERIPMAP.get(userId);
	}
	
	public static synchronized void init() {
		
		
		
		USERIPMAP = new HashMap<String, String>(256);
		USERIPMAP.put("10074056", "219.137.52.207");
		USERIPMAP.put("10074052", "219.136.94.208");
		USERIPMAP.put("153706", "219.137.52.209");
		USERIPMAP.put("100659", "219.136.91.208");
		USERIPMAP.put("276641", "219.137.521.232");
		USERIPMAP.put("103060", "219.136.94.208");
		USERIPMAP.put("102659", "219.137.22.232");
		USERIPMAP.put("101553", "219.136.54.208");
		USERIPMAP.put("101380", "219.137.22.232");
		USERIPMAP.put("103132", "219.136.34.208");
		USERIPMAP.put("102087", "219.137.21.232");
		USERIPMAP.put("100919", "219.136.92.208");
		USERIPMAP.put("100510", "219.137.54.232");
		USERIPMAP.put("102641", "219.136.95.208");
		USERIPMAP.put("102292", "219.137.56.232");
		USERIPMAP.put("101099", "219.136.97.208");
		USERIPMAP.put("103127", "219.137.58.232");
		USERIPMAP.put("101733", "219.136.99.208");
		USERIPMAP.put("102547", "219.137.55.242");
		USERIPMAP.put("103431", "219.136.94.238");
		USERIPMAP.put("104285", "219.137.52.242");
		USERIPMAP.put("103896", "219.136.94.258");
		USERIPMAP.put("102839", "219.137.52.262");
		USERIPMAP.put("104900", "219.136.94.278");
		USERIPMAP.put("103594", "219.137.52.282");
		USERIPMAP.put("101701", "219.136.94.298");
		USERIPMAP.put("104721", "219.137.52.202");
		USERIPMAP.put("103229", "219.136.94.108");
		USERIPMAP.put("100157", "219.137.52.132");
		USERIPMAP.put("104939", "219.136.94.108");
		USERIPMAP.put("100389", "219.137.52.132");
		USERIPMAP.put("101236", "219.136.94.308");
		USERIPMAP.put("100990", "219.137.52.332");
		USERIPMAP.put("103996", "219.136.94.308");
		
		USERIPMAP.put("276641", "219.136.94.208");
		
		USERIPMAP.put("153706", "219.136.94.208");
		
		USERIPMAP.put("", "61.55.114.238");
		USERIPMAP.put("", "61.55.116.202");
		USERIPMAP.put("", "61.182.71.38");
		USERIPMAP.put("", "61.182.78.58");
		USERIPMAP.put("", "59.44.236.2");
		USERIPMAP.put("", "120.11.6.46");
		USERIPMAP.put("", "121.16.16.23");
		USERIPMAP.put("", "121.16.95.84");
		USERIPMAP.put("", "124.238.99.219");
		USERIPMAP.put("", "60.18.220.230");
		USERIPMAP.put("", "60.18.241.206");
		USERIPMAP.put("", "60.18.243.94");
		USERIPMAP.put("", "218.61.170.12");
		USERIPMAP.put("", "218.61.170.130");
		USERIPMAP.put("", "219.149.59.210");
		USERIPMAP.put("", "219.149.61.22");
		USERIPMAP.put("", "60.220.236.71");
		USERIPMAP.put("", "124.164.236.212");
		USERIPMAP.put("", "124.164.246.38");
		USERIPMAP.put("", "202.99.203.118");
		USERIPMAP.put("", "211.142.64.114");
		USERIPMAP.put("", "218.26.26.22");
		USERIPMAP.put("", "218.26.31.18");
		USERIPMAP.put("", "218.21.240.167");
		USERIPMAP.put("", "218.21.242.50");
		USERIPMAP.put("", "218.21.243.2");
		USERIPMAP.put("", "219.147.110.67");
		USERIPMAP.put("", "59.45.180.12");
		USERIPMAP.put("", "59.45.199.24");
		USERIPMAP.put("", "59.45.199.28");
		USERIPMAP.put("", "59.45.199.52");
		USERIPMAP.put("", "59.45.199.69");
		USERIPMAP.put("", "59.45.202.67");
		USERIPMAP.put("", "59.45.207.12");
		USERIPMAP.put("", "59.44.70.238");
		USERIPMAP.put("", "59.44.96.6");
		USERIPMAP.put("", "58.242.184.70");
		USERIPMAP.put("", "60.169.70.2");
		USERIPMAP.put("", "61.190.98.97");
		USERIPMAP.put("", "61.191.136.238");
		USERIPMAP.put("", "61.241.148.45");
		USERIPMAP.put("", "218.22.64.22");
		USERIPMAP.put("", "218.22.64.30");
		USERIPMAP.put("", "218.22.65.4");
		USERIPMAP.put("", "218.22.65.106");
		USERIPMAP.put("", "218.22.70.14");
		USERIPMAP.put("", "218.22.71.34");
		USERIPMAP.put("", "");
		USERIPMAP.put("", "");
		USERIPMAP.put("", "");
		USERIPMAP.put("", "");
		USERIPMAP.put("", "");
		USERIPMAP.put("", "");
		USERIPMAP.put("", "");
		USERIPMAP.put("", "");
		USERIPMAP.put("", "");
		USERIPMAP.put("", "");
		USERIPMAP.put("", "");
		USERIPMAP.put("", "");
		USERIPMAP.put("", "");
		USERIPMAP.put("", "");
		USERIPMAP.put("", "");
		USERIPMAP.put("", "");
		USERIPMAP.put("", "");
		USERIPMAP.put("", "");
		USERIPMAP.put("", "");
		USERIPMAP.put("", "");
		USERIPMAP.put("", "");
		USERIPMAP.put("", "");
		USERIPMAP.put("", "");
		USERIPMAP.put("", "");
		USERIPMAP.put("", "");
		USERIPMAP.put("", "");
		USERIPMAP.put("", "");
		USERIPMAP.put("", "");
		USERIPMAP.put("", "");
		USERIPMAP.put("", "");
		USERIPMAP.put("", "");
		USERIPMAP.put("", "");
		USERIPMAP.put("", "");
		USERIPMAP.put("", "");
		USERIPMAP.put("", "");
	}

}
