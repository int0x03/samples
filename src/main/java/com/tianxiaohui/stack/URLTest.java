package com.tianxiaohui.stack;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class URLTest {
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 1000; i++) {
	        try {
	            // 创建 URL 对象
	            URL url = new URL("http://www.tianxiaohui.com");
	
	            // 创建 HttpURLConnection 对象
	            HttpURLConnection con = (HttpURLConnection) url.openConnection();
	
	            // 设置请求方法
	            con.setRequestMethod("GET");
	
	            // 打印请求码
	            System.out.println(i + "Response code: " + con.getResponseCode());
	
	            // 获取响应数据
	            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	            String line;
	            StringBuilder response = new StringBuilder();
	
	            while ((line = in.readLine()) != null) {
	                response.append(line);
	            }
	            in.close();
	            // 打印响应数据
	            //System.out.println(response.toString());
	
	        } catch (Exception e) {
	        	e.printStackTrace();
	            System.out.println(e.getMessage());
	        }
	        Thread.sleep(2000);
        }
    }
}
