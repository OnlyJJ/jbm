package com.lm.jbm.utils;

public class ChatMsgUtil {
	/**
	 * 表情
	 */
	private static final String[] EMOIG = {"[em27]","[em13]","[em19]","[em41]", "[em65]", "[em39]"};
	/**
	 * 聊天内容
	 */
	private static final String[] CONENT = {"6666", "哈哈哈哈","扎心了", "厉害" ,"冒个泡证明我存在~[em39]"};
	
	/**
	 * 获取聊天内容
	 * @param type 1-表情，2-文字
	 */
	public static String getMsgInfo(int type) {
		String content = "";
		switch(type) {
		case 1:
			content = EMOIG[RandomUtil.getRandom(0, EMOIG.length)];
			break;
		case 2:
			content = CONENT[RandomUtil.getRandom(0, CONENT.length)];
			break;
		default :
			content = "好厉害";
		}
		return content;
	}
	
}
