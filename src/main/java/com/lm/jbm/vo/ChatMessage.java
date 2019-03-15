/**
 *
 */
package com.lm.jbm.vo;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.lm.jbm.utils.JsonParseInterface;
import com.lm.jbm.utils.JsonUtil;

/**
 * Administrator
 * created at 2016/9/12 11:51
 * TODO:所有IM消息主体
 */
public class ChatMessage extends JsonParseInterface {

    public int funID;

    /**
     * 消息响应id，序列号(int 4) 1-INTEGER.MAX
     */
    public int seqID;


    public MsgData data;


    public int getfunID() {
        return funID;
    }


    public void setfunID(int msgID) {
        this.funID = msgID;
    }


    public int getSeqID() {
        return seqID;
    }

    public void setSeqID(int seqID) {
        this.seqID = seqID;
    }

    public MsgData getData() {
        return data;
    }


    public void setData(MsgData data) {
        this.data = data;
    }


    @Override
    public JSONObject buildJson() {
        try {
            json = new JSONObject();
            if (funID != 0) {
                setInt(json,"funID", funID);
            }
            setInt(json, "seqID", seqID);
            if (data != null) {
                json.put("data", data.buildJson());
            }
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
        this.json = json;

        try {
			funID = getInt(json, "funID");
			seqID = getInt(json, "seqID");
			data = (MsgData) JsonUtil.parseJSonObjectNotShortName(MsgData.class, json.toString());
		} catch (Exception e) {
		}

    }


    @Override
    public String toString() {
        return "ChatMessage{" +
                "funID=" + funID +
                ", seqID=" + seqID +
                ", data=" + data +
                '}';
    }

    @Override
    public String getShortName() {
        return "";
    }



}
