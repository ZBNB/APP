package com.service.system;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.DaoSupport;
import com.entity.Menu;

@SuppressWarnings("unchecked")
@Service("menuService")
public class MenuService{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	public void deleteMenuById(Integer menuId) throws Exception {
		dao.delete("MenuMapper.deleteMenuById", menuId);
	}

	public Menu getMenuById(Integer menuId) throws Exception {
		return (Menu) dao.findForObject("MenuMapper.getMenuById", menuId);
	}

	public List<Menu> listAllParentMenu() throws Exception {
		return (List<Menu>) dao.findForList("MenuMapper.listAllParentMenu", null);
	}

	public void saveMenu(Menu menu) throws Exception {
		// TODO Auto-generated method stub
		if(menu.getMenuId()!=null && menu.getMenuId().intValue()>0){
			dao.update("MenuMapper.updateMenu", menu);
		}else{
			dao.save("MenuMapper.insertMenu", menu);
		}
	}

	public List<Menu> listSubMenuByParentId(Integer parentId) throws Exception {
		return (List<Menu>) dao.findForList("MenuMapper.listSubMenuByParentId", parentId);
	}
	
	public List<Menu> listAllMenu() throws Exception {
		List<Menu> rl = this.listAllParentMenu();
		for(Menu menu : rl){
			List<Menu> subList = this.listSubMenuByParentId(menu.getMenuId());
			menu.setSubMenu(subList);
		}
		return rl;
	}

	public List<Menu> listAllSubMenu() throws Exception{
		return (List<Menu>) dao.findForList("MenuMapper.listAllSubMenu", null);
	}
	
}
