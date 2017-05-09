package com.xfh.service.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xfh.dao.IBaseDao;
import com.xfh.model.User;
import com.xfh.service.user.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private IBaseDao<User> userDao;
	
	//登陆
	@Override
	public boolean UserLogin(User user) {
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("user_Name",user.getUser_Name());
		params.put("user_Pass",user.getUser_Pass());
		
		List<User> users=userDao.getByParams(1,User.class,params);
		if(users.isEmpty())
			return false;
		return true;
	}

	//注册
	@Override
	public boolean UserRegister(User user) throws Exception {
		List<User> users=userDao.getByParam(User.class,"user_Name",user.getUser_Name());
		if(!users.isEmpty())
			return false;
		userDao.save(user);
		return true;
	}

	//获取用户信息
	@Override
	public User getUser(String param, Object value) throws Exception {
		List<User> users=userDao.getByParam(User.class,param,value);
		if(users==null)
			return null;
		return users.get(0);
	}


}
