<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="JS/j2.js"></script>




<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Library Book Vending Machine System</title>
</head>
<body>
<%@include file="top.jsp"%>


<s:set name="quota_statu" value="0" />
<s:if test="#session.check_out_list!=null" >
  <s:if test="#session.user.quota>=#session.check_out_list.size()" >
     <s:set name="quota_statu" value="1" /> 
  </s:if>
</s:if>


<br>
 <h1 align="center">Checkout List：</h1> 
 <h4 align="center">quota:<s:property value="#session.user.quota"/></h4>
 <label id="quota_status" style="visibility:hidden"><s:property value="#quota_statu"/></label>
 <label id="quota_label" style="visibility:hidden"><s:property value="#session.user.quota"/></label>

 <br><br>
<table style="border:solid;vertical-align:middle; text-align:center;" cellpadding=5 border="1" align="center">
   <tr  style="background-color: powderblue">
        <td>book ID</td>
        <td>book name</td>
        <td>book ISBN</td>
     
        <td>delete</td>
  
  </tr>
<s:if test="#session.check_out_list!=null" >
    <s:if test="#session.check_out_list.size()!=0">
    	<s:set name="empty_status" value="1"/>
    	<%int i=0;%>
    	<s:iterator value="#session.check_out_list" var="check_out_book">    		
		    <% i++; %>
    
    
    
   <tr>  
   <td id="checkout_bookId<%=i%>"><s:property value="#check_out_book.get('bookId')" /></td>
   <td><s:property value="#check_out_book.get('bookName')" /></td>
   <td><s:property value="#check_out_book.get('bookISBN')" /></td>

   <td><input type="button" value="delete" onclick="delele_checkout(document.getElementById('checkout_bookId<%=i%>'))"></td>

       
    </tr>
  </s:iterator>
  </s:if>

    <s:else>   
	    <tr><th colspan="4"> the check-out list is empty!</th></tr>  
    </s:else>
 </s:if>   
<s:else>
<tr><th colspan="4"> the check-out list is empty!</th></tr>
</s:else>

</table>
<br>
<table align="center">
  <tr>
  
<s:set name="userRole" value="1" />   
<s:if test="#session.user.role==2">
    <s:set name="userRole" value="2" />   
</s:if>  
  
<s:if test="#userRole==1&&#empty_status==1">  
    
     <td> <input type="button" value="submit" onclick="submit_checkoutList(document.getElementById('userId_label'),document.getElementById('quota_status'),document.getElementById('quota_label'))"></td>
</s:if>
<s:if test="#request.from.equals('top')==false&&#empty_status==1" >


  
     <td> <input type="button" value="back to the homepage" onclick="goback()"></td>
</s:if>


  </tr>
</table>


</head> 
</body>
</html>