package com.server.sgip;

//��Ӧʹ�õ�com.huawei.insa2���ڵ���
import com.huawei.smproxy.*;
import com.huawei.smproxy.comm.sgip.message.*;
import com.huawei.smproxy.util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.lang.Thread;

public class SGIPDemo extends JFrame
{// implements DemoConst{

	private static Map args;

	/** �����շ��ӿ� */
	private MySGIPSMProxy smp = null;

	private boolean loginSmProxy = false;

	// ����CMPP_Submit_Repͳ����Ϣ
	int sendMsgSum = 0; // ������Ϣ������

	int sendSuccessMsgSum = 0; // �ɹ�������Ϣ������

	int recvDeliverMsgSum = 0; // ������Ϣ������

	// ����CMPP_Query_Repͳ����Ϣ
	int mt_tlmsg = 0; // ��SP������Ϣ������

	int mt_tlusr = 0; // ��SP���յ��û�����

	int mt_scs = 0; // �ɹ�ת������

	int mt_wt = 0; // ��ת������

	int mt_fl = 0; // ת��ʧ�ܵ�����

	int mo_scs = 0; // ��Sp�ɹ��ʹ������

	int mo_wt = 0; // ��SP���ʹ�����

	int mo_fl = 0; // ��SP�ʹ�ʧ�ܵ�����

	// ��ҪΪ������ڶ��߳̽������ܲ��Ե�ʱ��ʹ��

	int calledIndex = 0;

	String serviceId = null;

	String feeTerminalId = null;

	String msgSrc = null;

	Date valid_Time = null;// new Date();

	Date at_Time = null;// new Date();

	String srcTerminalId = null;

	String UserNumber[] = new String[1];

	byte[] msgContent = null;

	String destTerminalPhone = null;

	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.sss ");

	JLabel jLabel2 = new JLabel();

	JTextField service_Id = new JTextField();

	JLabel jLabel3 = new JLabel();

	JTextField fee_Terminal_Id = new JTextField();

	JLabel jLabel4 = new JLabel();

	JTextField msg_src = new JTextField();

	JLabel jLabel5 = new JLabel();

	JTextField src_Terminal_Id = new JTextField();

	JLabel jLabel6 = new JLabel();

	JTextField dest_Terminal_Id = new JTextField();

	JLabel jLabel7 = new JLabel();

	JTextField msg_Content = new JTextField();

	JButton SendButton = new JButton();


	JToggleButton loginbutton = new JToggleButton();


	JButton logoutbutton = new JButton();

	JLabel jLabel14 = new JLabel();

	JLabel jLabel15 = new JLabel();

	JTextField SendMsgSum = new JTextField();

	JLabel jLabel16 = new JLabel();

	JTextField SuccessSendSum = new JTextField();

	JLabel jLabel17 = new JLabel();

	JTextField RecvMsgSum = new JTextField();

	JLabel jLabel21 = new JLabel();

	JTextField threadSleepInterval = new JTextField();

	JLabel jLabel22 = new JLabel();

	JTextField myProxyState = new JTextField();

	JLabel jLabel23 = new JLabel();

	JTextField loginName = new JTextField();

	JLabel jLabel24 = new JLabel();

	JTextField loginPass = new JTextField();

	JButton initbutton = new JButton();

	JButton stopbutton = new JButton();

	JScrollPane jScrollPane1 = new JScrollPane();

	JTextArea allRecvContent = new JTextArea();

	public SGIPDemo() {
		/** @todo ���沼�֡� */
		try
		{
			jbInit();
			this.setSize(new Dimension(620, 450));
			Rectangle r = GraphicsEnvironment.getLocalGraphicsEnvironment()
			        .getScreenDevices()[0].getDefaultConfiguration()
			        .getBounds();
			this.setLocation((int) (r.getWidth() - 600) / 2, (int) (r
			        .getHeight() - 450) / 2);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * ������Ϣ�̵߳��߳���,��������Ϣ��
	 */
	public void Task()
	{
		sendMsgSum++;
		// ���Ͷ���Ϣ����,���Է��ص���Ϣ������,����ͳ������
		// ���ڳ��ֵ��쳣�򽻸�SendReqThreadȥ������.
		/*
		 * try { ProcessSubmitRep(smp.send(getSubmitMsg(calledIndex))); } catch
		 * (Exception ex) { ex.printStackTrace(); }
		 */
	}

	/**
	 * �������Ͷ���Ϣ������߳�
	 * 
	 * @param threadNum
	 *            �������̵߳ĸ���
	 */
	public void StartSendThread(int threadNum, int timeLong, int sleepInterval)
	{
		if (smp == null)
		{
			return;
		}
		// ���������߳�
		/*
		 * for(int i = 0;i<threadNum;i++) { new
		 * SendReqThread30("test",this,timeLong,sleepInterval).start(); }
		 */
	}

	/**
	 * ������ڡ�
	 */
	public static void main(String[] a) throws Exception
	{
		new SGIPDemo().show();

		args = Env.getConfig();
	}

	private void jbInit() throws Exception
	{
		this.getContentPane().setLayout(null);
		this.getContentPane().setBackground(new Color(204, 230, 210));
		this.setDefaultCloseOperation(3);
		this.setForeground(Color.black);
		this.setResizable(false);
		this.setTitle("����Ϣ���ز��Գ���");
		jLabel2.setText("ҵ������");
		jLabel2.setBounds(new Rectangle(22, 61, 57, 22));
		service_Id.setBorder(BorderFactory.createLineBorder(Color.black));
		service_Id.setToolTipText("");
		service_Id.setText("Huawei");
		service_Id.setBounds(new Rectangle(99, 61, 131, 22));
		jLabel3.setText("�ǷѺ���");
		jLabel3.setBounds(new Rectangle(242, 61, 57, 22));
		fee_Terminal_Id.setBorder(BorderFactory.createLineBorder(Color.black));
		fee_Terminal_Id.setText("");
		fee_Terminal_Id.setBounds(new Rectangle(305, 61, 131, 22));
		jLabel4.setText("��Ϣ��Դ");
		jLabel4.setBounds(new Rectangle(450, 61, 58, 22));
		msg_src.setBorder(BorderFactory.createLineBorder(Color.black));
		msg_src.setText("huawei");
		msg_src.setBounds(new Rectangle(506, 61, 75, 22));
		jLabel5.setText("���е�ַ");
		jLabel5.setBounds(new Rectangle(22, 94, 59, 22));
		src_Terminal_Id.setBorder(BorderFactory.createLineBorder(Color.black));
		src_Terminal_Id.setToolTipText("Դ�ն�MSISDN����, ���˶���Ϣ�����е�ַ");
		src_Terminal_Id.setText("127.0.0.1");
		src_Terminal_Id.setBounds(new Rectangle(99, 94, 131, 22));
		jLabel6.setText("���е�ַ");
		jLabel6.setBounds(new Rectangle(242, 94, 58, 22));
		dest_Terminal_Id.setBorder(BorderFactory.createLineBorder(Color.black));
		dest_Terminal_Id.setToolTipText("Ŀ���û��ֻ�����");
		dest_Terminal_Id.setText("127.0.0.1");
		dest_Terminal_Id.setBounds(new Rectangle(305, 94, 70, 24));
		
		jLabel7.setText("����Ϣ����");
		jLabel7.setBounds(new Rectangle(22, 124, 69, 22));
		msg_Content.setBorder(BorderFactory.createLineBorder(Color.black));
		msg_Content.setToolTipText("���뷢�͵Ķ���Ϣ����");
		msg_Content.setText("this is a test");
		msg_Content.setBounds(new Rectangle(99, 124, 483, 22));
		SendButton.setBorder(BorderFactory.createEtchedBorder());
		SendButton.setText("���Ͳ�������");
		SendButton.setBounds(new Rectangle(292, 190, 91, 22));
		SendButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				SendButton_actionPerformed(e);
			}
		});
		
		
		loginbutton.setBorder(BorderFactory.createEtchedBorder());
		loginbutton.setText("��½ϵͳ");
		loginbutton.setBounds(new Rectangle(392, 190, 91, 22));
		loginbutton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				loginbutton_actionPerformed(e);
			}
		});


		logoutbutton.setBorder(BorderFactory.createEtchedBorder());
		logoutbutton.setToolTipText("");
		logoutbutton.setText("�˳���½");
		logoutbutton.setBounds(new Rectangle(492, 190, 91, 22));
		logoutbutton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				logoutbutton_actionPerformed(e);
			}
		});
		jLabel14.setFont(new java.awt.Font("Dialog", 0, 14));
		jLabel14.setBorder(BorderFactory.createEtchedBorder());
		jLabel14.setText("ͳ������");
		jLabel14.setBounds(new Rectangle(63, 262, 61, 22));
		jLabel15.setText("������Ϣ����");
		jLabel15.setBounds(new Rectangle(24, 293, 81, 22));
		SendMsgSum.setBorder(BorderFactory.createEtchedBorder());
		SendMsgSum.setEditable(false);
		SendMsgSum.setBounds(new Rectangle(113, 293, 74, 22));
		jLabel16.setToolTipText("");
		jLabel16.setText("�ɹ�������Ϣ��");
		jLabel16.setBounds(new Rectangle(24, 320, 90, 22));
		SuccessSendSum.setBorder(BorderFactory.createEtchedBorder());
		SuccessSendSum.setEditable(false);
		SuccessSendSum.setBounds(new Rectangle(113, 320, 74, 22));
		jLabel17.setText("������Ϣ����");
		jLabel17.setBounds(new Rectangle(24, 347, 85, 22));
		RecvMsgSum.setBorder(BorderFactory.createEtchedBorder());
		RecvMsgSum.setToolTipText("Smc�·�����Ϣ������");
		RecvMsgSum.setEditable(false);
		RecvMsgSum.setBounds(new Rectangle(113, 348, 74, 22));
		jLabel21.setToolTipText("");
		jLabel21.setText("˯��ʱ��");
		jLabel21.setBounds(new Rectangle(320, 161, 56, 22));
		threadSleepInterval.setBorder(BorderFactory
		        .createLineBorder(Color.black));
		threadSleepInterval.setText("0");
		threadSleepInterval.setBounds(new Rectangle(377, 161, 76, 22));
		jLabel22.setBorder(BorderFactory.createEtchedBorder());
		jLabel22.setText("����״̬ ");
		jLabel22.setBounds(new Rectangle(26, 381, 58, 22));
		myProxyState.setBorder(BorderFactory.createEtchedBorder());
		myProxyState.setEditable(false);
		myProxyState.setBounds(new Rectangle(101, 382, 484, 22));
		myProxyState.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				myProxyState_actionPerformed(e);
			}
		});
		jLabel23.setToolTipText("");
		jLabel23.setText("��¼�ʺ�");
		jLabel23.setBounds(new Rectangle(23, 21, 66, 22));
		loginName.setBorder(BorderFactory.createLineBorder(Color.black));
		loginName.setText("sptest");
		loginName.setBounds(new Rectangle(100, 21, 131, 22));
		jLabel24.setText("��¼����");
		jLabel24.setBounds(new Rectangle(243, 21, 60, 22));
		loginPass.setBorder(BorderFactory.createLineBorder(Color.black));
		loginPass.setBounds(new Rectangle(306, 21, 131, 22));
		loginPass.setText("sptest");
		initbutton.setBorder(BorderFactory.createEtchedBorder());
		initbutton.setText("��ʼ��");
		initbutton.setBounds(new Rectangle(452, 21, 70, 22));
		initbutton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				initbutton_actionPerformed(e);
			}
		});
		stopbutton.setBorder(BorderFactory.createEtchedBorder());
		stopbutton.setText("�رռ���");
		stopbutton.setBounds(new Rectangle(532, 21, 70, 22));
		stopbutton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				stopbutton_actionPerformed(e);
			}
		});
		jScrollPane1
		        .setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jScrollPane1
		        .setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jScrollPane1.setBounds(new Rectangle(200, 253, 385, 122));
		allRecvContent.setLineWrap(true);

		this.getContentPane().add(jLabel23, null);
		this.getContentPane().add(loginName, null);
		this.getContentPane().add(jLabel24, null);
		this.getContentPane().add(loginPass, null);
		this.getContentPane().add(service_Id, null);
		this.getContentPane().add(jLabel2, null);
		this.getContentPane().add(jLabel3, null);
		this.getContentPane().add(fee_Terminal_Id, null);
		this.getContentPane().add(msg_src, null);
	
		this.getContentPane().add(logoutbutton, null);

		this.getContentPane().add(jLabel14, null);
		this.getContentPane().add(jScrollPane1, null);
		jScrollPane1.getViewport().add(allRecvContent, null);
		this.getContentPane().add(RecvMsgSum, null);
		this.getContentPane().add(jLabel15, null);
		this.getContentPane().add(jLabel16, null);
		this.getContentPane().add(SuccessSendSum, null);
		this.getContentPane().add(SendMsgSum, null);
		this.getContentPane().add(jLabel17, null);
		this.getContentPane().add(myProxyState, null);
		this.getContentPane().add(jLabel22, null);
		this.getContentPane().add(dest_Terminal_Id, null);
		this.getContentPane().add(src_Terminal_Id, null);
		this.getContentPane().add(jLabel6, null);
		this.getContentPane().add(jLabel5, null);
		this.getContentPane().add(msg_Content, null);
		this.getContentPane().add(jLabel7, null);
		this.getContentPane().add(SendButton, null);
		this.getContentPane().add(loginbutton, null);
		this.getContentPane().add(jLabel4, null);
		this.getContentPane().add(initbutton, null);
		this.getContentPane().add(stopbutton, null);

	}

	/**
	 * �����û����Ͷ���Ϣ�Ĳ�������.
	 */
	void SendButton_actionPerformed(ActionEvent e)
	{
		int threadNum = 0;
		int runInterval = 0;
		int sleepInterval = 0;

		// ���û�гɳɹ���¼
		if (!loginSmProxy)
		{
			return;
		}

		try
		{
//			threadNum = Integer.parseInt(ThreadNum.getText().trim());
//			runInterval = Integer.parseInt(threadRunInterval.getText().trim());
//			sleepInterval = Integer.parseInt(threadSleepInterval.getText()
//			        .trim());

			// begin
			msgContent = msg_Content.getText().trim().getBytes();
			// end
		} catch (Exception ex) // such as �û�û�������߳��������������Ƿ��ַ���ʱ��
		{ // ����ֻ���Ͷ���Ϣ,���������߳�
			threadNum = 0;
			runInterval = 0;
		}

		// ������Ĳ�������Ч���ж�
		if ((threadNum > 0) && (runInterval > 0))
		{
			// submitMsg = getSubmitMsg();
			StartSendThread(threadNum, runInterval, sleepInterval);
		} else
		{
			try
			{
				sendMsgSum++;
				ProcessSubmitRep(smp.send(getSubmitMsg(0)));
			} catch (IllegalArgumentException ex)
			{
				// add
			} catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}
		/*
		 * if(smp!=null) { try { smp.close(); smp = null; loginSmProxy = false;
		 * showStateMsg("�Ͽ�����"); } catch(Exception ex) { ex.printStackTrace();
		 * showStateMsg("��SMG���������쳣"); } }
		 */
	}

	/**
	 * ��½��SMG
	 */
	void loginbutton_actionPerformed(ActionEvent e)
	{

		// boolean result = smp.connect(loginName.getText(),
		// loginPass.getText());

		boolean result = smp.connect();

		if (result)
		{
			showStateMsg("��½�ɹ�");
		} else
		{
			showStateMsg("��½ʧ��");
		}
	}

	/**
	 * �Ͽ���SMG������
	 */
	void logoutbutton_actionPerformed(ActionEvent e)
	{
		if (smp != null)
		{
			smp.close();
		}
	}

	/**
	 * �����û����˳�����
	 */
	void ExitButton_actionPerformed(ActionEvent e)
	{

		// ���û�гɳɹ���¼
		if (!loginSmProxy)
		{
			showStateMsg("ϵͳû�гɹ���¼");
			return;
		}

		if (smp != null)
		{
			try
			{
				smp.close();
			} catch (Exception ex)
			{
				ex.printStackTrace();
				showStateMsg("��ISMG���������쳣");
			}
		}
	}

	/**
	 * �����û��������Ϣ����SubmitMsg
	 * 
	 * @return CMPPSubmitMessage ����
	 */
	private SGIPSubmitMessage getSubmitMsg(int index)
	{
		 
		  String SPNumber = src_Terminal_Id.getText().trim();;
		  String ChargeNumber = fee_Terminal_Id.getText().trim();		  
		  String[] UserNumber = dest_Terminal_Id.getText().trim().split(",");
		  String CorpId = "";
		  String ServiceType = "XW";
		  int FeeType = 2;
		  String FeeValue = "10";
		  String GivenValue = "0";
		  int AgentFlag = 0;
		  int MorelatetoMTFlag= 2;
		  int Priority = 0;
		  Date ExpireTime = null;
		  Date ScheduleTime = null;
		  int ReportFlag = 0;
		  int TP_pid = 0;
		  int TP_udhi = 0;
		  int MessageCoding = 0;
		  int MessageType = 6;
		  int MessageLen = msgContent.length;
		  byte[] MessageContent = msg_Content.getText().trim().getBytes();
		  String reserve = "0";
		 		 

		try
		{
			return new SGIPSubmitMessage( SPNumber,
					   ChargeNumber,
					   UserNumber,
					   CorpId,
					   ServiceType,
					   FeeType,
					   FeeValue,
					   GivenValue,
					   AgentFlag,
					   MorelatetoMTFlag,
					   Priority,
					   ExpireTime,
					   ScheduleTime,
					   ReportFlag,
					   TP_pid,
					   TP_udhi,
					   MessageCoding,
					   MessageType,
					   MessageLen,
					   MessageContent,
					   reserve);

		} catch (IllegalArgumentException e)
		{
			showStateMsg("�ύ����Ϣ���������������Ϸ�");
			e.printStackTrace();
			return null;
		} catch (Exception e)
		{
			showStateMsg("�ύ����Ϣ�������쳣");
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * ���ύ������յ�����Ӧ��Ϣ���д���
	 * 
	 * @param msg
	 *            �ύ����Ϣ������յ�����Ӧ��Ϣ
	 * @return ���ش���CMPP_Submit_REP�Ĳ������
	 */
	private void ProcessSubmitRep(SGIPMessage msg)
	{
		// �յ�����Ӧ��Ϣת����repMsg
		SGIPSubmitRepMessage repMsg = (SGIPSubmitRepMessage) msg;
		if (repMsg != null && repMsg.getResult() == 0)
		{
			sendSuccessMsgSum++;
		}
	}

	/**
	 * ���յ���Ѷ�����·��Ķ���Ϣ�Ĵ���
	 * 
	 * @param msg
	 *            ��Ѷ�����·��Ķ���Ϣ
	 */
	public void ProcessRecvDeliverMsg(SGIPMessage msg)
	{
		SGIPDeliverMessage deliverMsg = (SGIPDeliverMessage) msg;
		showStateMsg(deliverMsg.toString());
		// �·�����Ϣ
		recvDeliverMsgSum++;
	}

	/**
	 * ֪ͨSMC�·���Terminate��Ϣ
	 */
	public void Terminate()
	{

		showStateMsg("SMC�·��ն���Ϣ");
		loginSmProxy = false;
		smp = null;
	}

	/**
	 * ��ʾSmProxy���г����״̬
	 * 
	 * @str ��ʾ������
	 */
	private void showStateMsg(String str)
	{
		if ((str == null) || (str == ""))
		{
			return;
		}
		allRecvContent.insert(sdf.format(new Date()) + str + "\n", 0);

		// ��ֹ�ڴ�ľ�
		if (allRecvContent.getText().length() > 2 * 1024)
		{
			allRecvContent.setText(allRecvContent.getText().substring(0, 1024));
		}
		allRecvContent.setCaretPosition(0);
	}

	/**
	 * ��ʾͳ����Ϣ
	 */
	private void showStatisticData()
	{
		SendMsgSum.setText(new Integer(sendMsgSum).toString());
		SuccessSendSum.setText(new Integer(sendSuccessMsgSum).toString());
		RecvMsgSum.setText(new Integer(recvDeliverMsgSum).toString());
	}

	/**
	 * ���ϵͳ����״̬��Ϣ
	 */
	class MoniterThread extends Thread
	{
		public MoniterThread() {
			setDaemon(true);
		}

		public void run()
		{
			String connState = null;
			try
			{
				while (smp != null)
				{
					connState = smp.getConnState();
					showStatisticData();
					if (connState == null)
						connState = "ϵͳ��������";
					myProxyState.setText(connState);

					// һ���Ӽ��10��
					sleep(100);
				}
			} catch (Exception e)
			{
				showStateMsg("״̬����̳߳����쳣�˳�");
				e.printStackTrace();
			}
		}
	}

	/**
	 * ʹ��SmProxyӦ�ó���,�����¼ϵͳ
	 */
	void initbutton_actionPerformed(ActionEvent e)
	{
		// �ж�ϵͳ�Ƿ��Ѿ���ʼ����
		if (loginSmProxy)
		{
			showStateMsg("ϵͳ�Ѿ���ʼ��");
			return;
		}

		try
		{
			/*
			 * �Բ��������޸�
			 * 
			 */

			args.put("login-name", loginName.getText().trim());
			args.put("login-pass", loginPass.getText().trim());
			showStateMsg("ϵͳ���ڳ�ʼ��");
			smp = new MySGIPSMProxy(this, args);
			showStateMsg("ϵͳ��ʼ���ɹ�");
			loginSmProxy = true;
			new MoniterThread().start();
		} catch (Exception ex)
		{
			showStateMsg("ϵͳ��ʼ��ʧ��");
			this.myProxyState.setText(ex.getMessage());
			ex.printStackTrace();
			loginSmProxy = false;
		}
	}

	/**
	 * �رռ����˿�
	 */
	void stopbutton_actionPerformed(ActionEvent e)
	{
		try
		{
			smp.stopService();
		} catch (Exception ex)
		{
		}
	}

	void myProxyState_actionPerformed(ActionEvent e)
	{

	}
}
