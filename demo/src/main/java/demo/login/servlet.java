package demo.login;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.xml.crypto.Data;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/login")
public class servlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private ServletRequest request;
	//private ServletResponse response;

	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		String i = req.getParameter("num1");
		String j = req.getParameter("num2");
		System.out.println("hai");
		PrintWriter out = res.getWriter();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			List<Data> list = new ArrayList<Data>();
			int cnt = 0;

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/supermark", "root", "Sairaju@9");
			Statement stmt = con.createStatement();
			//System.out.println("insert records");
			// String sql="insert into login values(46,'sai')";
			String sql = "select id,pass from login where id=" + i + " and pass='" + j + "'";
			// stmt.execute(sql);
			ResultSet resultSet = stmt.executeQuery(sql);
			while (resultSet.next()) {
				cnt = cnt + 1;
			}
			if (cnt == 1) {
				
				RequestDispatcher rd = req.getRequestDispatcher("page2.html");
				rd.forward(req, res);
			} else {
				out.println("Invalid id and password");
			}
			System.out.println("");
			stmt.close();
			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}

	}

}
