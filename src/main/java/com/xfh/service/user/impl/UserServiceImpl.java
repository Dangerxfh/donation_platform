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
		user=users.get(0);
		Set<Project> projects=new HashSet<Project>(projectDao.getWithMany(Project.class,"userByUserId",user.getId()));
		user.setProById(projects);
		
		Set<Do_Record> do_Records=new HashSet<Do_Record>(recordDao.getByParam(Do_Record.class,"userByUserId",user));
		user.setRecById(do_Records);
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

	//捐款
		@Override
		public void donate(Integer id,Do_Record record) throws Exception {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			
			Project project=null;
			User user=(User) request.getSession().getAttribute("user");
			Set<Project> projects=new HashSet<Project>(projectDao.getWithMany(Project.class,"userByUserId", user.getId()));
			//首先从参与的活动表中查询是否存在与当前id相等的活动
			for(Project cur_project : user.getProById()){
				if(cur_project.getId()==id)
					project=cur_project;
				System.out.println(cur_project.getId()+"=============");
			}
			//如果没有，则从数据库中查询
			if(project==null){
				project=projectDao.getByParam(Project.class,"id",id).get(0);
				//更新参与的活动集合
				projects.add(project);	
			}
			user.setProById(projects);
			userDao.update(user);
			
			project.setPro_CurNumber(project.getPro_CurNumber()+record.getMon_Number());
			project.setPro_CurPeoples(project.getPro_CurPeoples()+1);
			projectDao.update(project);
			
			record.setUserByUserId(user);
			record.setProByProId(project);
			SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
			String do_Time=format.format(new Date());
			record.setDo_Time(do_Time);
			recordDao.save(record);
		}
	//修改个人信息
	@Override
	public boolean userUpdate(User user) throws Exception {
		List<User> users=userDao.getByParam(User.class,"user_Name",user.getUser_Name());
		//System.out.println(users.get(0).getId()+" "+user.getId());
		if(users.isEmpty() || users.get(0).getId()==user.getId()){
			userDao.update(user);	
			return true;
		}
		return false;
	}

	


}
