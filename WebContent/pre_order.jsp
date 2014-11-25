<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="JS/j2.js"></script>
<%
   HashMap<String,Object> pre_orderBook=new HashMap<String,Object>();
   pre_orderBook=(HashMap<String,Object>)session.getAttribute("pre_orderBook");

   int empty_status=0;
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Library Book Vending Machine System</title>
</head>
<body>
<%@include file="top.jsp"%>
<br><br><br>
<h1 align="center">Pre-order book</h1><br>
<h4 align="center">pre-order quota: <s:property value="#session.user.preorder_quota" /></h4>
<label id="preorder_quota" style="visibility:hidden"><s:property value="#session.user.preorder_quota" /></label> 
<table style="border:solid;vertical-align:middle; text-align:center;" cellpadding=5 border="1" align="center">
   <tr  style="background-color: powderblue">
        <td>bookId</td>
        <td>ISBN</td>
        <td>name</td>
        <td>expire date</td>
      
  </tr>
  
<% 
    if(pre_orderBook!=null)
    {
      empty_status=1;
      int bookId=(Integer)pre_orderBook.get("bookId");
      String bookISBN=(String)pre_orderBook.get("bookISBN");
      String bookName=(String)pre_orderBook.get("bookName");
      String expireDate=(String)pre_orderBook.get("expireDate");


%>
  <tr >
        <td id="bookId"><%=bookId %></td>
        <td><%=bookISBN %></td>
        <td><%=bookName %></td>
        <td><%=expireDate %></td>
        
  </tr>
<% 
   }
    else
    {  	
  
%>
    <tr><th colspan="4">no pre-order</th></tr>
<%} %>
  </table>
  <br>
<table align="center">
  <tr>
  
  
 <s:set name="userRole" value="1" />   
<s:if test="#session.user.role==2">
    <s:set name="userRole" value="2" />   
</s:if>  
  
  
   <%
      if(empty_status==1)
      {
   %>
   <s:if test="#userRole==1">
      <td>
       <input type="button" value="submit" onclick="pre_order_submit(document.getElementById('bookId'),document.getElementById('userId_label'),document.getElementById('preorder_quota'))">
      </td>
      <td>
       <input type="button" value="cancel" onclick="pre_order_delete()">
      </td>
    </s:if>
    <%
    }
    %>
  </tr>
</table>  




</body>
</html>