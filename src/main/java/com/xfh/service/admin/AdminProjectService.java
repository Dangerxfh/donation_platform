package com.xfh.service.admin;

import com.xfh.model.Project;

public interface AdminProjectService {
	//添加活动
	public boolean addProject(Project project) throws Exception;
	
	//修改活动
	public boolean updateProject(Project project) throws Exception;
	//删除活动
	public boolean deleteProject(Project project);
}
