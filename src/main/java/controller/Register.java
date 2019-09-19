package com.revature.grading.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.revature.grading.dao.RegisterDao;
import com.revature.grading.exception.DBException;
import com.revature.grading.model.Admindetails;
//@WebServlet("/Register")
public class Register extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        //Get Inputs
        String name=request.getParameter("name"); 
        String password=request.getParameter("Password");
        
        String mobile= request.getParameter("phno");
        String role = request.getParameter("role");
         
        PrintWriter out = response.getWriter();
          System.out.println("UserName:" +name );
          System.out.println("phoneNo:" +mobile);
           System.out.println("Password:" + password);
        
           out.println("UserName:" +name );
           out.println("phoneNo:" +mobile);
           out.println("Password:" + password);
         
           Admindetails user= new Admindetails();
           user.setNAME(name);
           user.setPASSWORD(password);
           user.setMOBILENO(Integer.parseInt(mobile));
         int mobileno=Integer.parseInt(mobile);
        
          //To call dao
           try {
        	   if("Admin".equals(role)) {
			RegisterDao.admin( name,password, mobileno, role);
        	   }
        	   else if ("Teacher".equals(role))
        	   {
        		   RegisterDao.tech(name,password, mobileno, role);
        	   }
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
             
           // Convert list to json
        Gson gson= new Gson();
        String json = gson.toJson(user);
        out.print(json);   
        out.flush();

    }
}