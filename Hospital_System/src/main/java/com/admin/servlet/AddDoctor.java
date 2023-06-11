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
import com.dao.DoctorDao;
import com.entity.Doctor;

@WebServlet("/addDoctor")
public class AddDoctor extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name=req.getParameter("fullname");
		String dob=req.getParameter("dob");
		String qualification=req.getParameter("qualifiaction");
		String email=req.getParameter("email");
		String specialist=req.getParameter("spec");
		String mobNo=req.getParameter("mobno");
		String password=req.getParameter("password");
		Doctor doctor=new Doctor(name,dob,qualification,specialist,email,mobNo,password);
		try {
			DoctorDao dao=new DoctorDao(DBConnect.getConn());
			boolean rs=dao.regissterDoctor(doctor);
			HttpSession session=req.getSession();
			if(rs) {
				session.setAttribute("dObj", "doctor added Succesfully");
				resp.sendRedirect("admin/doctor.jsp");
			}
			else {
				session.setAttribute("dObj", "some error");
				resp.sendRedirect("admin/doctor.jsp");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
