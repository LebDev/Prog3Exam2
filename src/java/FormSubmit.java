/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author fouadbalhawan
 */
@WebServlet(urlPatterns = {"/FormSubmit"})
public class FormSubmit extends HttpServlet {

   private Statement statement;
	private Connection con;
	private String URL = "jdbc:mysql://localhost:3306/webformtest";

	public void init() throws ServletException
	{
		super.init();
	try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL,"root","root");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}
        public void doGet(HttpServletRequest request,	HttpServletResponse response)throws ServletException,IOException
        {
            String lname,fname,mi,telephone,email,street,city,state,zip;
		lname = request.getParameter("lastName");
		fname = request.getParameter("firstName");
		mi = request.getParameter("mi");
                telephone = request.getParameter("telephone");
                email = request.getParameter("email");
                street = request.getParameter("street");
                 city = request.getParameter("city");
                  state = request.getParameter("state");
                   zip = request.getParameter("zip");
                

		response.setContentType("text/HTML");
		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<TITLE>Servlet + JDBC</TITLE>");
		out.println("</HEAD>");
		out.println("<BODY>");

                String insertion = "INSERT INTO `webformtest`.`user1` (`Id`, `lastName`, `firstName`, `mi`, `telephone`, `email`, `street`, `city`, `state`, `zip`) VALUES (NULL, '"+lname+"', '"+fname+"', '"+mi+"', '"+telephone+"', '"+email+"', '"+street+"', '"+city+"', '"+state+"', '"+zip+"')";
		try
		{
			statement = con.createStatement();
			statement.executeUpdate(insertion);
			statement.close();	//Ensures committal.
		}
		catch (SQLException e)
		{
			out.println("<BR><CENTER><H2>Unable to execute insertion!</H2></CENTER>");
			out.println("</BODY>");
			out.println("</HTML>");
			out.flush();
			System.exit(1);
		}
        }

	
	public void destroy()
	{
		try
		{
			con.close();
		}
		catch(Exception e)
		{
			System.out.println("Error on closing database!");
			e.printStackTrace();
			System.exit(1);
		}
	}

}
