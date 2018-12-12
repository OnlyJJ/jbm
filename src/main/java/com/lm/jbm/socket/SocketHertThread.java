package com.lm.jbm.socket;


import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.lm.jbm.service.JmService;
import com.lm.jbm.thread.PeachThread;
import com.lm.jbm.thread.ThreadManager;
import com.lm.jbm.utils.JsonUtil;
import com.lm.jbm.utils.LogUtil;


public class SocketHertThread implements Runnable {
	public static long SEQID;
	public static int COUNT=0;
	public static long getSeqId() {
		SEQID++;
		return SEQID;
	}
	
	public SocketHertThread() {
	}

	public void run() {
		try {
			while(true) {
				JSONObject data = new JSONObject();
				long seqId = getSeqId();
				data.put("seqID", seqId);
				data.put("funID", 11004);
				Thread.sleep(10000);
				try {
					SocketUtil.sendToImForHeartbeat(data.toString());
				} catch(NullPointerException e) {
					COUNT++;
					LogUtil.log.error("发送心跳异常，发生次数：" + COUNT);
					if(COUNT >3) {
						throw e;
					}
				}
			}
		} catch(Exception e) {
			LogUtil.log.error("发送心跳异常：重新启动监听。。。");
			SocketRestartThread rest = new SocketRestartThread();
			ThreadManager.getInstance().execute(rest);
			LogUtil.log.error(e.getMessage(), e);
		}
	}

}
