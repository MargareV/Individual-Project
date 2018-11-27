/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

/**
 *
 * @author margs
 */
public class search_client extends HttpServlet {

   


    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            PrintWriter out = response.getWriter();
            String search = request.getParameter("search");
            
              
        try {
			Class.forName("com.mysql.jdbc.Driver");
		 // loads driver
		Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", ""); // gets a new connection
 
		PreparedStatement ps = c.prepareStatement("select * from freelancers where services_provided=?");
		ps.setString(1, search);
		
 
		ResultSet rs = ps.executeQuery();
                out.println("<html>\n" +
"<head>\n" +
"<style>\n" +
"#customers {\n" +
"    font-family: \"Trebuchet MS\", Arial, Helvetica, sans-serif;\n" +
"    border-collapse: collapse;\n" +
"    width: 100%;\n" +
"}\n" +
"\n" +
"#customers td, #customers th {\n" +
"    border: 1px solid #ddd;\n" +
"    padding: 8px;\n" +
"}\n" +
"\n" +
"#customers tr:nth-child(even){background-color: #f2f2f2;}\n" +
"\n" +
"#customers tr:hover {background-color: #ddd;}\n" +
"\n" +
"#customers th {\n" +
"    padding-top: 12px;\n" +
"    padding-bottom: 12px;\n" +
"    text-align: left;\n" +
"    background-color: #4CAF50;\n" +
"    color: white;\n" +
"}\n" +
"</style>\n" +
"</head>\n" +
"<body>\n" +
"\n" +
"<table id=\"customers\">\n" +
"  <tr>\n" +
"    <th>Username</th>\n" +
"    <th>Email</th>\n" +
"    <th>Phone Number</th>\n" +
                        "<th>Services</th>" +
"  </tr>");
 
		while (rs.next()) {
			String user = rs.getString("username");
                        String email = rs.getString("email");
                        int ph = rs.getInt("phone_number");
                        String serv = rs.getString("services_provided");
                        out.println("<tr>\n" +
"    <td>"+user+"</td>\n" +
"    <td>"+email+"</td>\n" +
"    <td>"+ph+"</td>\n" +
"    <td>"+serv+"</td>\n" +
"  </tr>");
			return;
		}
		out.println("No freelancers available for this service at the moment :/");
		return;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
            
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
