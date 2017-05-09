package com.xfh.service.user;

import java.util.List;

import com.xfh.model.Project;

public interface ProjectService {
	//查询所有活动
	public List<Project> getAllProject();
	
	//获取历史捐赠的总钱数
	public Integer getAllMoney(List<Project> projects);
	
	//获取历史捐款总人次
	public Integer getAllPeople(List<Project> projects);
	
	//根据参数获取活动
	public Project getProjectByparam(String param,Object value)throws Exception;
	
	//活动分页
	public Integer getProjectByPage(String type,Integer page)throws Exception;
}
