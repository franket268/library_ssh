<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="JS/j2.js"></script>

<%
  HashMap<String,Object> usage_report= new HashMap<String,Object>();
usage_report=(HashMap<String,Object>)request.getAttribute("usage_report");

ArrayList<HashMap<String, Object>> loan_record=new ArrayList<HashMap<String, Object>>();
loan_record=(ArrayList<HashMap<String, Object>>)request.getAttribute("loan_record");

ArrayList<HashMap<String, Object>> pre_order_record=new ArrayList<HashMap<String, Object>>();
pre_order_record=(ArrayList<HashMap<String, Object>>)request.getAttribute("pre_order_record");
%>



<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Library Book Vending Machine System</title>
</head>
<body>
<%@include file="top.jsp"%>
<!-- prevent other users to access this page -->
<s:if test="userRole==1" >
	request.getRequestDispatcher("homepage.jsp").forward(request,response);
</s:if>

<br>
<div align="center"  >
<h2>Usage Report</h2>
<table style="border:solid;vertical-align:middle; text-align:center;" cellpadding=5 border="1">
   <tr style="background-color: powderblue;">
      <td>loan_record_count</td>
      <td>not_return_count</td>
      <td>pre_order_count</td>
      <td>not_finish_count</td>
      <td>time</td>
   </tr>
   
<%
    if (usage_report!=null)
    {
       int  loan_record_count=(Integer)usage_report.get("loan_record_count");
       int not_return_count=(Integer)usage_report.get("not_return_count");
       int pre_order_count=(Integer)usage_report.get("pre_order_count");
       int not_finish_count=(Integer)usage_report.get("not_finish_count");
       String time=(String)usage_report.get("time");
    	
    
%>
    <tr>
      <td><%=loan_record_count %></td>
      <td><%=not_return_count %></td>
      <td><%=pre_order_count %></td>
      <td><%=not_finish_count %></td>
      <td><%=time %></td>
    
    </tr>
 <%
    }
 %>
 
</table>
</div>

<div align="center"  >
<h2>Loan Report</h2>
<table style="border:solid;vertical-align:middle; text-align:center;" cellpadding=5 border="1">
   <tr style="background-color: powderblue;">
      <td>book ISBN</td>
      <td>user name</td>
      <td>check out date</td>
      <td> return</td>
      <td>return date</td>
      <td>expire date</td>
   </tr>
   
 <%
   for(HashMap<String, Object> hashmap :loan_record)
    {
	 String ISBN=(String)hashmap.get("ISBN");
 	 String  userName=(String)hashmap.get("userName");
     String checkoutDate=(String)hashmap.get("checkOutDate");
     int  isReturn=(Integer)hashmap.get("isReturn");
     String ifReturn="";
     if(isReturn==0)
     {
    	  ifReturn="no";
     }
     else
    	  ifReturn="yes"; 
     
     String returnDate=(String)hashmap.get("returnDate");
     String expireDate=(String)hashmap.get("expireDate");
 
 
 %>  
   <tr>
      <td><%=ISBN %></td>
      <td><%=userName %></td>
      <td><%=checkoutDate %></td>
      <td><%=ifReturn %></td>
      <td><%=returnDate %></td>
      <td><%=expireDate %></td>
   </tr>
   <%} %>
</table>
</div>

<div align="center"  >
<h2>Pre-order Report</h2>
<table style="border:solid;vertical-align:middle; text-align:center;" cellpadding=5 border="1">
   <tr style="background-color: powderblue;">
      <td>book ISBN</td>
      <td>user name</td>
      <td>pre-order time</td>
      <td>is finish</td>
   </tr>
   
 <%
   for(HashMap<String, Object> hashmap :pre_order_record)
    {
	 String ISBN=(String)hashmap.get("ISBN");
 	 String  userName=(String)hashmap.get("userName");
     String preOrderTime=(String)hashmap.get("preOrderTime");
     int  isFinish=(Integer)hashmap.get("isFinish");
     String ifFinish="";
     if(isFinish==0)
     {
    	 ifFinish="no";
     }
     else
    	 ifFinish="yes"; 
     

 
 
 %>  
   <tr>
      <td><%=ISBN %></td>
      <td><%=userName %></td>
      <td><%=preOrderTime %></td>
      <td><%=ifFinish %></td>

   </tr>
   <%} %>
</table>
<br><br><br>
</div>
</body>
</html>