package com.server.http.yunXin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Date;

public class YunXin {
	public static void main(String[] args) throws Exception
    {
        System.out.println(sendMsg());


    }

	
    /**
     * 发送POST方法的请求
     * 
     * @return 所代表远程资源的响应结果
     */
    public static String sendMsg()
    {
        String appKey = "0efd67eef195c8e3800eb10a042d1e81";
        String appSecret = "57d27ff4593f";
        String nonce = "baoluo"; // 随机数（最大长度128个字符）
        String curTime = String.valueOf((new Date()).getTime() / 1000L); // 当前UTC时间戳
        System.out.println("curTime: " + curTime);


        String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce, curTime);
        System.out.println("checkSum: " + checkSum);


        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try
        {
            String url = "https://api.netease.im/sms/sendtemplate.action"; //网址可以不修改
            String encStr1 = URLEncoder.encode("Tom", "utf-8");
            String encStr2 = URLEncoder.encode("name", "utf-8"); // url编码；防止不识别中文
            String params = "templateid=201704028841253&mobiles=[\"15720310922\"]" 
                            + "&params=" + "[\"" + encStr1 + "\",\""+ encStr2 + "\"]";
            System.out.println("params：" + params);


            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("AppKey", appKey);
            conn.setRequestProperty("CheckSum", checkSum);
            conn.setRequestProperty("CurTime", curTime);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            conn.setRequestProperty("Nonce", nonce);


            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
     


            // 发送请求参数
            out.print(params);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            System.out.println(conn);
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            System.out.println("in"+in);
            String line;
            while ((line = in.readLine()) != null)
            {
                result += line;
            }
        } catch (Exception e)
        {
            System.out.println("发送 POST 请求出现异常！\n" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally
        {
            try
            {
                if (out != null)
                {
                    out.close();
                }
                if (in != null)
                {
                    in.close();
                }
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
        return result;
    }
    
}
