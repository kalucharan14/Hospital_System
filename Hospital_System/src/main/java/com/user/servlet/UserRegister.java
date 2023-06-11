package com.user.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Db.DBConnect;
import com.dao.UserDao;
import com.entity.User;

import javax.servlet.http.HttpSession;


@WebServlet("/user_register")
public class UserRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public UserRegister() {
        super();
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name=request.getParameter("fullname");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		User u=new User(name,email,password);
		try {
			UserDao dao=new UserDao(DBConnect.getConn());
			boolean br=dao.userRegister(u);
			HttpSession session=request.getSession();
			if(br) {
				session.setAttribute("succMsg", "Register Sucessfully");
				response.sendRedirect("ulogin.jsp");
			}
			else {
				session.setAttribute("errorMsg", "Somethin wrong here");
				response.sendRedirect("ulogin.jsp");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
