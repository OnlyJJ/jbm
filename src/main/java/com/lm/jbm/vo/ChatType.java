package com.lm.jbm.vo;

/**
 * Administrator
 * created at 2016/6/24 18:24
 * TODO:聊天类型
 */
public class ChatType {

    public final static int MSG_TYPE_JOIN_ROOM = 1;//加入房间
    public final static int MSG_TYPE_MT_MATURE = 2;//蜜桃成熟消息体
    public final static int MSG_TYPE_RED_PACKERS = 3;//红包


    //特殊消息类型,不做处理，用于兼容旧版
    public final static int MSG_TYPE_COMPATIBLE = 10000;
}
