package com.lm.jbm.socket;


import java.net.Socket;
import com.alibaba.fastjson.JSONObject;
import com.lm.jbm.factory.SocketFactory;
import com.lm.jbm.msg.MsgManager;
import com.lm.jbm.service.InitializingBiz;
import com.lm.jbm.utils.LogUtil;


public class SocketHertThread implements Runnable {
	private Socket socket;
	private String userId;
	
	public SocketHertThread(Socket socket, String userId) {
		this.socket = socket;
		this.userId = userId;
	}

	public void run() {
		try {
			int count = 1;
			while(true) {
				JSONObject data = new JSONObject();
				data.put("funID", 11004);
				data.put("seqID", MsgManager.getSeqID());
				Thread.sleep(10000);
				try {
					MsgManager.sendToImForHeartbeat(data.toString(), socket);
				} catch(Exception e) {
					if(count > 10) {
						LogUtil.log.info("接收心跳异常，当前重试次数：" +count + "，销毁当前socket，重启新用户！");
						throw e;
					}
					count++;
				}
			}
		} catch(Exception e) {
			LogUtil.log.error("发送心跳异常：重新启动监听。。。");
			LogUtil.log.error(e.getMessage(), e);
			// 销毁当前socket
			SocketFactory.destory(socket, userId);
		} finally {
			// 初始化新用户监听
			LogUtil.log.info("销毁当前socket，重启新用户！");
			InitializingBiz.newUserListen();
			
		}
	}

}
