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

public class RemoveOnServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			ServletOutputStream out=resp.getOutputStream();
			Gson gson=new Gson();
		try {
			String id=req.getParameter("id");
			PageServlet.list.remove(id);
			//save
//			SerializeInfo.save(PageServlet.list);
			DbUtils.remove(id);
			out.write(gson.toJson("success").getBytes("UTF-8"));
		} catch (Exception e) {
			out.write(gson.toJson("fail").getBytes("UTF-8"));
			e.printStackTrace();
		}finally{
			out.flush();
		}
	}

}
