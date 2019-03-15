package com.lm.jbm.vo;

/**
 * Administrator
 * created at 2016/6/27 15:12
 * TODO:发送socket消息状态
 */
public class SocketMsgStatus {


    public final static int RESPONSE_STATUS_2000 = 2000;//SUCCESS
    public final static int RESPONSE_STATUS_5000 = 5000;//服务器未知错误
    public final static int RESPONSE_STATUS_5001 = 5001;//用户标识未验证或验证失败
    public final static int RESPONSE_STATUS_5002 = 5002;//参数格式错误
    public final static int RESPONSE_STATUS_5004 = 5004;//会话已失效
    public final static int RESPONSE_STATUS_5005 = 5005;//非法数据
    public final static int RESPONSE_STATUS_5008 = 5008;//用户未登录
    public final static int RESPONSE_STATUS_5012 = 5012;//用户被禁言
    public final static int RESPONSE_STATUS_5013 = 5013;//发言速度太快（草民发言）
    public final static int RESPONSE_STATUS_5014 = 5014;//私聊只对一富以上用户开放
    public final static int RESPONSE_STATUS_5015 = 5015;//您的私聊间隔不能少于10秒，升级六富即可畅所欲言~
    public final static int RESPONSE_STATUS_5016 = 5016;//您发言速度太快，请稍后再试

}
