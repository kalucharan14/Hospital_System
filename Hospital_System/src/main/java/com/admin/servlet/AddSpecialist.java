package com.admin.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Db.DBConnect;
import com.dao.SpecialistDao;

@WebServlet("/addSpecialist")
public class AddSpecialist extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name=req.getParameter("specName");
		
	try {
		SpecialistDao dao=new SpecialistDao(DBConnect.getConn());
		boolean br=dao.addSpecialist(name);
		HttpSession session=req.getSession();
		
		if(br) {
			session.setAttribute("sucMesg", "add specilist sucessfull");
			resp.sendRedirect("admin/index.jsp");
		}
		else {
			session.setAttribute("eroMesg", "some mistak on serverl");
			resp.sendRedirect("admin/index.jsp");
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		
	}
	
}
