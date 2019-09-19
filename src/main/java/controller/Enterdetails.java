package controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.revature.grading.dao.RegisterDao;
import com.revature.grading.exception.DBException;
import com.revature.grading.model.Admindetails;
import com.revature.grading.services.UserOperations;

//@WebServlet("/EnterDetails")
public class Enterdetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Get Inputs
		String sname=request.getParameter("name");
		int regno=Integer.parseInt(request.getParameter("Register_No")); //you didn't pass register no from frontend
		int eng=Integer.parseInt(request.getParameter("english"));
		int mat=Integer.parseInt(request.getParameter("maths"));
		int computer=Integer.parseInt(request.getParameter("computer"));
		int science=Integer.parseInt(request.getParameter("science"));
		int social=Integer.parseInt(request.getParameter("social"));
		//int total=Integer.parseInt(request.getParameter("Total"));
		//double avg=Double.parseDouble(request.getParameter("Average"));
		//String grade=request.getParameter("Grade");
		PrintWriter out = response.getWriter();
	      System.out.println("Student_Name:" + sname);
	      System.out.println("Register_No:" + regno);
	      System.out.println("English_Mark:" + eng);
	      System.out.println("Maths_Mark:" + mat);
	      System.out.println("Computer_Mark:" + computer);
	      System.out.println("Science_Mark:" + science);
	      System.out.println("Social_Mark:" + social);
	      //System.out.println("Total:" + total);
	      //System.out.println("Average:" + avg);
	      //System.out.println("Grade:" + grade);
	      
	      String errorMessage = null;
	      
	    //To call dao
	       Admindetails enterdetails=new Admindetails();
	       enterdetails.setSname(sname);
	       enterdetails.setRegNo(regno);
	       enterdetails.setENG(eng);
	       enterdetails.setMAT(mat);
	       enterdetails.setCOM(computer);
	       enterdetails.setScience(science);
	       enterdetails.setSocial(social);
	       int total = enterdetails.getENG() + enterdetails.getMAT() + enterdetails.getCOM() + enterdetails.getScience() + enterdetails.getSocial();
			int noOfSubjects = 5;
			double avg = (double)total / noOfSubjects;
			enterdetails.setTotal(total);
			enterdetails.setAvg(avg);
			
			String grade =  UserOperations.getgrade(total);
			enterdetails.setGrade(grade); 
			System.out.println(enterdetails);
	       try {
	    	    RegisterDao.register(sname, regno, eng, mat, computer, science, social, total, avg, grade);
	    	//    RegisterDao.Marks( sname, regno, eng, mat, computer, science, social, total, avg, grade);
	    	 //  RegisterDao.register(enterdetails);
	       } catch (ClassNotFoundException e) {

			e.printStackTrace();
			errorMessage = e.getMessage();
		} catch (SQLException e) {
			
			e.printStackTrace();
			errorMessage = e.getMessage();
		} catch (DBException e) {
			
			e.printStackTrace();
			errorMessage = e.getMessage();
		}
	       
	    // Convert list to json
	       JsonObject obj = new JsonObject();
	       if (errorMessage != null) {
	    	   obj.addProperty("errorMessage", errorMessage);
	       }
	       else {
	    	   obj.addProperty("message", "Successfully Marks added");
	       }
	       
	       out.write(obj.toString());
	       out.flush();

	       
	}
}
