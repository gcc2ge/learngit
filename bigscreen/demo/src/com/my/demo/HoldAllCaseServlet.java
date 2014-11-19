package com.my.demo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.my.demo.test.DbUtils;

public class HoldAllCaseServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Gson gson=new Gson();
		//load all
		DbUtils.loadall();
		resp.getOutputStream().write(gson.toJson(PageServlet.list).getBytes("UTF-8"));
		resp.getOutputStream().flush();
	}

}
