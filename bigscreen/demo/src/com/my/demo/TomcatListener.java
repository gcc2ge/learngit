package com.my.demo;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.my.demo.test.DbUtils;

public class TomcatListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
//		System.out.println(arg0.getServletContext().getRealPath("/"));
		System.out.println(arg0.getServletContext().getInitParameter("databaseurl"));
		DbUtils.path=arg0.getServletContext().getInitParameter("databaseurl")+"/test";
	}

	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
//		SerializeInfo.save(PageServlet.list);
//		DbUtils.loadall();
		
	}


}