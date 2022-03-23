package backend;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "display", urlPatterns = {"/display"})
public class display extends HttpServlet {
Connection dbconnection;
ResultSet resultset;
@Override
public void init() throws ServletException{
try
{
Class.forName("org.apache.derby.jdbc.ClientDriver");
}
catch(ClassNotFoundException cnfe)
{
System.err.println("Error loading driver: " + cnfe);
}
try
{
dbconnection =DriverManager.getConnection("jdbc:derby://localhost:1527/dbandservlet","root","root");
}
catch(SQLException sqle)
{
System.err.println("Connection error: " + sqle);
}
}
protected void processRequest(HttpServletRequest request,
HttpServletResponse response)
throws ServletException, IOException {
response.setContentType("text/html;charset=UTF-8");
try (PrintWriter out = response.getWriter()) {
/* TODO output your page here. You may use following sample
code. */
out.println("<!DOCTYPE html>");
out.println("<html>");
out.println("<head>");
out.println("<title>Servlet display</title>");
out.println("</head>");
out.println("<body>");
out.println("<h3>ID &nbsp; NAME</h3>");
try
{
Statement statement = dbconnection.createStatement();
String sqlString = "SELECT * FROM TABLE1";
resultset=statement.executeQuery(sqlString);
while(resultset.next())
{
out.println(resultset.getString("id")+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
out.println(resultset.getString("name") + "<br>");
}
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
