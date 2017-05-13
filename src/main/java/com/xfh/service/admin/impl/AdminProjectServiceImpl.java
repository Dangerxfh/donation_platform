package com.xfh.service.admin.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		List<Project> projects=projectDao.getByParam(Project.class,"pro_Title",project.getPro_Title());
		if(projects.isEmpty()){
			project.setPro_CurNumber(0);
			project.setPro_CurPeoples(0);
			projectDao.save(project);
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
