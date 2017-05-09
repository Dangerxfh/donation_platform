package com.xfh.controller.user;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xfh.model.Do_Record;
import com.xfh.model.Project;
import com.xfh.service.user.ProjectService;

@Controller
@RequestMapping("/project")
public class ProjectController {
	@Resource
	private ProjectService projectService;
	
	//获取活动列表（type:活动状态，page:页码）
	@RequestMapping(value="/list/{type}/{page}")
	public String pageProject(@PathVariable("type")String type,@PathVariable("page")Integer page,ModelMap map) throws Exception{
		Integer cur_page=projectService.getProjectByPage(type,page);
		System.out.println("cur_page:"+cur_page);
		map.addAttribute("page",cur_page);
		map.addAttribute("type",type);
		return "jsp/project/pro_list";
	}
	
	//根据id获取活动,根据活动获取捐款记录列表
	@RequestMapping(value="/detail/{id}")
	public String getProject (@PathVariable Integer id,ModelMap map) throws Exception{
		Project project=projectService.getProjectByparam("id",id);
		List<Do_Record> do_Records=projectService.getRecords(project);
		
		map.addAttribute("project", project);
		map.addAttribute("do_Records", do_Records);
		return "jsp/project/pro_detail";
	}
	
	//捐款
	@RequestMapping(value="/donate/{id}",method=RequestMethod.POST)
	public String donate(@PathVariable Integer id,@ModelAttribute Do_Record do_Record) throws Exception{
		projectService.donate(id, do_Record);
		return "redirect:/project/detail/"+id;
	}
	
}
