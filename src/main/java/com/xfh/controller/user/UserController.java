package com.xfh.controller.user;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xfh.model.User;
import com.xfh.service.user.UserService;

/**
 * Created by Watson on 2017/4/10.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
	@Resource
	private UserService userService;
	
	//登陆
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public String login(@ModelAttribute User user,HttpSession session,ModelMap map){
		String url=(String) session.getAttribute("url");
		User cur_user=userService.UserLogin(user);
    	if(cur_user!=null){
    		session.setAttribute("user",cur_user);
    		if(url==null)
    			return "forward:/beforeindex";
    		return "redirect:./"+url;
    	}
    	map.put("login",false);
		return "jsp/user/user_login";
	}
    
    //注册
    @RequestMapping(value="/register",method=RequestMethod.POST)
    public String UserRegister(@ModelAttribute User user) throws Exception{
    	//System.out.println(user.getUser_Name());
    	if(userService.UserRegister(user)==true){
    		return "/jsp/user/user_login";    		
    	}
    	return "jsp/user/user_register";
    	
    }
    
    //注销
    @RequestMapping(value="logout")
    public String UserLogout(HttpSession session){
    	String url=(String) session.getAttribute("url");
    	session.setAttribute("user",null);
    	if(url==null)
    		return "forward:/beforeindex";
    	return "redirect:./"+url;
    }
}
