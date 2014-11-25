<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Library Book Vending Machine System</title>
</head>
<body >
<%@include file="top.jsp"%>

 <div align="center">
   <h1>Booklist：</h1> <br>
   <table style="border:solid;vertical-align:middle; text-align:center;" cellpadding=5 border="1">
   <tr  style="background-color: powderblue">
        <td>ISBN</td>
        <td>name</td>
        <td>author</td>
        <td>description</td>
        <td>location</td>

   </tr>

<s:if test="booklist!=null" >   
<s:iterator value="booklist" var="book">
   <tr>
    <td><s:property value="#book.ISBN"/></td>
    <td><a href="<%=path%>/book!getDetail?ISBN=<s:property value='#book.ISBN'/>"><s:property value="#book.name"/></td>
    <td><s:property value="#book.author"/></td>
    <td style="word-wrap:break-word:" width="250"><s:property value="#book.description"/></td>
    <td><s:property value="#book.location"/></td>

   </tr>  
</s:iterator>            
</s:if>

<s:else>    
 <tr><th colspan="5">the result is empty</th></tr>
  
   
</s:else>
</table>
</div>

</body>
</html>