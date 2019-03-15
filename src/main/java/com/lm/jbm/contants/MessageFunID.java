package com.lm.jbm.contants;

public enum MessageFunID {

	/** 客户端用户标识 */
	FUNID_11000(11000),
	/** 服务端用户认证成功 */
	FUNID_12000(12000),
	
	/** 客户端向服务器发送聊天消息 */
	FUNID_11001(11001),
	/** 服务器收到聊天消息应答 */
	FUNID_12001(12001),
	
	/** 服务器向客户端推送聊天消息 */
	FUNID_11002(11002),
	/** 服务器向客户端推送聊天消息应答 */
	FUNID_12002(12002),
	
	/** 客户端心跳包 */
	FUNID_11004(11004),
	/** 心跳包成功响应 */
	FUNID_12004(12004),
	
	/** 客户端退出 */
	FUNID_11005(11005),
	
	/** 加入房间 */
	FUNID_11006(11006),
	/** 加入房间响应 */
	FUNID_12006(12006),

	/** 退出房间 */
	FUNID_11007(11007),
	/** 退出房间响应 */
	FUNID_12007(12007),
	
	/** 服务端用户认证失败 */
	FUNID_13000(13000),
	
	/** socket断开通知*/
	FUNID_21000(21000),
	
	/** 加好友通知*/
	FUNID_21001(21001),
	
	/** 同意加好友通知*/
	FUNID_21002(21002),
	
	/** 群组变更通知*/
	FUNID_21003(21003),
	
	/** 个人资料变更通知*/
	FUNID_21004(21004),
	
	/** 朋友圈有新动态通知*/
	FUNID_21005(21005),
	
	/** 朋友圈被评论、被回复或者被赞通知*/
	FUNID_21006(21006),
	
	/** 系统通知*/
	FUNID_21007(21007),
	/** 系统通知应答*/
	FUNID_22007(22007)
	;
	
	private int funID;
	
	private MessageFunID(int funID){
		this.funID = funID;
	}

	public int getFunID() {
		return funID;
	}
	
}
