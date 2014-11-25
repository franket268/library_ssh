<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="JS/j2.js"></script>

<%
     ArrayList<HashMap<String, Object>> notice=new ArrayList<HashMap<String, Object>>();
     notice=(ArrayList<HashMap<String, Object>>)session.getAttribute("late_notice");
%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Library Book Vending Machine System</title>
</head>
<body>
<%@include file="top.jsp"%>

 <s:set name="userRole" value="1" />   
<s:if test="#session.user.role==2">
    <s:set name="userRole" value="2" />   
</s:if> 


<!-- prevent other users to access this page -->
<s:if test="userRole==1" >
	request.getRequestDispatcher("homepage.jsp").forward(request,response);
</s:if>

<div align="center">
   <h1>Late Return Notice：</h1> <br>
   <table style="border:solid;vertical-align:middle; text-align:center;" cellpadding=5 border="1">
   <tr  style="background-color: powderblue">
        <td>user name</td>
        <td>book id</td>
        <td>book ISBN</td>
        <td>book name</td>
  </tr>
  
 
<%
   if(notice!=null)
   {
   for(HashMap<String, Object> hashmap :notice)
            {
            	String name=(String)hashmap.get("username");
                String bookId=(String)hashmap.get("bookId");
                String ISBN=(String)hashmap.get("ISBN");
                String bookname=(String)hashmap.get("bookname");

%> 
 
   <tr>
    <td><%=name%></td>
    <td><%=bookId%></td>
    <td><%=ISBN%></td>
    <td> <%=bookname%></td>

   </tr>  
 <%
            }
   }
   else{
	 
 %>
 <tr>
 <th colspan="4">There is no late notice</th>
 </tr>  
 <%
 }
%>
   </table>
<br>
<input type="button" value="Display the notice" onclick="display_notice(1)">
<input type="button" value="Cancel the display" onclick="display_notice(0)">
</div>
</body>
</html>