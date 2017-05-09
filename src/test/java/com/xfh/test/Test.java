package com.xfh.test;

import static org.junit.Assert.*;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class Test {

	@org.junit.Test
	public void  textSche(){
		/*创建外键单向*/
		new SchemaExport(new Configuration().configure()).create(true,true);
	}

}
