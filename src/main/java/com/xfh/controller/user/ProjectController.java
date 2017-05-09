package com.xfh.controller.user;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xfh.model.Project;
import com.xfh.service.user.ProjectService;

@Controller
@RequestMapping("/project")
public class ProjectController {
	@Resource
	private ProjectService projectService;
	
	@RequestMapping(value="/list/{type}/{page}")
	public String pageProject(@PathVariable("type")String type,@PathVariable("page")Integer page,ModelMap map) throws Exception{
		Integer cur_page=projectService.getProjectByPage(type,page);
		System.out.println("cur_page:"+cur_page);
		map.addAttribute("page",cur_page);
		map.addAttribute("type",type);
		return "jsp/project/pro_list";
	}
	
}
