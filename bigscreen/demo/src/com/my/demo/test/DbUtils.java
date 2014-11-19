package com.my.demo.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import com.my.demo.PageServlet;
import com.my.demo.ScreenInfo;


public class DbUtils {
	public static String path="";
//	D:/workspace/demo/WebContent/database/test
	public static Connection getConnection(){
		Connection c=null;
		try {
			Class.forName("org.hsqldb.jdbcDriver" );  
			c = DriverManager.getConnection("jdbc:hsqldb:file:"+path+";shutdown=true","sa", "");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}  
		return c;
	}
	public static Statement getStatement(){
		try {
			return getConnection().createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void save(ScreenInfo info){
		try {
			Connection connection=getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement("insert into screeninfo(id,pmhtml,name,content,createtime) values(?,?,?,?,?)");
			preparedStatement.setString(1, info.getId());
			preparedStatement.setString(2, info.getPmhtml());
			preparedStatement.setString(3, info.getName());
			preparedStatement.setString(4, info.getContent());
			preparedStatement.setString(5, info.getCreatetime());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	public static void remove(String id) {
		Statement statement=getStatement();
		if(statement!=null){
			try {
				statement.executeUpdate("delete from screeninfo where id='"+id+"'");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}else{
			throw new IllegalArgumentException("异常");
		}
	}
	public static  void update(ScreenInfo info){
		try {
			Connection connection=getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement("update screeninfo set pmhtml=? , name=? ,content=? where id=?");
			preparedStatement.setString(1, info.getPmhtml());
			preparedStatement.setString(2, info.getName());
			preparedStatement.setString(3, info.getContent());
			preparedStatement.setString(4, info.getId());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void loadall(){
		PageServlet.list.clear();
		Statement statement=getStatement();
		ResultSet rs = null;
		if(statement!=null){
			try { 
				rs=statement.executeQuery("select * from screeninfo order by createtime asc");
				while (rs.next()) {
					PageServlet.list.put(rs.getString("id"), new ScreenInfo(rs.getString("id"), rs.getString("pmhtml"), rs.getString("name"), rs.getString("content"),rs.getString("createtime")));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		}else{
			throw new IllegalArgumentException("异常");
		}
	}
}
