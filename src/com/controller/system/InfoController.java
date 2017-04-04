package com.controller.system;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entity.Info;
import com.entity.Page;
import com.service.system.InfoService;

@Controller
@RequestMapping(value="/info")
public class InfoController {
	
	@Resource(name = "infoService")
	private InfoService infoService;
	
	@RequestMapping
	public String info(Model model,Page page) throws Exception{
		List<Info> infoList = infoService.listPageInfo(page);
		model.addAttribute("infoList", infoList);
		model.addAttribute("page", page);
		return "info";
	}
	
}
