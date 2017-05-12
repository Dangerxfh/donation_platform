package com.xfh.controller.admin;

import java.io.File;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

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
	
	//跳转到修改活动页面
	@RequestMapping(value="/update/{id}")
	public String toUpdate(@PathVariable Integer id,ModelMap map,HttpSession session) throws Exception{
		Project project=projectService.getProjectByparam("id",id);
		map.addAttribute("project", project);
		System.out.println(session.getAttribute("pro_update")+"========");
		map.addAttribute("pro_update", session.getAttribute("pro_update"));
		return "admin/alterproject";
	}
	
	//修改活动
	@RequestMapping(value="/updateP",method=RequestMethod.POST)
	public String updateProject(@ModelAttribute Project project,HttpSession session) throws Exception{
		if(adminProService.updateProject(project)==false)
			session.setAttribute("pro_update",false);
		return "redirect:/admin/project/update/"+project.getId();
		
	}
	//上传图片
	@RequestMapping(value="/uploadimg",method=RequestMethod.POST)
	@ResponseBody
	public void uploadImg(HttpServletRequest request,@RequestParam("id") Integer id,@RequestParam("pic") MultipartFile file) throws Exception, ServletException{
	    
		 String picPath = null;  
		 String fileName = file.getOriginalFilename();
      
         //获取项目的部署路径  
         String path = request.getSession().getServletContext().getRealPath("/");  
         if(id!=null){//更新图片
          	picPath = id+".jpg";
          }	
          else{//上传图片
          	picPath = "upload.jpg";  
          	
          	//将刚刚上传的文件路径存在session中方便页面显示  
 	        request.getSession().setAttribute("PIC","img\\"+fileName);
          }
         File targetFile = new File( path+"img",picPath);
         //上传文件
         file.transferTo(targetFile);
            
         try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
	}
	
	
}
