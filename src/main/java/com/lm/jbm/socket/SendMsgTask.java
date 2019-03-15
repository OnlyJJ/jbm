/**
 * 
 */
package com.lm.jbm.socket;



import java.io.BufferedOutputStream;
import java.io.OutputStream;

import com.lm.jbm.utils.ByteUtil;
import com.lm.jbm.utils.GZipUtil;
import com.lm.jbm.utils.LogUtil;



public class SendMsgTask implements Runnable {

	private String content;
	private OutputStream writer;
	private int SIZE = 1024;

	public SendMsgTask(String content, OutputStream ips) {
		this.content = content;
		this.writer = ips;
	}

	public void run() {
		try {
			if (content == null) {
				LogUtil.log.error("send the content is null");
				return;
			}

			BufferedOutputStream bops = new BufferedOutputStream(writer,
					4 * SIZE);
			byte[] contentbytes = GZipUtil.compressToBtyes(content
					.getBytes("UTF-8"));
			int length = contentbytes.length;
			byte[] data = new byte[length + 4];
			byte[] lengthdata = ByteUtil.toByteArray(length, 4);
			for (int i = 0; i < (length + 4); i++) {
				if (i < 4) {
					data[i] = lengthdata[i];
				} else {
					data[i] = contentbytes[i - 4];
				}
			}
			bops.write(data);
			bops.flush();
			LogUtil.log.error("send the content succeed");
		} catch (Exception e) {
			LogUtil.log.error(e.getMessage());
		}
	}

}
