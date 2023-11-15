package com.tianxiaohui.async;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Supplier;

public class WhyFutureStuck {
	
	public static void main(String[] args) {
		ExecutorService es = Executors.newFixedThreadPool(2);
		
		Future<String> f1 = es.submit((Callable<String>) new Callable<String>() {
			@Override
			public String call() throws Exception {
				Thread.sleep(3600 * 1000);
				return "result-f1";
			}
		});
		
		CompletableFuture<String> cf1 = CompletableFuture.supplyAsync((Supplier<String>) () -> {
			try {
				Thread.sleep(3600 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "result-cf1";
		}, es);
		
		//threads are used up
		Future<String> f2 = es.submit((Callable<String>) new Callable<String>() {
			@Override
			public String call() throws Exception {
				Thread.sleep(3600 * 1000);
				return "result-f2";
			}
		});
		
		CompletableFuture<String> cf2 = CompletableFuture.supplyAsync((Supplier<String>) () -> {
			try {
				Thread.sleep(3600 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "result-cf2";
		}, es);
		
		try {
			System.out.println(f1.get());
			System.out.println(f2.get());
			System.out.println(cf1.get());
			System.out.println(cf2.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
}
