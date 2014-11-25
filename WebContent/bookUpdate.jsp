<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="JS/j2.js"></script>
<script type="text/javascript" src="JS/md5.js"></script>
<%
ArrayList<HashMap<String, Object>> booklist= new ArrayList<HashMap<String, Object>>();
booklist=(ArrayList<HashMap<String, Object>>)request.getAttribute("booklist");
String update_ISBN="";
String update_name="";
String update_author="";
String update_description="";
String update_location="";
HashMap<String, Object> book=new HashMap();

if(request.getAttribute("booklist")!=null)
{
	if(booklist.size()!=0)
	{
	 book=booklist.get(0);
    update_ISBN=(String)book.get("ISBN");
    update_name=(String)book.get("name");
    update_author=(String)book.get("author");
    update_description=(String)book.get("description");
    update_location=(String)book.get("location");
	}
}







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

<label id="pswd" style="visibility:hidden"><s:property value="#session.user.password" /></label>
<div align="center"  >
<h2>Add Book</h2>
<form action="<%=path%>/librarian!addBook"   method="post">
   <table   style="vertical-align:middle; text-align:center;" cellpadding=5 border="1">
      <tr style="background-color: powderblue;">
        <td>ISBN</td>
        <td>name</td>
        <td>author</td>
        <td>description</td>
        <td>location</td>
      </tr>
      <tr>
        <td ><input type="text" name="book.ISBN"></td>
        <td><input type="text" name="book.name"></td>
        <td><input type="text" name="book.author"></td>
        <td><input type="text" name="book.description"></td>
        <td><input type="text" name="book.location"></td>
      </tr>

   </table>
<br>
<input type="submit" value="  Add  " onclick="add_book()">
</form>
</div>
<br><br><br>
<div align="center" >
<h2>Update Book</h2>
  <form name="update_form" action="<%=path%>/librarian!updateBook" method="post">
     
      Input the ISBN: 
      <input type="text" name="search_ISBN" id="search_ISBN">
      <input type="button" value="search" onclick="update_search(document.getElementById('search_ISBN'))">
 <br><br>
   <table   style="vertical-align:middle; text-align:center;" cellpadding=5 border="1">

   <tr style="background-color: powderblue;">
        <td>ISBN</td>
        <td>name</td>
        <td>author</td>
        <td>description</td>
        <td>location</td>
   </tr>
   <tr>
        <td><input type="text" name="book.ISBN" value="<%=update_ISBN%> " readOnly ></td>
        <td><input type="text" name="book.name" value="<%=update_name%>" ></td>
        <td><input type="text" name="book.author" value="<%=update_author%>" ></td>
        <td><input type="text" name="book.description" value="<%=update_description%>" ></td>
        <td><input type="text" name="book.location" value="<%=update_location%>" ></td>
   </tr>
   
   
   
   </table>
<br>
<input type="button" value="  Update  " onclick="update_book(document.getElementById('pswd'))">
<br><br>
<% 
   if(request.getAttribute("booklist")!=null)
    {
	   if(booklist.size()>1)
	   {
		   
	   
%>		   
<table   style="vertical-align:middle; text-align:center;" cellpadding=5 border="1" align="center">
       <tr >
      <td>book Id</td>
      <td>book ISBN</td>
       <td>name</td>
       <td>delete</td>
      </tr>
<%      
    for(int i=1;i<booklist.size();i++)
    {
        book=booklist.get(i);
	   int copyId=(Integer)book.get("copyId");
	   String copyISBN=(String)book.get("copyISBN");
	   int copyStatus=(Integer)book.get("copyStatus");

%>
    <tr>
       <td id="copyId<%=i%>"><%=copyId%></td>
       <td id="copyISBN<%=i%>"> <%=copyISBN%></td>
       <td id="copyName<%=i%>"><%=update_name%></td>
<%        
    if(copyStatus==1)
    {
%>
     <td><input type="button" value="delete" onclick="delete_book(document.getElementById('pswd'),document.getElementById('copyId<%=i%>'))" ></td>
<% 
    }
  }
%>
 </table>
 
<% 
 }
}
%>     

  </form>
</div>
<br><br><br>
</body>
</html>