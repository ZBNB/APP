package com.server.http.alidayu;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.taobao.api.ApiException;
import com.taobao.api.Constants;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.internal.util.StringUtils;
import com.taobao.api.request.AlibabaAliqinFcSmsNumQueryRequest;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumQueryResponse;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

@Service("alidayuMSGService")
public class AlidayuMSG {
	private static String appKey = "23734177";
	private static String appSecret = "3b6533c9148f4b3f89616e81103fd308"; // app安全码
	private static String signName = "SMS列侬"; // 短信签名
	private static String smsTemplateCode = "SMS_60050506"; // 短信模板ID
	// 短信模板参数，字符串类型
	private static String smsParams = "{\"name\":\"" + "lennon列侬" + "\"}";
	private static String mobiles = "15720310922"; // 传入多个号码，以英文逗号分隔\
	
	private static String signHmac = "hmac"; // 加密方式
	private static String signMd5 = "md5"; // 加密方式
	private static String sign = null; // 全部参数的hmac或md5的校验码
	private static String apiSendMethod = "alibaba.aliqin.fc.sms.num.send"; // 短信发送
	private static String apiQueryMethod = "alibaba.aliqin.fc.sms.num.query"; // 短信发送记录查询
	private static String serverUrl = " http://gw.api.taobao.com/router/rest"; // 正式环境
	
	public static void main (String[] args) throws IOException, ApiException {

		Map<String, String> params = new HashMap<>();
		params.put("app_key", appKey);
		params.put("format", "json");// format:响应格式。默认为xml格式，可选值：xml，json。
		params.put("method", apiSendMethod);// API接口名称。
		params.put("rec_num", mobiles);
		params.put("sign_method", signHmac); // 签名的摘要算法，可选值为：hmac，md5。
		params.put("timestamp", getTimeStamp());// 时间戳，格式为yyyy-MM-dd HH:mm:ss
		params.put("v", "2.0"); // API协议版本，可选值：2.0。
		params.put("sms_free_sign_name", signName);
		params.put("sms_param", smsParams);
		params.put("sms_template_code", smsTemplateCode);
		params.put("sms_type", "normal");

		sign = signTopRequest(params, appSecret, signHmac);
		//System.out.println(sign);

		TaobaoClient client = new DefaultTaobaoClient(serverUrl, appKey, appSecret);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setSmsType("normal");
		req.setSmsFreeSignName(signName);
		System.out.println(smsParams);
		req.setSmsParamString(smsParams);
		req.setRecNum(mobiles);
		req.setSmsTemplateCode(smsTemplateCode);
		AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
		String resultBody = rsp.getBody();
		JSONObject responseBody = JSON.parseObject(resultBody);
		boolean success = responseBody.getBooleanValue("success");
		
		String currentDate = new SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis());
		String queryEnd = Integer.parseInt(currentDate) + 4 + "";
		AlibabaAliqinFcSmsNumQueryRequest reqQ = new AlibabaAliqinFcSmsNumQueryRequest();
		reqQ.setRecNum("15720310922");
		reqQ.setQueryDate(currentDate);
		reqQ.setCurrentPage(1L);
		reqQ.setPageSize(50L);
		AlibabaAliqinFcSmsNumQueryResponse rspQ = client.execute(reqQ);
		//System.out.println(currentDate + rspQ.getBody());
		reqQ.setQueryDate(queryEnd);
		client.execute(reqQ);
		//System.out.println(queryEnd + rspQ.getBody());
		JSONObject jo = new JSONObject();
		jo.put("responseBody", responseBody);
		jo.put("executeResponse", rspQ.getBody());
		jo.put("success", success);
		System.out.println("==========result==========");
		System.out.println(JSON.toJSONString(jo));
		//return JSON.toJSONString(jo);
	}

	public static String getTimeStamp() {
		// 时间戳，格式为yyyy-MM-dd HH:mm:ss，时区为GMT+8，例如：2015-01-01 12:00:00。
		// 淘宝API服务端允许客户端请求最大时间误差为10分钟。
		long curTime = new Date().getTime();
		// 定义日期的中文输出格式,并输出日期
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
		return df.format(curTime).toString();
	}

	public static String signTopRequest(Map<String, String> params, String secret, String signMethod)
			throws IOException {
		// 第一步：检查参数是否已经排序
		String[] keys = params.keySet().toArray(new String[0]);
		Arrays.sort(keys);

		// 第二步：把所有参数名和参数值串在一起
		StringBuilder query = new StringBuilder();
		if (Constants.SIGN_METHOD_MD5.equals(signMethod)) {
			query.append(secret);
		}
		for (String key : keys) {
			String value = params.get(key);
			if (StringUtils.areNotEmpty(key, value)) {
				query.append(key).append(value);
			}
		}

		// 第三步：使用MD5/HMAC加密
		byte[] bytes;
		if (Constants.SIGN_METHOD_HMAC.equals(signMethod)) {
			bytes = encryptHMAC(query.toString(), secret);
		} else {
			query.append(secret);
			bytes = encryptMD5(query.toString());
		}

		// 第四步：把二进制转化为大写的十六进制
		return byte2hex(bytes);
	}

	public static byte[] encryptHMAC(String data, String secret) throws IOException {
		byte[] bytes = null;
		try {
			SecretKey secretKey = new SecretKeySpec(secret.getBytes(Constants.CHARSET_UTF8), "HmacMD5");
			Mac mac = Mac.getInstance(secretKey.getAlgorithm());
			mac.init(secretKey);
			bytes = mac.doFinal(data.getBytes(Constants.CHARSET_UTF8));
		} catch (GeneralSecurityException gse) {
			throw new IOException(gse.toString());
		}
		return bytes;
	}

	public static byte[] encryptMD5(String data) throws IOException {
		return encryptMD5(data.getBytes(Constants.CHARSET_UTF8).toString());
	}

	public static String byte2hex(byte[] bytes) {
		StringBuilder sign = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(bytes[i] & 0xFF);
			if (hex.length() == 1) {
				sign.append("0");
			}
			sign.append(hex.toUpperCase());
		}
		return sign.toString();
	}
}
