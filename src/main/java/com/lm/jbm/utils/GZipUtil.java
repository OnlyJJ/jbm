package com.lm.jbm.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipException;

import org.apache.commons.lang.StringUtils;

public class GZipUtil {

	private static String encode = "utf-8";
	private static final int length = 1024;

	public String getEncode() {
		return encode;
	}

	public static void setEncode(String encode) {
		GZipUtil.encode = encode;
	}
	
	/**
     * @param data 需要压缩的内容
     */
    public static byte[] compressToBtyes(byte[] data) throws Exception {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 压缩
        compress(bais, baos);

        byte[] output = baos.toByteArray();

        baos.flush();
        baos.close();

        bais.close();

        return output;

    }
    
    public static void compress(InputStream is, OutputStream os)
            throws Exception {
        // DeflaterOutputStream GZIPOutputStream
        GZIPOutputStream gos = new GZIPOutputStream(os);

        int count;
        byte data[] = new byte[1024];
        int num = 0;
        while ((count = is.read(data, 0, data.length)) != -1) {
            gos.write(data, 0, count);
        }
        gos.finish();
        // gos.flush();//4.4.2会出错
        gos.close();
    }

	public static byte[] compressToByte(String str) {
		if (str == null || str.length() == 0) {
			return null;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		GZIPOutputStream gzip;
		try {
			gzip = new GZIPOutputStream(out);
			gzip.write(str.getBytes(encode));
			gzip.close();
		} catch (Exception e) {
		}
		return out.toByteArray();
	}
	public static byte[] compressToByte(String str, String encoding) {
		if (str == null || str.length() == 0) {
			return null;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		GZIPOutputStream gzip;
		try {
			gzip = new GZIPOutputStream(out);
			gzip.write(str.getBytes(encoding));
			gzip.close();
		} catch (Exception e) {
		}
		return out.toByteArray();
	}
	
	public static String compressToString(String str) throws IOException {
		if (str == null || str.length() == 0) {
			return null;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		GZIPOutputStream gzip;
		try {
			gzip = new GZIPOutputStream(out);
			gzip.write(str.getBytes(encode));
			gzip.close();
		} catch (Exception e) {
		}
		return out.toString(encode);
	}
	
	public static String uncompressToString(byte[] b) {
		if (b == null || b.length == 0) {
			return null;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream in = new ByteArrayInputStream(b);

		try {
			GZIPInputStream gunzip = new GZIPInputStream(in);
			byte[] buffer = new byte[length];
			int n;
			while ((n = gunzip.read(buffer)) >= 0) {
				out.write(buffer, 0, n);
			}
		} catch (Exception e) {
		}
		return out.toString();
	}

	public static String uncompressToString(byte[] b, String encoding) throws IOException {
		if (b == null || b.length == 0) {
			return null;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream in = new ByteArrayInputStream(b);

		try {
			GZIPInputStream gunzip = new GZIPInputStream(in);
			byte[] buffer = new byte[length];
			int n;
			while ((n = gunzip.read(buffer)) >= 0) {
				out.write(buffer, 0, n);
			}
		}catch(ZipException zipException){ 
		}catch (IOException e) {
			throw e;
		}
		return out.toString(encoding);
	}
	
	public static String uncompress(String str) throws IOException {
		if (str == null || str.length() == 0) {
			return str;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream in = new ByteArrayInputStream(
				str.getBytes("UTF-8"));
		byte[] b = new byte[512];
		int n = 0;
		while((n = in.read(b, 0 , 512)) >0) {
			out.write(b, 0 , n);
		}
		byte[] bt = out.toByteArray();
		return uncompressToString(bt);
	}
}
