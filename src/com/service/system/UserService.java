package com.service.system;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.DaoSupport;
import com.entity.User;

@SuppressWarnings("unchecked")
@Service("userService")
public class UserService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	public User getUserById(Integer userId) throws Exception {
		return (User) dao.findForObject("UserMapper.getUserById", userId);
	}

	public boolean insertUser(User user) throws Exception {
		int count = (int) dao.findForObject("UserMapper.getCountByName", user.getLoginname());
		if(count>0){
			return false;
		}else{
			dao.save("UserMapper.insertUser", user);
			return true;
		}
		
	}

	public List<User> listPageUser(User user) throws Exception{
		return (List<User>) dao.findForList("UserMapper.listPageUser", user);
	}

	public void updateUser(User user) throws Exception {
		dao.update("UserMapper.updateUser", user);
	}

	public void updateUserBaseInfo(User user) throws Exception{
		dao.update("UserMapper.updateUserBaseInfo", user);
	}
	
	public void updateUserRights(User user) throws Exception{
		dao.update("UserMapper.updateUserRights", user);
	}
	
	public User getUserByNameAndPwd(String loginname, String password) throws Exception {
		User user = new User();
		user.setLoginname(loginname);
		user.setPassword(password);
		return (User) dao.findForObject("UserMapper.getUserInfo", user);
	}
	
	public void deleteUser(int userId) throws Exception{
		dao.delete("UserMapper.getUserInfo", userId);
	}

	public User getUserAndRoleById(Integer userId) throws Exception {
		return (User) dao.findForObject("UserMapper.getUserAndRoleById", userId);
	}

	public void updateLastLogin(User user) throws Exception {
		dao.update("UserMapper.updateLastLogin", user);
	}

	public List<User> listAllUser() throws Exception {
		return (List<User>) dao.findForList("UserMapper.listAllUser", null);
	}
	
}
