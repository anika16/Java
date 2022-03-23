package backend;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class insert extends HttpServlet {
Connection dbconnection;
ResultSet resultset;
@Override
public void init() throws ServletException {
ServletConfig config = getServletConfig();
String driverClassName = "org.apache.derby.jdbc.ClientDriver";
String dbURL;
dbURL = "jdbc:derby://localhost:1527/dbandservlet";
String username = "root";
String dbpassword = "root";
try
{
Class.forName(driverClassName);
}
catch(ClassNotFoundException cnfe)
{
System.err.println("Error loading driver: " + cnfe);
}
try
{
dbconnection = DriverManager.getConnection(dbURL, username,
dbpassword);
}
catch(SQLException sqle)
{
System.err.println("Connection error: " + sqle);
}
}




protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet insert</title>");            
            out.println("</head>");
            out.println("<body>");
            try{
                PreparedStatement pstmt =dbconnection.prepareStatement("INSERT INTO TABLE1"+"(id, name)VALUES(?,?)");
                 pstmt.setInt(1,Integer.parseInt(request.getParameter("id")));
                 pstmt.setString(2,request.getParameter("name"));
                  out.println("<h1>Record inserted!</h1>");
                 pstmt.executeUpdate();  
             
            } 
  
        catch(SQLException sqle)
        {
            System.err.println("Connection error: " + sqle);
        }
            out.println("</body>");
            out.println("</html>");
        }
    }



@Override
protected void doGet(HttpServletRequest request, HttpServletResponse
response)
throws ServletException, IOException {
processRequest(request, response);
}
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse
response)
throws ServletException, IOException {
processRequest(request, response);
}
@Override
public String getServletInfo() {
return "Short description";
}
}
