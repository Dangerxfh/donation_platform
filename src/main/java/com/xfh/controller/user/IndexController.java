package com.xfh.controller.user;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xfh.model.Project;
import com.xfh.service.user.ProjectService;

@Controller
public class IndexController {
	@Resource
	private ProjectService projectSevice;
	
	@RequestMapping(value="/beforeindex")
	public String beforeIndex(ModelMap map,HttpSession session){
		List<Project> projects=projectSevice.getAllProject();
		Collections.reverse(projects);
		Integer total_money=projectSevice.getAllMoney(projects);
		Integer total_people=projectSevice.getAllPeople(projects);
		
		session.setAttribute("projects",projects);
		map.addAttribute("total_money",total_money);
		map.addAttribute("total_people",total_people);
		
		Map<String,String> pro_status=new HashMap<String,String>();
		pro_status.put("donate","正在募捐");    //将状态存到map中
		pro_status.put("execute","正在执行");
		pro_status.put("end","已结束");
		session.setAttribute("pro_status",pro_status);
		return "index";
	}
}
