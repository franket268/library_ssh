<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="JS/j2.js"></script>

<%
ArrayList<HashMap<String, Object>> borrowlist=new ArrayList<HashMap<String,Object>>();
HashMap<String, Object> preOrderRecord=new HashMap<String, Object>();

borrowlist=(ArrayList<HashMap<String, Object>>)request.getAttribute("borrowlist");
preOrderRecord=(HashMap<String, Object>)request.getAttribute("preOrderRecord");
String p_name;

%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Library Book Vending Machine System</title>
</head>
<body>
<%@include file="top.jsp"%>
<h1 align="center">My Loan Record </h1>
<table style="border:solid;vertical-align:middle; text-align:center;" cellpadding=5 border="1" align="center">
    <tr style="background-color: powderblue;">
        <td >book id</td>
        <td>name</td>
        <td>ISBN</td>
        <td>check out date</td>
        <td>expire date</td>
        <td>return</td>
    </tr>
    
<%    
    if(borrowlist.size()!=0)
    {
    	    int i=0; 
            for(HashMap<String, Object> hashmap :borrowlist)
            {
            	i++;
            	int bookId=(Integer)hashmap.get("bookId");
                String name=(String)hashmap.get("name");
                String ISBN=(String)hashmap.get("ISBN");
                String checkOutDate=(String)hashmap.get("checkOutDate");
                String expireDate=(String)hashmap.get("expireDate");
   
 %>  
    <tr>
      <td id="returnBoooId"><%=bookId%></td>
     <td><%=name %></td>
     <td><%=ISBN%></td>
     <td><%=checkOutDate%></td>
     <td><%=expireDate%> </td>
     <td><input type="button" value="return" onclick="returnbook(document.getElementById('returnBoooId'),document.getElementById('userId_label'))"></td>

   </tr>  
           
<%        
     }
}

    else{
%>
<tr><th colspan="6">no loan record </th></tr>
<%
    }
%>
</table>

<h1 align="center">My Pre-order Record </h1>
<table style="border:solid;vertical-align:middle; text-align:center;" cellpadding=5 border="1" align="center">
    <tr style="background-color: powderblue;">
        <td >book id</td>
        <td>name</td>
        <td>ISBN</td>
        <td>preOrderTime</td>
        <td>availableTime</td>
        <td>cancel pre-order</td>
    </tr>
 
 <%   
     if(preOrderRecord.get("name")!=null)
     {
 
 %>  
	<tr>
		<td id="preOrderId" ><%=preOrderRecord.get("bookId")%></td>
		<td><%=preOrderRecord.get("name") %></td>
		<td><%=preOrderRecord.get("ISBN")%></td>
		<td><%=preOrderRecord.get("preOrderTime")%></td>
		<td><%=preOrderRecord.get("availableTime")%> </td>
		<td><input type="button"value="cancel" onclick="cancel_preOrder(document.getElementById('preOrderId'),document.getElementById('userId_label'))"></td>

	</tr>      
 <%
     }
     else
     {
 %>
<tr><th colspan="6">no pre-order record </th></tr>
<%
}
%>
</table>
</body>
</html>