package com.controller.system;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.entity.Menu;
import com.service.system.MenuService;

import net.sf.json.JSONArray;

@Controller
@RequestMapping(value="/menu")
public class MenuController {

	@Resource(name = "menuService")
	private MenuService menuService;
	
	/**
	 * 显示菜单列表
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping
	public String list(Model model) throws Exception{
		List<Menu> menuList = menuService.listAllParentMenu();
		model.addAttribute("menuList", menuList);
		return "menus";
	}
	
	/**
	 * 请求新增菜单页面
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/add")
	public String toAdd(Model model) throws Exception{
		List<Menu> menuList = menuService.listAllParentMenu();
		model.addAttribute("menuList", menuList);
		return "menus_info";
	}
	
	/**
	 * 请求编辑菜单页面
	 * @param menuId
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/edit")
	public String toEdit(@RequestParam Integer menuId,Model model) throws Exception{
		Menu menu = menuService.getMenuById(menuId);
		model.addAttribute("menu", menu);
		if(menu.getParentId()!=null && menu.getParentId().intValue()>0){
			List<Menu> menuList = menuService.listAllParentMenu();
			model.addAttribute("menuList", menuList);
		}
		return "menus_info";
	}
	
	/**
	 * 保存菜单信息
	 * @param menu
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/save")
	public String save(Menu menu,Model model) throws Exception{
		menuService.saveMenu(menu);
		model.addAttribute("msg", "success");
		return "save_result";
	}
	
	/**
	 * 获取当前菜单的所有子菜单
	 * @param menuId
	 * @param response
	 * @throws Exception 
	 */
	@RequestMapping(value="/sub")
	public void getSub(@RequestParam Integer menuId,HttpServletResponse response) throws Exception{
		List<Menu> subMenu = menuService.listSubMenuByParentId(menuId);
		JSONArray arr = JSONArray.fromObject(subMenu);
		PrintWriter out;
		try {
			response.setCharacterEncoding("utf-8");
			out = response.getWriter();
			String json = arr.toString();
			out.write(json);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除菜单
	 * @param menuId
	 * @param out
	 * @throws Exception 
	 */
	@RequestMapping(value="/del")
	public void delete(@RequestParam Integer menuId,PrintWriter out) throws Exception{
		menuService.deleteMenuById(menuId);
		out.write("success");
		out.flush();
		out.close();
	}
}
