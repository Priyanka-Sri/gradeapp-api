package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Teachlogin {
	private static final long serialVersionUID = 1L;
       
    	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		String UserName = request.getParameter("UserName");
    		String Password = request.getParameter("PassWord");
            PrintWriter out = response.getWriter();
            
            String json = Controller.login(UserName, Password);
           out.write(json);
           out.flush();
    	}

	
}
