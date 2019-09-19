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
import com.revature.grading.dao.UserDao;
import com.revature.grading.dao.UserDaoImpl;
import com.revature.grading.model.SubjectWise;


public class Subjectwise extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	
		UserDao dao =new UserDaoImpl();
  		List<SubjectWise> list = null;
		String sub="ENG";
		
			try {
				list = dao.findBySubject();
				System.out.println("list"+list);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		// convert list to json
		Gson gson = new Gson();
		String json = gson.toJson(list);
		System.out.println("json"+json);
        //   write the json as response
		PrintWriter out = response.getWriter();
		out.write(json);
		out.flush();

	}
public static void main (String [] args) throws ServletException, IOException {
	Subjectwise wise = new Subjectwise();
	wise.doGet(null, null);
	
}
}