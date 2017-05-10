package com.xfh.service.user;


import com.xfh.model.Do_Record;
import com.xfh.model.User;

public interface UserService {
	//登陆
	public User userLogin(User user)throws Exception;
	
	//注册
	public boolean userRegister(User user) throws Exception;
	
	//更新个人信息
	public boolean userUpdate(User user) throws Exception;
	
	//捐款
	public void donate (Integer id,Do_Record record) throws Exception;
	//获取用户信息
	public User getUser(String param,Object value)throws Exception;

}
