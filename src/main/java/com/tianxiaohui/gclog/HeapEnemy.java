package com.tianxiaohui.gclog;

import java.util.HashMap;
import java.util.Map;

public class HeapEnemy {

	public static void main(String[] args) {
		Map<Long, String> map = new HashMap<>();
		Long now = null;
		while (true) {
			now = System.currentTimeMillis();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < 100; i++) {
				sb.append(now);
			}
			map.put(now, sb.toString());
			//System.out.println(now);
		}
	}

}
