package com.my.demo;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.my.demo.test.DbUtils;
import com.my.demo.test.SerializeInfo;
import com.sun.xml.internal.bind.v2.runtime.Name;

public class PageServlet extends HttpServlet {
	public final static Map<String, ScreenInfo> list=new LinkedHashMap<String, ScreenInfo>();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
//		super.doGet(req, resp);
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
//		super.doPost(req, resp);
		UUID uuidString=UUID.randomUUID();
		ScreenInfo info=new ScreenInfo();
		info.setId(uuidString.toString());
		String contentString=req.getParameter("content");
		info.setContent(contentString);
		String name=req.getParameter("name");
		info.setName(name);
		info.setCreatetime(new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		list.put(uuidString.toString(),info);
		//save
//		SerializeInfo.save(PageServlet.list);
		DbUtils.save(info);
		Gson g=new Gson();
//		g.toJson(list);
//		resp.getOutputStream().write(g.toJson(list).getBytes());
		resp.getOutputStream().write(g.toJson(info).getBytes("UTF-8"));
		resp.getOutputStream().flush();
	}

}
