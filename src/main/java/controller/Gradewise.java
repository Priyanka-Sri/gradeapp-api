package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.revature.grading.dao.GradeDao;
import com.revature.grading.dao.GradeDaoImpl;
import com.revature.grading.exception.DBException;
import com.revature.grading.model.Admindetails;

//@WebServlet("/GradeWise")
public class Gradewise extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String grade = request.getParameter("grade");
		System.out.println("Grade:" + grade);
		GradeDao GradeDao=new GradeDaoImpl();
		List<Admindetails> list = null;
		try {
			list = GradeDao.login(grade);		
			} catch (SQLException e) {
			e.printStackTrace();
		} catch (DBException e) {
			e.printStackTrace();
		}
		// convert list to json
		Gson gson = new Gson();
		String json = gson.toJson(list);
        //   write the json as response
		PrintWriter out = response.getWriter();
		out.write(json);
		out.flush();

	}

}