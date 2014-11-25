<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

//requestFrom 表示是从哪个页面请求登陆   
String requestFrom=request.getParameter("requestFrom"); 
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Library Book Vending Machine System</title>


  </head>
  
  <body  >
   <center>
  <img src='img/1.jpg'></img>
  <br>
  <h1>LOGIN</h1>	
 <form name="form" action="<%=path%>/user!login"   method="post">
username：<input type="text" name="user.username"  value=""/> <br>
password:  <input type="password" name="user.password"  value=""/> <br>
<input type="text" name="requestFrom"  value="<%=requestFrom %>" style="visibility:hidden"/><br>
<input type="submit" name="submit" value="submit" />
<input type="reset" name="submit" value="reset" />
</form>
</center>
  </body>
</html>