package com.server.cmpp;

import com.util.cmppUtil.MsgContainer;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String content = "测试信息3，退订回N【京东商城】";
		String mobile = "127.0.0.1";
		MsgContainer.sendMsg(content,mobile);
	}

}
