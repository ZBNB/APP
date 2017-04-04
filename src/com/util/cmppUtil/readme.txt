说明：下面说明是以java开发为例
1.将common文件夹、MsgConfig.properties放于src根目录下。
2.修改MsgConfig.properties配置文件对应的内容为可用参数。已做修改
3.方法入口：
 common.msg.util.MsgContainer
 sendWapPushMsg(String url,String desc,String cusMsisdn)：发送web push短信；
 sendMsg(String msg,String cusMsisdn)：发送SMS
5.依赖包见lib目录