package com.server.sgip;
import java.util.Map;

import com.huawei.smproxy.*;
import com.huawei.smproxy.comm.sgip.message.*;
import com.huawei.smproxy.util.Args;

/**
 * Title:        ��������
 * Description:  �ṩͨ���й��ƶ�����Э��CMPP3.0�շ����ŵ�������
 * Copyright:    ��Ȩ���У���Ϊ�������޹�˾/Copyright (c) 2001 HUAWEI TECHNOLOGIES CO. LTD.
 * Company:      ��Ϊ�������޹�˾
 * @author
 * @version 1.0
 */

public class MySGIPSMProxy extends SGIPSMProxy
{

  private SGIPDemo  demo = null;
  public MySGIPSMProxy(SGIPDemo demo, Map args)
  {
     super(args);
     startService("127.0.0.1", 8088);
     this.demo = demo;
  }
  /**
   * ��ISMG�����·�����Ϣ�Ĵ���
   * @param msg �յ�����Ϣ
   * @return ���ص���Ӧ��Ϣ��
   */
  public SGIPMessage onDeliver(SGIPDeliverMessage msg) {
    /**@todo do some thing to handle this message. then return a response */
    demo.ProcessRecvDeliverMsg(msg);
    return super.onDeliver(msg);
  }
  public void OnTerminate()
  {
    demo.Terminate();
  }  
   
}