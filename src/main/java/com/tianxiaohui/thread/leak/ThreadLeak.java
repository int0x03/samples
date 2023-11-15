package com.tianxiaohui.thread.leak;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLeak {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public void handleRequest(List<String> requestPayload) {
		if (requestPayload.size() > 0) {
			ExecutorService executor = Executors.newFixedThreadPool(2);
			
			for (String str : requestPayload) {
				final String s = str;
				executor.submit(new Runnable() {
					@Override
					public void run() {
						// print 
						System.out.println(s);
					}
				});
			}
		}
		// do some other things
	}
	
	public void handleRequests(List<String> requestPayload) {
		if (requestPayload.size() > 0) {
			for (String str : requestPayload) {
				final String s = str;
				Thread t = new Thread(new Runnable() {
					@Override
					public void run() {
					}
				}, "ProcessingTaskThread");
				t.setDaemon(true);
				t.start();
			}
		}
	}
}
