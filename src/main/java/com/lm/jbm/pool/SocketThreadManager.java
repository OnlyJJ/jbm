package com.lm.jbm.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class SocketThreadManager {
	
	private static class ThreadPool {
		private static final ExecutorService pools = new ThreadPoolExecutor(1000, 10000,
                90L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(500));
	}
	
	private SocketThreadManager() {}
	
	public static final ExecutorService getInstance() {
		return ThreadPool.pools;
		
	} 
	
		
}
