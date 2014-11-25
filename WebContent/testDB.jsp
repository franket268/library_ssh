<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% 
java.sql.Connection conn=null;
java.lang.String strConn;
try{
   Class.forName("com.mysql.jdbc.Driver").newInstance();
   conn=java.sql.DriverManager.getConnection("jdbc:mysql://localhost/library_hibernate","root","123");
%>
连接数据库成功！
<%
} catch (java.sql.SQLException e)
  {
	out.println(e.toString());
  }
finally
{
	if(conn!=null) conn.close();
}
%>  


</html>