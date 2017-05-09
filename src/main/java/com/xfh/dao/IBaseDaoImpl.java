package com.xfh.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.xfh.util.HibernateUtil;

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
		
	//
	@Override
	public void save(T t){
		getSession().save(t);
	}
	
	
	//鏍规嵁鍙傛暟鏌ヨ
	@Override
	public  List<T> getByParam(Class t,String param,Object value)throws Exception{

		List<T> tList=getSession().createCriteria(t).add(Restrictions.eq(param, value)).list();
		return tList;
	}
	
	//type=1:精确查询/type=2:模糊查询
	@Override
	public List<T> getByParams(int type,Class t,Map<String,Object> params){
	
		List<T> tList=null;
		//鍒涘缓 Criteria
		Criteria criteria=getSession().createCriteria(t);
		//鏌ヨ
		for(String param:params.keySet()){
			if(type==1)
			 tList=criteria.add(Restrictions.eq(param, params.get(param))).list();
			if(type==2)
				tList=criteria.add(Restrictions.ilike(param, (String) params.get(param),MatchMode.ANYWHERE)).list();
		}
		return tList;
	}

	//
	@Override
	public void delete(T t) {
		getSession().delete(t);
	}
	
	//鏇存柊鎿嶄綔
	@Override
	public void update(T t) {
		getSession().update(t);
	}

	//or鏌ヨ
	@Override
	public List<T> getWithOr(Class t, String param, List<?> list) {
		List<T> tList=null;
		//鍒涘缓 Criteria
		Criteria criteria=getSession().createCriteria(t);
		tList=criteria.add(Restrictions.in(param,list)).list();
		return tList;
	}

	//鏌ヨ鎵�湁璁板綍
	@Override
	public List<T> getAll(Class t) {
		List<T> tList=null;
		//鍒涘缓 Criteria
		Criteria criteria=getSession().createCriteria(t);
		tList=criteria.list();
		return tList;
	}

}
