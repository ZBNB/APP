package com.controller.sms.api;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.server.http.alidayu.AlidayuMSG;
import com.taobao.api.ApiException;

@Controller
@RequestMapping(value = "HttpAPIController")
public class HttpAPIController {
	
	@Resource(name = "alidayuMSGService")
	private AlidayuMSG alidayuMSGService;
	
	@RequestMapping(value = "httpAPI", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String httpAPI(String app_key, String smsParams, String mobiles) throws IOException, ApiException{
		String result = "";//alidayuMSGService.alidayuSend(smsParams, mobiles);
		return result;
	}
}
