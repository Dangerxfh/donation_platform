package com.xfh.service.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfh.dao.IBaseDao;
import com.xfh.model.Project;
import com.xfh.service.admin.AdminProjectService;

@Service("adminProService")
public class AdminProjectServiceImpl implements AdminProjectService{

	@Autowired
	private IBaseDao<Project> projectDao;
	//添加活动
	@Override
	public boolean addProject(Project project) throws Exception {
	
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
	public boolean deleteProject(Project project) {
		
		return false;
	}

}
