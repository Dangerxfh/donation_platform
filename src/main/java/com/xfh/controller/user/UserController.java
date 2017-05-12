package com.xfh.controller.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xfh.model.Do_Record;
import com.xfh.model.Project;
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
	
	@RequestMapping(value="/tologin")
	public String toLogin(HttpServletRequest request){
		String url=request.getHeader("Referer");
		request.getSession().setAttribute("url", url);
		//if(url.indexOf("login")>0)
		System.out.println("tologin==="+url);
		return "user/user_login";
	}
	
	@RequestMapping(value="toregister")
	public String toRegister(){
		return "user/user_register";
	}
	//登陆
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public String login(@ModelAttribute User user,HttpSession session,ModelMap map) throws Exception{
		String url=(String) session.getAttribute("url");
		System.out.println("login==="+url);
		User cur_user=userService.userLogin(user);
    	if(cur_user!=null){
    		session.setAttribute("user",cur_user);
    		if(cur_user.getUser_Name().equals("admin"))
    			return "redirect:/admin/project/list/all/1";
    		if(url==null)
    			return "forward:/beforeindex";	
    		return "redirect:"+url;
    	}
    	map.put("login",false);
		return "user/user_login";
	}
    
    //注册
    @RequestMapping(value="/register",method=RequestMethod.POST)
    public String UserRegister(@ModelAttribute User user) throws Exception{
    	//System.out.println(user.getUser_Name());
    	if(userService.userRegister(user)==true){
    		return "/user/user_login";    		
    	}
    	return "/user/user_register";
    	
    }
    
    //注销
    @RequestMapping(value="/logout")
    public String UserLogout(HttpServletRequest request){
    	String url=request.getHeader("Referer");
    	System.out.println("logout===="+url);
    	request.getSession().setAttribute("user",null);
    	if(url==null || url.indexOf("user/detail")>0)
    		return "forward:/beforeindex";
    	return "redirect:"+url;
    }
    
    //捐款
  	@RequestMapping(value="/donate/{id}",method=RequestMethod.POST)
  	public String donate(@PathVariable Integer id,@ModelAttribute Do_Record do_Record) throws Exception{
  		userService.donate(id, do_Record);
  		return "redirect:/project/detail/"+id;
  	}
    //个人中心
    @RequestMapping(value="/detail/{type}")
    public String userDetail(@PathVariable String type,ModelMap map,HttpSession session){
    	User user=(User) session.getAttribute("user");
    	if(type.equals("projects")){   //如果是查看参与的活动
    		List<Project> projects=new ArrayList<Project>(user.getProById());
    		Collections.sort(projects, new Comparator<Project>() {  
    		    public int compare(Project arg0, Project arg1) {  
    		        return arg1.getId().compareTo(arg0.getId()); // 按照id排列  
    		    }  
    		});  
    		//将参与的活动列表注入到页面
    		map.addAttribute("person_projects", projects);
    		return "user/user_detail";
    	} 
    	else if(type.equals("money")){  //如果是查看捐出的善款
    		List<Do_Record> do_Records=new ArrayList<Do_Record>(user.getRecById());
    		Collections.sort(do_Records, new Comparator<Do_Record>() {  
    		    public int compare(Do_Record arg0, Do_Record arg1) {  
    		        return arg1.getId().compareTo(arg0.getId()); // 按照id排列  
    		    }  
    		});  
    		//获取捐出的总钱数
    		Integer person_money=0;
    		for(Do_Record do_Record : do_Records){
    			person_money+=do_Record.getMon_Number();
    		}
    		//将捐款记录列表和捐款总数注入到页面
    		map.addAttribute("person_record", do_Records);
    		map.addAttribute("person_money",person_money);
    		return "user/user_detail_money";
    	}
    	else if(type.equals("myinfo")){  //如果是查看个人信息
    		return "user/user_detail_myinfo";
    	}	
    	return "index";
    }
    
    //修改个人信息
    @RequestMapping(value="/update")
    public String userUpdate(@ModelAttribute User user,ModelMap map,HttpSession session) throws Exception{
    	User sessionUser=(User) session.getAttribute("user");
    	user.setProById(sessionUser.getProById());
    	user.setRecById(sessionUser.getRecById());
    	System.out.println(userService.userUpdate(user)+"==========");
    	if(userService.userUpdate(user)==true)
    		session.setAttribute("user", user);
    	else
    		map.addAttribute("msg_update",false);
    	System.out.println(((User)session.getAttribute("user")).getUser_Name()+"********");
    		return "user/user_detail_myinfo";
    }
    
}
