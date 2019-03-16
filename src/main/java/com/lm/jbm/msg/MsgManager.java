package com.lm.jbm.msg;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.lm.jbm.utils.ByteUtil;
import com.lm.jbm.utils.GZipUtil;
import com.lm.jbm.utils.JsonUtil;
import com.lm.jbm.utils.LogUtil;


public class MsgManager {
    public static int seqID = 0;
    private static int SIZE = 1024;
   
	public static void sendToImForHeartbeat(String msg, Socket socket)  throws Exception{
		DataOutputStream os = null;
		try {
			if(StringUtils.isNotEmpty(msg)) {
				msg=msg.replaceAll("\n|\r|\t|\b|\f", "");
				byte[] body = GZipUtil.compressToByte(msg);
				byte[] head = ByteUtil.toByteArray(body.length, 4);
				byte[] data = new byte[body.length+head.length];

				System.arraycopy(head, 0, data, 0, head.length);
				System.arraycopy(body, 0, data, head.length, body.length);
				os = new DataOutputStream(socket.getOutputStream());
				os.write(data);
				os.flush();
			}
		} catch(Exception e) {
			if(os != null) {
				os.close();
			}
			throw e;
		}
	}
	
	public static String recieve(Socket socket) throws Exception {
		DataInputStream is = null;
		try {
			is = new DataInputStream(socket.getInputStream());
			String msg = getDataBody(is); 
			return msg;
		} catch(Exception e) {
			throw e;
		}
	}

	public static String getDataBody(InputStream is) throws IOException {
		String dataBody = null;
		byte[] head = getData(is, 4);
		int dataLength = ByteUtil.toInt(head);
		byte[] data = getData(is, dataLength);
		dataBody = GZipUtil.uncompressToString(data, "utf-8");
		return dataBody;
	}
	
	private static byte[] getData(InputStream is, int length) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[5120];
		int nIdx = 0;  
		int nReadLen = 0;  
		while (nIdx < length) {  
			if(length - nIdx >= buffer.length){  
				nReadLen = is.read(buffer);
			}else{  
				nReadLen = is.read(buffer, 0, length - nIdx);
			}
			if (nReadLen > 0) {
				baos.write(buffer, 0, nReadLen);
				nIdx = nIdx + nReadLen;
			} else {
				break;
			}
		}
		return baos.toByteArray();
	}
	
	public static  void sendSocketMsg(OutputStream ops, String content) {
		try {
			BufferedOutputStream bops = new BufferedOutputStream(ops,
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
		} catch (Exception e) {
			e.printStackTrace();
		}  
	}
	 /**
     * seqID自增
     */
    public static int getSeqID() {
        if (seqID < Integer.MAX_VALUE) {
            seqID++;
        } else {
            seqID = 1;
        }
        return seqID;
    }
}
