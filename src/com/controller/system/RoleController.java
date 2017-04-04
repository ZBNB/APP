package com.controller.system;

import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.entity.Menu;
import com.entity.Role;
import com.service.system.MenuService;
import com.service.system.RoleService;
import com.util.RightsHelper;
import com.util.Tools;

import net.sf.json.JSONArray;

@Controller
@RequestMapping(value="/role")
public class RoleController {
	
	@Resource(name = "roleService")
	private RoleService roleService;
	@Resource(name = "menuService")
	private MenuService menuService;
	
	/**
	 * 显示角色列表
	 * @param map
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping
	public String list(Map<String,Object> map) throws Exception{
		List<Role> roleList = roleService.listAllRoles();
		map.put("roleList", roleList);
		return "roles";
	}
	
	/**
	 * 保存角色信息
	 * @param out
	 * @param role
	 * @throws Exception 
	 */
	@RequestMapping(value="/save")
	public void save(PrintWriter out,Role role) throws Exception{
		boolean flag = true;
		if(role.getRoleId()!=null && role.getRoleId().intValue()>0){
			flag = roleService.updateRoleBaseInfo(role);
		}else{
			flag = roleService.insertRole(role);
		}
		if(flag){
			out.write("success");
		}else{
			out.write("failed");
		}
		out.flush();
		out.close();
	}
	
	/**
	 * 请求角色授权页面
	 * @param roleId
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/auth")
	public String auth(@RequestParam int roleId,Model model) throws Exception{
		List<Menu> menuList = menuService.listAllMenu();
		Role role = roleService.getRoleById(roleId);
		String roleRights = role.getRights();
		if(Tools.notEmpty(roleRights)){
			for(Menu menu : menuList){
				menu.setHasMenu(RightsHelper.testRights(roleRights, menu.getMenuId()));
				if(menu.isHasMenu()){
					List<Menu> subMenuList = menu.getSubMenu();
					for(Menu sub : subMenuList){
						sub.setHasMenu(RightsHelper.testRights(roleRights, sub.getMenuId()));
					}
				}
			}
		}
		JSONArray arr = JSONArray.fromObject(menuList);
		String json = arr.toString();
		json = json.replaceAll("menuId", "id").replaceAll("menuName", "name").replaceAll("subMenu", "nodes").replaceAll("hasMenu", "checked");
		model.addAttribute("zTreeNodes", json);
		model.addAttribute("roleId", roleId);
		return "authorization";
	}
	
	/**
	 * 保存角色权限
	 * @param roleId
	 * @param menuIds
	 * @param out
	 * @throws Exception 
	 */
	@RequestMapping(value="/auth/save")
	public void saveAuth(@RequestParam int roleId,@RequestParam String menuIds,PrintWriter out) throws Exception{
		BigInteger rights = RightsHelper.sumRights(Tools.str2StrArray(menuIds));
		Role role = roleService.getRoleById(roleId);
		role.setRights(rights.toString());
		roleService.updateRoleRights(role);
		out.write("success");
		out.close();
	}
}
