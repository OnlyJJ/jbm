/**
 *
 */
package com.lm.jbm.vo;



import java.io.Serializable;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.lm.jbm.utils.JsonParseInterface;
import com.lm.jbm.utils.JsonUtil;


/**
 * Administrator
 * created at 2016/9/12 11:50
 * TODO:IM实体类
 */
public class MsgData extends JsonParseInterface implements Serializable {

    public MsgData() {

    }

    public MsgData(int type) {
        this.type = type;
    }

    /**
     * 消息ID
     */
    public String msgId;

    /**
     * 1:单聊，2：群聊
     */
    public int msgType;

    /**
     * 用户ID 或者 房间ID
     */
    public String targetId;

    /**
     * @link com.cn.nineshows.entity.im.ChatType
     */
    public int type;
    /**
     * 发送时间
     */
    public long datetime;

    /**
     * 附件地址
     */
    public String attachment;

    /**
     * 普通字符串消息内容
     */
    public String content;

    /**
     * token
     */
    public String token;

    /**
     * 加入房间|退出房间  成功与否的状态  2000为成功，其他为失败
     * 状态码
     */
    public int status;

    /**
     * 错误信息
     */
    public String decr;

    /**
     * 字体颜色
     */
    public String fontColor;
    /**
     * 是否发送
     */
    public boolean isSend = false;
    /**
     * 是否已读
     */
    public boolean isRead = false;

    /**
     * 未读个数
     */
    public long unReadCount = 0;

    /**
     * 格式化后的时间戳
     */
    public String timestamp;

    /**
     * 发送者信息
     */
    public Chat2User user;

    /**
     * 接收者消息
     */
    public Chat2User to;

    /**
     * 熟桃
     */
    public String matureRoomId;
    public String matureUserId;
    public int maturePeachRipeLevel;//蜜桃成熟的阶数，5|6

    //红包
    public String redPacketsId;


    public String getMatureRoomId() {
        return matureRoomId;
    }

    public String getMatureUserId() {
        return matureUserId;
    }

    public int getMsgType() {
        return msgType;
    }

    public int getType() {
        return type;
    }

    public void setIsSend(boolean isSend) {
        this.isSend = isSend;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

    public void setDatetime(long datetime) {
        this.datetime = datetime;
    }

    public String getMsgId() {
        return msgId;
    }

    public String getTargetId() {
        return targetId;
    }

    public long getDatetime() {
        return datetime;
    }

    public String getAttachment() {
        return attachment;
    }

    public String getContent() {
        return content;
    }

    public String getToken() {
        return token;
    }

    public int getStatus() {
        return status;
    }

    public String getDecr() {
        return decr;
    }

    public boolean isSend() {
        return isSend;
    }

    public boolean isRead() {
        return isRead;
    }

    public long getUnReadCount() {
        return unReadCount;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public Chat2User getUser() {
        return user;
    }

    public Chat2User getTo() {
        return to;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setUnReadCount(long unReadCount) {
        this.unReadCount = unReadCount;
    }

    public String getFontColor() {
        return fontColor;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }

    @Override
    public String toString() {
        return "MsgData{" +
                "isSend=" + isSend +
                ", msgId='" + msgId + '\'' +
                ", msgType=" + msgType +
                ", targetId='" + targetId + '\'' +
                ", type=" + type +
                ", datetime=" + datetime +
                ", attachment='" + attachment + '\'' +
                ", content='" + content + '\'' +
                ", token='" + token + '\'' +
                ", status=" + status +
                ", decr='" + decr + '\'' +
                ", fontColor='" + fontColor + '\'' +
                ", isRead=" + isRead +
                ", unReadCount=" + unReadCount +
                ", timestamp='" + timestamp + '\'' +
                ", user=" + user +
                ", to=" + to +
                '}';
    }

    @Override
    public JSONObject buildJson() {
        try {
            json = new JSONObject();
            setString(json, "msgid", msgId);
            setInt(json,"msgtype", msgType);
            setString(json,"targetid", targetId);
            setInt(json,"type", type);
            setLong(json,"datetime", datetime);
            setString(json, "content", content);

//            //如果礼物不为null，则表示赠送礼物
//            if (gift != null)
//                json.put("content", gift.buildJson2Msg());

            setString(json, "attachment", attachment);
//            put("sessionid", sessionId);
//            put("uid", uid);
            setString(json,"token", token);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }


    @Override
    public void parseJson(JSONObject json) {
        if (json == null) {
            return;
        }

        int funID = 0;
        try {
            funID = json.getIntValue("funID");
            if (json.containsKey("data")) {
                this.json = new JSONObject();
                json.put("data", json.getString("data"));
            }

	        if (null == this.json)
	            return;
	
	        msgId = getString(json,"msgid");
	        if(!json.containsKey("msgtype")) {
	        	msgType = 2;
	        } else {
	        	msgType = getInt(json,"msgtype");
	        }
	        targetId = getString(json,"targetid");
	        type = getInt(json,"type");
	        attachment = getString(json,"attachment");
	        token = getString(json,"token");
	        if(!json.containsKey("status")) {
	        	status = 0;
	        } else {
	        	status = getInt(json,"status");
	        }
	        decr = getString(json,"decr");
	        if(!json.containsKey("datetime")) {
	        	datetime = 0;
	        } else {
	        	datetime = getLong(json,"datetime");
	        }
	        fontColor = getString(json,"fontColor");
	        try {
	            //系统消息
	            if (SocketFunctionID.ID_SOCKET_PUSH_SYS_MSG == funID) {
	
	                parseJson2SysMsg();
	            }
	            //普通常用消息
	            else {
	
	                parseJson2CommonMsg();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getShortName() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * 解析常用消息
     */
    private void parseJson2CommonMsg() throws JSONException {

        switch (type) {

            case 17://蜜桃成熟消息体
                type = ChatType.MSG_TYPE_MT_MATURE;
                break;
            case 8://红包
                type = ChatType.MSG_TYPE_RED_PACKERS;
                break;
            default://默认为特殊类型，不处理此类型的IM消息
                type = ChatType.MSG_TYPE_COMPATIBLE;
                break;
        }
        //蜜桃成熟消息
        if (type == ChatType.MSG_TYPE_MT_MATURE) {


            JSONObject mtMatureJsonObject = JsonUtil.strToJsonObject(json.getString("content"));

            //主播信息
            if (mtMatureJsonObject.containsKey("anchor")) {

                matureUserId = mtMatureJsonObject.getJSONObject("anchor").getString("uid");
            }
            //主播的房间id
            if (mtMatureJsonObject.containsKey("roomId")) {
                matureRoomId = mtMatureJsonObject.getString("roomId");
            }
            //蜜桃阶数
            if (mtMatureJsonObject.containsKey("peachRipeLevel")) {
                maturePeachRipeLevel = mtMatureJsonObject.getIntValue("peachRipeLevel");
            }
        }
        //判断是否为红包
        else if (type == ChatType.MSG_TYPE_RED_PACKERS) {
            JSONObject redPacketsJsonObject = JsonUtil.strToJsonObject(json.getString("content"));
            //红包id
            if (redPacketsJsonObject.containsKey("id")) {
                redPacketsId = redPacketsJsonObject.getString("id");
            }
        } else {
            content = json.getString("content");
        }

    }

    private String[] doSplit(String code) {
        String[] set = new String[]{};
        try {
            set = json.getString(code).split(",");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return set;
    }

    /**
     * 系统消息
     */
    private void parseJson2SysMsg() throws JSONException {

        switch (type) {
            case 1://进入房间(默认为矮丑挫)
                type = ChatType.MSG_TYPE_JOIN_ROOM;
                break;

            default://默认为特殊类型，不处理此类型的IM消息
                type = ChatType.MSG_TYPE_COMPATIBLE;
        }
        content = json.getString("content");

    }
}
