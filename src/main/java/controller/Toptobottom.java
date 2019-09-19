package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.revature.grading.dao.TOBDaoImpl;
import com.revature.grading.dao.TobDao;
import com.revature.grading.exception.DBException;
import com.revature.grading.model.Admindetails;

public class Toptobottom extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		
		
		
	TobDao TOBDao = new TOBDaoImpl();
List<Admindetails> list = null;
try {
	list = TOBDao.login();
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (DBException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	//convert list to json
    Gson gson = new Gson();
    String json = gson.toJson(list);
    
 //   write the json as response
    PrintWriter out = response.getWriter();
    out.write(json);
    out.flush();
	
	}

	

}
