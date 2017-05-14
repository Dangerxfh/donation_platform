package com.xfh.service.admin.impl;

import java.io.File;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.xfh.dao.IBaseDao;
import com.xfh.model.Project;
import com.xfh.model.User;
import com.xfh.service.admin.AdminProjectService;

@Service("adminProService")
public class AdminProjectServiceImpl implements AdminProjectService{

	@Autowired
	private IBaseDao<Project> projectDao;
	
	@Autowired
	private IBaseDao<User> userDao; 
	//添加活动
	@Override
	public boolean addProject(Project project) throws Exception {
		List<Project> projects=null;
		projects=projectDao.getByParam(Project.class,"pro_Title",project.getPro_Title());
		if(projects.isEmpty()){ 
			project.setPro_CurNumber(0);
			project.setPro_CurPeoples(0);
			projectDao.save(project);
			
			//修改活动图片名
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			String basePath = request.getSession().getServletContext().getRealPath("/"); 
			File fileimg=new File(basePath+"img\\upload.jpg");
			projects=projectDao.getByParam(Project.class,"pro_Title",project.getPro_Title());
			fileimg.renameTo(new File(basePath+"img\\"+projects.get(0).getId()+".jpg"));
			return true;
		}
		return false;
	}

	//修改活动
	@Override
	public boolean updateProject(Project project) throws Exception {
		List<Project> projects=projectDao.getByParam(Project.class,"pro_Title",project.getPro_Title());
		if(projects.isEmpty() || projects.get(0).getId()==project.getId()){	
			projectDao.update(project);
			return true;
		}
		return false;
	}

	//删除活动
	@Override
	public void deleteProject(Integer  id) throws Exception {
		Project project =projectDao.getByParam(Project.class,"id",id).get(0);
		Set<User> users=project.getUserByUserId();
		for(User user : users)
			user.getProById().remove(project);
		projectDao.delete(project);
	}

}
