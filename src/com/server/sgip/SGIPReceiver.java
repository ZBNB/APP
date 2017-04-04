package com.server.sgip;

import java.util.Map;

import com.huawei.smproxy.SGIPSMProxy;
import com.huawei.smproxy.comm.sgip.message.SGIPDeliverMessage;
import com.huawei.smproxy.comm.sgip.message.SGIPMessage;

public class SGIPReceiver extends SGIPSMProxy
{
	private static Map args = Env.getConfig();

	public SGIPReceiver() {
		super(args);
		startService("10.164.102.171", 8801);
	}

	/**
	 * ��ISMG�����·�����Ϣ�Ĵ���
	 * 
	 * @param msg
	 *            �յ�����Ϣ
	 * @return ���ص���Ӧ��Ϣ��
	 */
	public SGIPMessage onDeliver(SGIPDeliverMessage msg)
	{
		/** @todo do some thing to handle this message. then return a response */

		if (null != msg)
		{
			System.out
			        .println("\n**************************Received a new message!***************************");
			System.out.println(msg.toString());
			System.out.println("The user is: " + msg.getUserNumber()  );
			System.out
			        .println("***************************End new message! **************************\n");

		}

		return super.onDeliver(msg);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		
		SGIPReceiver receiver = new SGIPReceiver();
		
	}

}
