package com.server.sgip;

import java.util.*;

/**
 * �ṩϵͳ���л�����Ϣ��
 */
public class Env {

  /** ���ö�д�ࡣ*/
  static Map<String, String> config;

  /**
   * ȡ�����ö�д�ࡣ
   */
  public static Map getConfig() {

    // SP add the GW info
    if (config == null) {
      try {
        config = new HashMap<String, String>();
        //网关主机的IP
        config.put("host", "127.0.0.1");
        //连接网关主机的端口
        config.put("port", "8888");
		//登陆的帐号名
        config.put("login-name", "sptest");
		//对应之前的login-pass
        config.put("login-pass", "sptest"); 
        //心跳信息发送间隔时间(单位：秒)
        config.put("heartbeat-interval","30" );
        //连接中断时重连间隔时间(单位：秒)
        config.put("reconnectInterval", "30");
        //需要重连时，连续发出心跳信息没有接收到响应的个数（单位：个)
        config.put("heartbeat-noresponseout", "5");
        //操作超时时间(单位：秒)
        config.put("transaction-timeout", "10");
       //	true表示属于调试状态，所有的消息被打印输出到屏幕;false表示不属于调试状态，所有的消息不被输出。
        config.put("debug", "false");   
      
      }
      catch (Exception ex) {
        ex.printStackTrace();
      }
    }
    return config;
  }
  
}
