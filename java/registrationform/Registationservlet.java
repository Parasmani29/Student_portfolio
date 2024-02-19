package registrationform;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Registationservlet
 */
@WebServlet("/Registationservlet")
public class Registationservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		 String uname = request.getParameter("name");
		 String email = request.getParameter("email");
		 String upwd = request.getParameter("pass");
		 String umobile = request.getParameter("contact");
		 
		 RequestDispatcher dispatcher = null;
		 Connection con = null;
		 try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/webpage?useSSL=false", "root", "Paras@123");
			 PreparedStatement pst = con.prepareStatement("insert into users(uname, upwd, email,umobile) values(?,?,?,?)");
			 pst.setString(1, uname);
			 pst.setString(2, upwd);
			 pst.setString(3, email);
			 pst.setString(4, umobile);
			 
			 int rowCount = pst.executeUpdate();
			 dispatcher = request.getRequestDispatcher("registration.jsp");
			 if(rowCount>0) {
				 request.setAttribute("Status", "Success");
				 
				 
			 }
			 
			 else {
				 request.setAttribute("Status", "Failed");
			
			 }
			 
			 dispatcher.forward(request, response);
			 
			 
			 
		 }catch(Exception e) {
			 e.printStackTrace();
			 
		 }
		 
		 finally {
			 try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			 
		 }
		 
		
		
	}

}
