package com.xfh.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by Fog on 2017/4/10.
 * 数据库持久层操作接口
 */
public interface IBaseDao<T> {
		//保存
		public void save(T t);
		
		//根据具体值查询
		public List<T> getByParam(Class t,String param,Object value)throws Exception;
		
		//多参数查询
		public  List<T> getByParams(int type,Class t,Map<String,Object> params);
		
		//or查询
		public List<T> getWithOr(Class t,String param,List<?> list);
		
		//查询所有记录
		public List<T> getAll(Class t);
		
		//删除
		public void delete(T t);
		
		//修改
		public void update (T t);
}
