package com.controller.sms;

import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.controller.base.BaseController;
import com.entity.Menu;
import com.entity.Page;
import com.entity.User;
import com.service.sms.SmsService;
import com.util.Const;
import com.util.DateUtil;
import com.util.PageData;

@Controller
@RequestMapping(value="/httpAPIJoin")
public class HttpAPIJoinController extends BaseController {
	
	@Resource(name = "smsService")
	private SmsService smsService;
	
	@RequestMapping
	public String list(HttpSession session, Model model, Page page) throws Exception{
		PageData pd = new PageData();
		User user = (User)session.getAttribute(Const.SESSION_USER);
		if (user == null) {
			return "login";
		}
		Integer roleId = user.getRoleId();
		String username = user.getUsername();
		if (roleId == Const.SYSTEM_ROLE_ID) {
			username = "";
		}
		pd.put("username", username);
		page.setPd(pd);
		List<PageData> httpList = smsService.listPageHttpJoinRecord(page);
		model.addAttribute("httpList", httpList);
		return "sms/http_join";
	}
	
	@RequestMapping(value="/add")
	public String toAdd(HttpSession session, Model model) throws Exception{
		List<PageData> apiTypeList = smsService.listAllAPIType();
		User user = (User)session.getAttribute(Const.SESSION_USER);
		if (user == null) {
			return "login";
		}
		String username = user.getUsername();
		PageData pd = new PageData();
		pd.put("username", username);
		model.addAttribute("apiTypeList", apiTypeList);
		model.addAttribute("opType", "add");
		model.addAttribute("httpJoin", pd);
		return "sms/http_join_info";
	}
	
	@RequestMapping(value="/edit")
	public String toEdit(@RequestParam String jr_id,Model model) throws Exception{
		List<PageData> apiTypeList = smsService.listAllAPIType();
		PageData httpJoin = smsService.getHttpJoinRecordById(jr_id);
		model.addAttribute("apiTypeList", apiTypeList);
		model.addAttribute("joinType", httpJoin.getString("api_type"));
		model.addAttribute("opType", "edit");
		model.addAttribute("httpJoin", httpJoin);
		return "sms/http_join_info";
	}
	
	@RequestMapping(value="/save")
	public String save(HttpSession session, Model model) throws Exception{
		String currentTime = DateUtil.getCurrentTime();
		User user = (User)session.getAttribute(Const.SESSION_USER);
		if (user == null) {
			return "login";
		}
		String username = user.getUsername();
		PageData pd = this.getPageData();
		String jrId = pd.getString("jr_id");
		String opType = "edit";
		if(!StringUtils.hasText(jrId)){
			pd.put("jr_id", this.get32UUID());
			pd.put("app_key", this.get32UUID());
			pd.put("username", username);
			pd.put("msg_count", 10);
			opType = "add";
		}
		pd.put("op_time", currentTime);
		smsService.saveHttpJoinRecord(pd, opType);
		model.addAttribute("msg", "success");
		return "save_result";
	}
	
	@RequestMapping(value="/del")
	public void delete(@RequestParam String jr_id, PrintWriter out) throws Exception{
		smsService.deleteHttpJoinRecordById(jr_id);
		out.write("success");
		out.flush();
		out.close();
	}
}
