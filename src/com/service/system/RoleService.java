package com.service.system;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.DaoSupport;
import com.entity.Role;

@SuppressWarnings("unchecked")
@Service("roleService")
public class RoleService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	public List<Role> listAllRoles() throws Exception {
		return (List<Role>) dao.findForList("RoleMapper.listAllRoles", null);
	}

	public void deleteRoleById(int roleId) throws Exception {
		dao.delete("RoleMapper.deleteRoleById", roleId);
	}

	public Role getRoleById(int roleId) throws Exception {
		return (Role) dao.findForObject("RoleMapper.getRoleById", roleId);
	}

	public boolean insertRole(Role role) throws Exception {
		int count = (int) dao.findForObject("RoleMapper.getCountByName", role);
		if(count > 0)
			return false;
		else{
			dao.save("RoleMapper.insertRole", role);
			return true;
		}
	}

	public boolean updateRoleBaseInfo(Role role) throws Exception {
		int count = (int) dao.findForObject("RoleMapper.getCountByName", role);
		if(count > 0)
			return false;
		else{
			dao.update("RoleMapper.updateRoleBaseInfo", role);
			return true;
		}
	}
	
	public void updateRoleRights(Role role) throws Exception {
		dao.update("RoleMapper.updateRoleRights", role);
	}

}
