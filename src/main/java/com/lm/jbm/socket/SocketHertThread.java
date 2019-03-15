package com.lm.jbm.socket;


import java.net.Socket;
import com.alibaba.fastjson.JSONObject;
import com.lm.jbm.utils.LogUtil;


public class SocketHertThread implements Runnable {
	private Socket socket;
	
	public SocketHertThread(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try {
			while(true) {
				JSONObject data = new JSONObject();
				data.put("funID", 11004);
				data.put("seqID", MsgManager.getSeqID());
				Thread.sleep(10000);
				try {
					MsgManager.sendToImForHeartbeat(data.toString(), socket);
				} catch(NullPointerException e) {
				}
			}
		} catch(Exception e) {
			LogUtil.log.error("发送心跳异常：重新启动监听。。。");
			LogUtil.log.error(e.getMessage(), e);
		}
	}

}
