package com.my.demo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class HoldCaseServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id=req.getParameter("id");
		ScreenInfo info=PageServlet.list.get(id);
//		req.setAttribute("info", info);
//		req.getRequestDispatcher("mainjsp.jsp").forward(req, resp);
		Gson gson=new Gson();
		resp.getOutputStream().write(gson.toJson(info).getBytes("UTF-8"));
		resp.getOutputStream().flush();
	}

}
