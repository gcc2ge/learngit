package com.my.demo.test;


import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Test {

	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		URL url=Test.class.getResource("/");
		System.out.println(url.getPath());
		Class.forName("org.hsqldb.jdbcDriver" );  
		Connection c = DriverManager.getConnection("jdbc:hsqldb:file:"+url.getPath()+"database/test;shutdown=true",  
		"sa", "");  
		
//		java.sql.Statement statement =c.createStatement(); 
//		statement.executeUpdate("create table customer(id integer not null primary key,firstname varchar(32),lastname varchar(32))");
//         for (int i = 10; i < 20; i++) {
//             statement.executeUpdate("insert into screeninfo(id,pmhtml,name,content) values(" + i + ",'liu','zhaoyang','content')");
//         }
//         statement.close();
//         c.close();
	}

}
