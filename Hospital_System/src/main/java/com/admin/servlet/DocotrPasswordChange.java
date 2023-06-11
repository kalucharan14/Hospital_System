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

@WebServlet("/doctChangePassword")
public class DocotrPasswordChange extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int uid = Integer.parseInt(req.getParameter("uid"));
		String oldPassword = req.getParameter("oldPassword");
		String newPassword = req.getParameter("newPassword");

		DoctorDao dao;
		try {
			dao = new DoctorDao(DBConnect.getConn());
			HttpSession session = req.getSession();

			if (dao.checkOldPassword(uid, oldPassword)) {

				if (dao.changePassword(uid, newPassword)) {
					session.setAttribute("succMsg", "Password Change Sucessfully");
					resp.sendRedirect("doctor/edit_profile.jsp");

				} else {
					session.setAttribute("errorMsg", "Something wrong on server");
					resp.sendRedirect("doctor/edit_profile.jsp");
				}

			} else {
				session.setAttribute("errorMsg", "Old Password Incorrect");
				resp.sendRedirect("doctor/edit_profile.jsp");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
}
