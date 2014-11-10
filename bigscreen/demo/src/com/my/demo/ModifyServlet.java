package com.my.demo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.my.demo.test.DbUtils;
import com.my.demo.test.SerializeInfo;

public class ModifyServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Gson gson=new Gson();
		ServletOutputStream out=resp.getOutputStream();
		try {
			String id=req.getParameter("id");
			String content=req.getParameter("content");
			String pmhtml=req.getParameter("pmhtml");
			ScreenInfo info=PageServlet.list.get(id);
			info.setContent(content);
			info.setPmhtml(pmhtml);
			//save
//			SerializeInfo.save(PageServlet.list);
			DbUtils.update(info);
			out.write(gson.toJson("success").getBytes("UTF-8"));
		} catch (Exception e) {
			out.write(gson.toJson("fail").getBytes("UTF-8"));
			e.printStackTrace();
		}finally{
			out.flush();
		}
	}

}
