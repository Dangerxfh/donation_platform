package com.xfh.controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.xfh.model.Project;
import com.xfh.service.admin.AdminProjectService;
import com.xfh.service.user.ProjectService;

@Controller
@RequestMapping("/admin/project")
public class AdminController {
	@Autowired
	private ProjectService projectService;
	
	@Autowired 
	private AdminProjectService adminProService;
	//活动列表
	@RequestMapping(value="/list/{type}/{page}")
	public String pageProject(@PathVariable("type")String type,@PathVariable("page")Integer page,ModelMap map) throws Exception{
		Integer cur_page=projectService.getProjectByPage(type,page);
		map.addAttribute("page",cur_page);
		map.addAttribute("type",type);
		return "admin/project_list";
	}
	
	//跳转到添加活动页面
	@RequestMapping(value="/add")
	public String toAdd(@RequestParam(value="addP",required=false) String addP,ModelMap map){
		map.addAttribute("pro_add",addP);
		return "admin/addproject";
	}
	
	//添加活动
	@RequestMapping(value="/addP")
	public String addProject(@ModelAttribute Project project,HttpSession session) throws Exception{
		if(adminProService.addProject(project)==true)
			return "redirect:/admin/project/list/all/1?addP=true";
		else
			return "redirect:/admin/project/add?addP=false";
		
	}
	//跳转到修改活动页面
	@RequestMapping(value="/update/{id}")
	public String toUpdate(@PathVariable Integer id,
						   @RequestParam(value="update",required=false) String update,
						   ModelMap map,HttpServletRequest request) throws Exception{
		Project project=projectService.getProjectByparam("id",id);
		map.addAttribute("project",project);
		map.addAttribute("pro_update",update);
		return "admin/alterproject";
	}
	
	//修改活动
	@RequestMapping(value="/updateP",method=RequestMethod.POST)
	public String updateProject(@ModelAttribute Project project) throws Exception{
		if(adminProService.updateProject(project)==false)
			return "forward:/admin/project/update/"+project.getId()+"?update=false";
		else
		return "forward:/admin/project/update/"+project.getId()+"?update=true";
		
	}
	
	//删除活动
	@RequestMapping(value="/delete/{id}")
	public String deleteProject(@PathVariable Integer id,HttpServletRequest request) throws Exception{
		String url=request.getHeader("Referer");
		adminProService.deleteProject(id);
		return "redirect:"+url;
	}
	
	//上传图片
	@RequestMapping(value="/uploadimg",method=RequestMethod.POST)
	@ResponseBody
	public void uploadImg(HttpServletRequest request,@RequestParam("id") Integer id,@RequestParam("pic") MultipartFile file) throws InterruptedException, IllegalStateException, IOException{
	    
		 String picPath = null;  
         //获取项目的部署路径  
         String path = request.getSession().getServletContext().getRealPath("/");  
         if(id!=null){//更新图片
          	picPath = id+".jpg";
          }	
          else{//上传图片
          	picPath = "upload.jpg";  
          	
          	//将刚刚上传的文件路径存在session中方便页面显示  
          	
          	request.getSession().setAttribute("PIC","img/"+picPath);
          }
         File targetFile = new File( path+"img",picPath);
         //上传文件
       
			file.transferTo(targetFile);
			Thread.sleep(1000);
       
	}
	
	
}
