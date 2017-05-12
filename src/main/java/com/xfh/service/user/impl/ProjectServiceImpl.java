package com.xfh.service.user.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.xfh.dao.IBaseDao;
import com.xfh.model.Do_Record;
import com.xfh.model.Project;
import com.xfh.service.user.ProjectService;
@Service("projectService")
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private IBaseDao<Project> projectDao;
	
	@Autowired 
	private IBaseDao<Do_Record> recordDao;
	
	//查询所有活动
	@Override
	public List<Project> getAllProject() {
	List<Project> projects=projectDao.getAll(Project.class);
		return projects;
	}

	//获取总钱数
	@Override
	public Integer getAllMoney(List<Project> projects) {
		Integer total_money=0;
		for(Project project : projects)
			total_money+=project.getPro_CurNumber();
		return total_money;
	}

	//获取历史捐款总人次
	@Override
	public Integer getAllPeople(List<Project> projects) {
		Integer total_people=0;
		for(Project project : projects)
			total_people+=project.getPro_CurPeoples();
		return total_people;
	}

	//根据参数获取活动
	@Override
	public Project getProjectByparam(String param, Object value) throws Exception {
		List<Project> projects=projectDao.getByParam(Project.class, param, value);	
		return projects.get(0);
	}

	//活动分页
	@Override
	public Integer getProjectByPage(String type,Integer page) throws Exception {
		int page_sum=0;
		int page_num=4;
		List<Project > projects=null;
		if(type.equals("all"))
			projects=projectDao.getAll(Project.class);
		else
			projects=projectDao.getByParam(Project.class,"pro_Status",type);
		if(projects.size()%page_num==0)
			page_sum=projects.size()/page_num;
		else
			page_sum=projects.size()/page_num+1;
		
		List<Project> pageProjectList=new ArrayList<Project>();
		if(page<=1){   //如果页数小于等于1，获取活动列表前page_num个
			Collections.reverse(projects);   //反转列表，以便将时间近的活动放在前边
			for(int i=0;i<page_num && i<projects.size();i++)
				pageProjectList.add(projects.get(i));
			page=1;
		}
		else if(page>=page_sum){ //如果页数大于等于总页数，获取活动列表后page_num个 
			for(int i=0;i<page_num && i<projects.size();i++)
				pageProjectList.add(projects.get(i));
			page=page_sum;
		}
		else{  //页数不超过限制，获取page+1页的page_num个活动
			Collections.reverse(projects);  //反转列表
			for(int i=(page-1)*page_num;i<page*page_num && i<projects.size();i++)
				pageProjectList.add(projects.get(i));
		}
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		request.getSession().setAttribute("page_sum",page_sum);
		request.getSession().setAttribute("pageProjectList", pageProjectList);
		return page;
	}

	//获取与本活动相关的捐款记录列表
	@Override
	public List<Do_Record> getRecords(Project project) throws Exception {
		List<Do_Record> do_Records=recordDao.getByParam(Do_Record.class,"proByProId",project);
		System.out.println("dorec==="+do_Records.size());
		return do_Records;
	}

	
}
