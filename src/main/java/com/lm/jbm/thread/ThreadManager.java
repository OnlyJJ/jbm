package com.lm.jbm.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class ThreadManager {
	
	private static class ThreadPool {
		private static final ExecutorService pools = new ThreadPoolExecutor(20, 10000,
                10L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(200));
	}
	
	private ThreadManager() {}
	
	public static final ExecutorService getInstance() {
		return ThreadPool.pools;
		
	} 
	
		
}
