package com.tianxiaohui;

public class OmitStackTrace {
	public static void doSomething() {
		// 循环 N 次, 只为每次都发生空指针异常
		for (int i = 0; i < 1000000000; i++) {
			try {
				Object obj = createObject();
				System.out.println(obj.hashCode());
			} catch (Exception e) {// 打印出错栈
					e.printStackTrace();
			}
		}
	}

	public static Object createObject() {
		return null;// 为了创造空指针异常, 返回一个空对象,
	}

	public static void main(String[] args) {
		OmitStackTrace.doSomething();
	}

}
