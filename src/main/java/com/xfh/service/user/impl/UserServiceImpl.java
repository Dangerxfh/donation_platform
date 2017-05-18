package com.xfh.service.user.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.xfh.dao.IBaseDao;
import com.xfh.model.Do_Record;
import com.xfh.model.Project;
import com.xfh.model.User;
import com.xfh.service.user.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private IBaseDao<User> userDao;
	
	@Autowired
	private IBaseDao<Project> projectDao;
	
	@Autowired
	private IBaseDao<Do_Record> recordDao;
	//登陆
	@Override
	public User userLogin(User user) throws Exception {
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("user_Name",user.getUser_Name());
		params.put("user_Pass",user.getUser_Pass());
		
		List<User> users=userDao.getByParams(1,User.class,params);
		if(users.isEmpty())
			return null;
		user=users.get(0);
		
		getProjects(user);
		getRecord(user);
		
		return user;
	}

	//注册
	@Override
	public boolean userRegister(User user) throws Exception {
		List<User> users=userDao.getByParam(User.class,"user_Name",user.getUser_Name());
		if(!users.isEmpty())
			return false;
		userDao.save(user);
		return true;
	}

	//获取用户信息
	@Override
	public User getUser(String param, Object value) throws Exception {
		List<User> users=userDao.getByParam(User.class,param,value);
		if(users==null)
			return null;
		return users.get(0);
	}

	//修改个人信息
		@Override
		public boolean userUpdate(User user) throws Exception {
			List<User> users=userDao.getByParam(User.class,"user_Name",user.getUser_Name());
			if(users.isEmpty() || users.get(0).getId()==user.getId()){
				userDao.update(user);	
				return true;
			}
			return false;
		}

	//捐款
		@Override
		public void donate(Integer id,Do_Record record) throws Exception {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		
			User user=(User) request.getSession().getAttribute("user");
			Set<Project> projects=new HashSet<Project>(projectDao.getWithMany(Project.class,"userByUserId", user.getId()));
		
			Project project=projectDao.getByParam(Project.class,"id",id).get(0);
			
			//更新参与活动总数和总人数
			project.setPro_CurNumber(project.getPro_CurNumber()+record.getMon_Number());
			project.setPro_CurPeoples(project.getPro_CurPeoples()+1);
			projectDao.update(project);
			
			//更新用户（包括参与的活动集合和捐款记录集合）
			projects.add(project);
			user.setProById(projects);
			Set<Do_Record> do_Records=user.getRecById();
			do_Records.add(record);
			user.setRecById(do_Records);
			userDao.update(user);
			
			//保存捐款记录
			record.setUserByUserId(user);
			record.setProByProId(project);
			SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
			String do_Time=format.format(new Date());
			record.setDo_Time(do_Time);
			recordDao.save(record);
		}

		//获取用户参与的活动集合，并赋值
		@Override
		public void getProjects(User user ) {
			Set<Project> projects=new HashSet<Project>(projectDao.getWithMany(Project.class,"userByUserId",user.getId()));
			
			user.setProById(projects);
		}
	
		//获取用户参与的捐款极了集合
		@Override
		public void getRecord(User user) throws Exception{
			Set<Do_Record> do_Records=new HashSet<Do_Record>(recordDao.getByParam(Do_Record.class,"userByUserId",user));
			user.setRecById(do_Records);
		}

}
