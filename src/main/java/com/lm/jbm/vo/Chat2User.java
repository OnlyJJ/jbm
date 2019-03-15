package com.lm.jbm.vo;


import java.io.Serializable;
import java.util.Random;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.lm.jbm.utils.JsonParseInterface;


/**
 * Administrator
 * created at 2016/5/5 19:06
 * TODO:IM 用户实体
 */
public class Chat2User extends JsonParseInterface implements Serializable {

    public String userId;//用户id

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    @Override
    public JSONObject buildJson() {
        try {
            json = new JSONObject();
            setString(json, "uid", userId);


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
			userId = getString(json, "uid");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Override
    public String getShortName() {
        return "";
    }

    private int getRandom() {
        int result = 190000;
        Random random = new Random();
        result += random.nextInt(1000);
        return result;
    }
}
