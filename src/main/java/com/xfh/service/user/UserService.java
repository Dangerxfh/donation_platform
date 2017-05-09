package com.xfh.service.user;

import com.xfh.model.User;

public interface UserService {
	//登陆
	public User UserLogin(User user);
	
	//注册
	public boolean UserRegister(User user) throws Exception;
	
	//获取用户信息
	public User getUser(String param,Object value)throws Exception;
}
