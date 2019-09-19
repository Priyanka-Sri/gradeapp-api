package controller;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.revature.grading.dao.RegisterDao;
import com.revature.grading.model.Admindetails;
 
public class Controller {
 
    public static String login(String name, String password) {
         
        String errorMessage = null;
        
        RegisterDao dao =new RegisterDao();
        Admindetails user  = new Admindetails();
        try {
        	user=dao.ad1login(name,password);
             if(user == null) {
            	 System.out.println("invalid");
             }
        } catch (Exception e) {
            //e.printStackTrace();
            errorMessage = e.getMessage();
        }       
         
        // Prepare JSON Object
        String json = null;
        Gson gson = new Gson();
        if( user != null) {
             json = gson.toJson(user);
        }
        else if ( user == null ) {
            JsonObject obj = new JsonObject();
            obj.addProperty("errorMessage", errorMessage);
            json = obj.toString();
        }
         
         
        return json;
         
    }
     
    public static String register(String sname,int regno,int eng, int mat,int computer,int science,int social,int total,double avg, String grade) {
		
    	String errorMessage = null;
        String Message = null;
        Admindetails enterdetails=new Admindetails();
        
        try {
        	 
  	       enterdetails.setSname(sname);
  	       enterdetails.setRegNo(regno);
  	       enterdetails.setENG(eng);
  	       enterdetails.setMAT(mat);
  	       enterdetails.setCOM(computer);
  	       enterdetails.setScience(science);
  	       enterdetails.setSocial(social);
  	     enterdetails.setTotal(total);
  	   enterdetails.setAvg(avg);
  	 enterdetails.setGrade(grade);
  	       
  	       
  	       RegisterDao.register(sname, regno, eng, mat, computer, science, social, total, avg, grade);
  	      Message="sucess";
             }
         catch (Exception e) {
            e.printStackTrace();
            errorMessage = e.getMessage();
        }
        // Prepare JSON Object
        JsonObject obj = new JsonObject();
        if (Message != null) {
            obj.addProperty("MESSAGE:", Message);
        } else if (errorMessage != null) {
            obj.addProperty("errorMessage", errorMessage);
        }
        return obj.toString();
    	
    }
    
    public static String list() {
		return null;
    	
    }
    
    public static void main(String[] args) {
    	testregister();
    
    }
    public static void testregister() {    
    System.out.println("Test Case 1: Valid User");
        //String validUserJson = Controller.register("deepa",1,56,76,87,98,87 );
        //System.out.println(validUserJson);
         
        
    }
}