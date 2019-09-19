package controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;
import com.revature.grading.dao.RangeDao;
import com.revature.grading.dao.RegisterDao;
import com.revature.grading.exception.DBException;
import com.revature.grading.model.Admindetails;
import com.revature.grading.services.UserOperations;

//@WebServlet("/EnterDetails")
public class Rage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Get Inputs
		int max1=Integer.parseInt(request.getParameter("max1")); //you didn't pass register no from frontend
		int max2=Integer.parseInt(request.getParameter("max2"));
		int max3=Integer.parseInt(request.getParameter("max3"));
		int max4=Integer.parseInt(request.getParameter("max4"));
		
		int min1=Integer.parseInt(request.getParameter("min1")); //you didn't pass register no from frontend
		int min2=Integer.parseInt(request.getParameter("min2"));
		int min3=Integer.parseInt(request.getParameter("min3"));
		int min4=Integer.parseInt(request.getParameter("min4"));
		
		String grade1=request.getParameter("grade1");
		String grade2=request.getParameter("grade2");
		String grade3=request.getParameter("grade3");
		String grade4=request.getParameter("grade4");
	
		
		
		
		PrintWriter out = response.getWriter();
	      System.out.println("Maximum Range:" + max1);
	      System.out.println("Maximum Range:" + max2);
	      System.out.println("Maximum Range:" + max3);
	      System.out.println("Maximum Range:" + max4);
	      
	      System.out.println("Minimum Range:" + min1);
	      System.out.println("Minimum Range:" + min2);
	      System.out.println("Minimum Range:" + min3);
	      System.out.println("Minimum Range:" + min4);
	      
	      System.out.println("Grade:" + grade1);
	      System.out.println("Grade:" + grade2);
	      System.out.println("Grade:" + grade3);
	      System.out.println("Grade:" + grade4);
	      
	      
	      String errorMessage = null;
	      
	    //To call dao
	       Admindetails enterdetails=new Admindetails();
	       enterdetails.setMax1(max1);
	       enterdetails.setMax2(max2);
	       enterdetails.setMax3(max3);
	       enterdetails.setMax4(max4);
	       
	       enterdetails.setMin1(min1);
	       enterdetails.setMin2(min2);
	       enterdetails.setMin3(min3);
	       enterdetails.setMin4(min4);
	       
	       enterdetails.setGrade1(grade1);
	       enterdetails.setGrade2(grade2);
	       enterdetails.setGrade3(grade3);
	       enterdetails.setGrade4(grade4);
	       
	       
	      
			 
			System.out.println(enterdetails);
	       try {
	    	   RangeDao.Range( max1,  min1, grade1, max2,  min2, grade2, max3,  min3, grade3, max4, min4, grade4);

	    	
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













/*protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        //response.getWriter().append("Served at: ").append(request.getContextPath());
        
        String grade = request.getParameter("grade");
        String minimum = request.getParameter("min");
        String maximum = request.getParameter("max");
        
        
        int max=Integer.parseInt(maximum);
        int min=Integer.parseInt(minimum);
        
      // GradeValidator gradeValidator = new GradeValidator();
      //  AdminService adminservice = new AdminService();
        String errorMessage = null;
        String status="";
        try {
            gradeValidator.isGradeExist(grade.toUpperCase(), min, max);
            
            adminservice.updateScoreRangeService(grade.toUpperCase(), min, max);
            
            status = "Success";
        }catch (ServiceException e) {
            errorMessage = e.getMessage();
            
        } catch (ValidatorException e) {
            errorMessage = e.getMessage();
        
        }
    
        String json = null;
       //Gson gson = new Gson();
        if(status.equals("Success") ) {
            
            JsonObject obj = new JsonObject();
           obj.addProperty("responseMessage", "score range updated");
           json = obj.toString();
        }else {
            JsonObject obj = new JsonObject();
           obj.addProperty("responseMessage", errorMessage);
           json = obj.toString();
        }
        
        PrintWriter out = response.getWriter();
       out.write(json);
       out.flush();
        
    }

*/