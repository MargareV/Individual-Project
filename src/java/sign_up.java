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
public class sign_up extends HttpServlet {

    

   

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
                String firstname = request.getParameter("fname");
		String username = request.getParameter("uname");
		String phno = request.getParameter("ph");
                String email = request.getParameter("email");
		String city = request.getParameter("city");
		String password = request.getParameter("psw");
                String pswrep = request.getParameter("psw-repeat");
                String serv = request.getParameter("serv");
                if(password.equals(pswrep)== false) {
                
                    out.println("Passwords don\'t Match");
            
            }    
                else {
                try{
        
        //loading drivers for mysql
        Class.forName("com.mysql.jdbc.Driver");
        

	//creating connection with the database 
          Connection  con=DriverManager.getConnection
                     ("jdbc:mysql://localhost:3306/project","root","");

        PreparedStatement ps=con.prepareStatement
                  ("insert into sign_up (first_name, username, phone_number, email, city, password, services) values(?,?,?,?,?,?,?)");

        ps.setString(1, firstname);
        ps.setString(2, username);
        ps.setString(3, phno);
        ps.setString(4, email);
        ps.setString(5, city);
        ps.setString(6, password);
        ps.setString(7, serv);
        int i=ps.executeUpdate();
        
        
          if(i>0)
          {
            response.sendRedirect("client_login.html");
          }
        
        }
        catch(Exception se)
        {
            se.printStackTrace();
        }
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
