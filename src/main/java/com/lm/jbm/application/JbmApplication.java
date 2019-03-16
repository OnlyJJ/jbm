package com.lm.jbm.application;

import com.lm.jbm.service.InitializingBiz;
import com.lm.jbm.utils.LogUtil;
import com.lm.jbm.utils.PropertiesUtil;


public class JbmApplication {
	
	public static void main(String[] args) {
		try {
			// false：开发环境，true：生产
			PropertiesUtil.load(false);
			InitializingBiz.init();
		} catch(Exception e) {
			LogUtil.log.error(e);
		}
	}
	
}
