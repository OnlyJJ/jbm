/**
 * 
 */
package com.lm.jbm.vo;

/**
 * @author "Park_tan"
 * <h2>TODO
 * @since 2015-4-27
 * @version V1.0
 */
public class SocketFunctionID {
	/** 发送验证 */
	public final static int ID_SOCKET_VERIFICAUSER = 11000;
	/** 响应成功验证 */
	public final static int ID_SOCKET_VERIFICAUSER_SUCCESS= 12000;
	/** 响应失败验证  */
	public final static int ID_SOCKET_VERIFICAUSER_FAIL = 13000;
	/** 发送聊天信息 */
	public final static int ID_SOCKET_SEND_MSG=11001;
	/** 接收常用聊天信息*/
	public final static int ID_SOCKET_PUSH_COMMON_MSG = 11002;
	/** 接收系统消息*/
	public final static int ID_SOCKET_PUSH_SYS_MSG=21007;
	/** 发送心跳包*/
	public final static int ID_SOCKET_HEART_PACKAGE = 11004;
	/** 发送心跳包成功*/
	public final static int ID_SOCKET_HEART_PACKAGE_SUCCEED=12004;
	/** 发送聊天信息 socket响应*/
	public final static int ID_SOCKET_SEND_MSG_RESULT=12001;

	/** 加入房间*/
	public final static int ID_SOCKET_JOIN_ROOM=11006;
	/** 加入房间响应成功|失败*/
	public final static int ID_SOCKET_JOIN_ROOM_RESULT=12006;

	/**退出房间*/
	public final static int ID_SOCKET_EXIT_ROOM=11007;
	/**退出房间响应成功|失败*/
	public final static int ID_SOCKET_EXIT_ROOM_RESULT=12007;

	/** socket断开通知*/
	public final static int ID_SOCKET_ABORT = 21000;
	/**加好友通知*/
	public final static int ID_SOCKET_MAKEFRIEND_NOTICE = 21001;
	/** 同意加好友通知*/
	public final static int ID_SOCKET_MAKEFRIEND_AGREE_NOTICE= 21002;
	/** 群组变更通知*/
	public final static int ID_SOCKET_QUNZU_STATU_CHANGE = 21003;

	/** 退出登录*/
	public final static int ID_SOCKET_LOGOUT=11005;
	/** 退出登录成功*/
	public final static int ID_SOCKET_LOGOUT_SUCCESS=12005;
	

}
