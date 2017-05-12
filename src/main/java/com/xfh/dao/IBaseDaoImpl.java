package com.xfh.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Fog on 2017/4/10.
 */
@Repository
@Transactional
public class IBaseDaoImpl<T> implements IBaseDao<T>{
	@Autowired
	private  SessionFactory sessionFactory;
	
	public  Session getSession(){
		return sessionFactory.getCurrentSession();
	}
		
	//保存
	@Override
	public void save(T t){
		getSession().save(t);
	}
	
	
	//根据参数查询
	@Override
	public  List<T> getByParam(Class t,String param,Object value)throws Exception{
		Criteria criteria=getSession().createCriteria(t);
		criteria.add(Restrictions.eq(param, value));
		//去掉重复元素
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}
	
	//type=1:精确查询/type=2:模糊查询
	@Override
	public List<T> getByParams(int type,Class t,Map<String,Object> params){
	
		List<T> tList=null;
		Criteria criteria=getSession().createCriteria(t);
		for(String param:params.keySet()){
			if(type==1)
				criteria.add(Restrictions.eq(param, params.get(param))).list();
			if(type==2)
				criteria.add(Restrictions.ilike(param, (String) params.get(param),MatchMode.ANYWHERE)).list();
		}
		//去掉重复元素
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		tList=criteria.list();
		return tList;
	}

	//删除
	@Override
	public void delete(T t) {
		getSession().delete(t);
	}
	
	//更新
	@Override
	public void update(T t) {
		getSession().update(t);
	}

	//or查询
	@Override
	public List<T> getWithOr(Class t, String param, List<?> list) {
		List<T> tList=null;
		//鍒涘缓 Criteria
		Criteria criteria=getSession().createCriteria(t);
		tList=criteria.add(Restrictions.in(param,list)).list();
		return tList;
	}

	//获取所有记录
	@Override
	public List<T> getAll(Class t) {
		List<T> tList=null;
		//鍒涘缓 Criteria
		Criteria criteria=getSession().createCriteria(t);
		tList=criteria.list();
		return tList;
	}

	//多对多查询
	@Override
	public List<T> getWithMany(Class t, String table,Object value) {
		Criteria criteria=getSession().createCriteria(t);
		List<T> tlist=null;
                //必需创建一个别名，roles为user中包 含的role 的list.
				criteria.createAlias(table,table);
                //roleId为传进来进行查询的角色ID
				criteria.add(Restrictions.eq(table+".id",value));
				criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
				tlist=criteria.list(); 
		return tlist;
	}

}
