<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
ArrayList<HashMap<String, Object>> display_notice=new ArrayList<HashMap<String, Object>>();
display_notice=(ArrayList<HashMap<String, Object>>)getServletContext().getAttribute("display_notice");

%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Library Book Vending Machine System</title>
</head>

<body >
<%@include file="top.jsp"%>
<br>
<div style=" position:absolute;left:190px;">
<div style="background-image:url('img/bg.jpg')"><br><h4 talign="center">-Late Return Notice-</h4><br></div>
<div>
<marquee  style="background-image:url('img/bg.jpg')" width="180" direction='up' scrollamount='3' onMouseOut='this.start()' onMouseOver='this.stop()' height=200px' >
<%

    if(display_notice!=null)
    {
    	 for(HashMap<String, Object> hashmap :display_notice)
         {
         	String name=(String)hashmap.get("username");
             String bookId=(String)hashmap.get("bookId");
             String ISBN=(String)hashmap.get("ISBN");
             String bookname=(String)hashmap.get("bookname");
%>
username:<%=name%><br>
book ISBN:<%=ISBN %><br>
book name:<%=bookname%><br><br><br><br>
<%
         }
    }
%>

</marquee>
</div>
<br><br><br><br><br>
</div>
<div style=" position:relative;  top:60px; left:40%;color: #8968CD;"  >
<h1 >Welcome to our library system
</h1></div>
<div style=" position:relative;  top:80px; left:70%" >

<img src=img/book.jpg    height="180px"  >
</div>

</body>
</html>