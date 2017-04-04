package com.server.http.webChinese;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

public class SendMsg {
	public static void main(String[] args) throws Exception {
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod("http://utf8.sms.webchinese.cn");
		post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");// 在头文件中设置转码
		NameValuePair[] data = { new NameValuePair("Uid", "ZBNB"),//本站用户名
				new NameValuePair("Key", "24329998e28d0ccdd09f"), new NameValuePair("smsMob", "15720310922"),
				//注册时填写的接口秘钥,如需要加密参数,请把Key变量名改成KeyMD5,KeyMD5=接口秘钥32位MD5加密，大写
				//目的手机号码（多个手机号请用半角逗号隔开）
				new NameValuePair("smsText", "ZBNB接收：手机验证码：12451597474") };//短信内容，最多支持400个字，普通短信70个字/条，长短信64个字/条计费
		post.setRequestBody(data);

		client.executeMethod(post);
		Header[] headers = post.getResponseHeaders();
		int statusCode = post.getStatusCode();
		System.out.println("statusCode:" + statusCode);
		for (Header h : headers) {
			System.out.println(h.toString());
		}
		String result = new String(post.getResponseBodyAsString().getBytes("gbk"));
		System.out.println(result);
		/**
		 * 短信发送后返回值	说　明
		-1	没有该用户账户
		-2	接口密钥不正确 [查看密钥]
		不是账户登陆密码
		-21	MD5接口密钥加密不正确
		-3	短信数量不足
		-11	该用户被禁用
		-14	短信内容出现非法字符
		-4	手机号格式不正确
		-41	手机号码为空
		-42	短信内容为空
		-51	短信签名格式不正确
		接口签名格式为：【签名内容】
		-6	IP限制
		大于0	短信发送数量
		 */

		post.releaseConnection();
	}
}
