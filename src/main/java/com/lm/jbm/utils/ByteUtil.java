package com.lm.jbm.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ByteUtil {
	
	public static byte[] toByteArray(int iSource, int iArrayLen) {
		byte[] bLocalArr = new byte[iArrayLen];
		for (int i = 0; (i < 4) && (i < iArrayLen); i++) {
			bLocalArr[i] = (byte) (iSource >> 8 * i & 0xFF);

		}
		return bLocalArr;
	}
	
	
	public static int toInt(byte[] bRefArr) {
		int iOutcome = 0;
		byte bLoop;

		for (int i = 0; i < 4; i++) {
			bLoop = bRefArr[i];
			iOutcome += (bLoop & 0xFF) << (8 * i);

		}
		return iOutcome;
	}
	
	 public static String getDataBody(InputStream is) throws IOException {
			String dataBody = null;
			byte[] head = getData(is, 4);
			int dataLength = ByteUtil.toInt(head);
			byte[] data = getData(is, dataLength);
			dataBody = GZipUtil.uncompressToString(data);

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

}
