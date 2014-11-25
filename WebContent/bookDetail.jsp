<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="JS/j2.js"></script>
<%@taglib uri="/struts-tags" prefix="s" %>
<% 

ArrayList<HashMap<String, Object>> book_detail_list= new ArrayList<HashMap<String, Object>>();
book_detail_list=(ArrayList<HashMap<String, Object>>)session.getAttribute("book_detail_list");

ArrayList<HashMap<String, Object>> check_out_list= new ArrayList<HashMap<String, Object>>();
check_out_list=(ArrayList<HashMap<String,Object>>)session.getAttribute("check_out_list");

%>


<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta HTTP-EQUIV=”Cache-Control” CONTENT=”no-cache”>
<title>Library Book Vending Machine System</title>
</head>

<body>

<%@include file="top.jsp"%>
<br><br>
<table style="border:solid;vertical-align:middle; text-align:center;" cellpadding=5 border="1" align="center">
   <tr  style="background-color: powderblue">
        <td>ISBN</td>
        <td>name</td>
        <td>author</td>
        <td>description</td>
        <td>location</td>
   </tr>

<% 
   HashMap<String, Object> map=new HashMap<String, Object>();
   map=book_detail_list.get(0);
   String bookISBN=(String)map.get("ISBN");
   String bookName=(String)map.get("name");
   String bookauthor=(String)map.get("author");
   String bookdescription=(String)map.get("description");
   String booklocation=(String)map.get("location");
%>   
   
   <tr>
        <td><s:property value="book.ISBN"/></td>
        <td><s:property value="book.name"/></td>
        <td><s:property value="book.author"/></td>
        <td style="word-wrap:break-word:" width="250"><s:property value="book.description"/></td>
        <td><s:property value="book.location"/></td>
        
   </tr>
  
</table>
<% 
   if(book_detail_list.size()>1)
   {

		 	   
%>	

<br><br>
<table   align="center" style="border:solid;vertical-align:middle; text-align:center;" cellpadding=5 border="1">
   <tr>
      <td>book ID</td>
      <td>book ISBN</td>
       <td>name </td>
      
      <td>borrow</td>
      <td>pre-order</td>
   </tr>
<% 


   //显示所有copy的详情
  for(int i=1;i<book_detail_list.size();i++)
  {
	  map=book_detail_list.get(i);
	  int copyId=(Integer)map.get("copyId");
	  String copyISBN=(String)map.get("copyISBN");
	 // int isAvailable=(Integer)map.get("copyIsAvailable");
	 int copyStatus=(Integer)map.get("copyStatus");
	 int checkout_status=1;
	 
	 //判断该书是否已经加入checkoulist
	 if(check_out_list!=null)
	 {
		 for(HashMap<String, Object> hashmap :check_out_list)
         {
         	int bookId=(Integer)hashmap.get("bookId");
            if (copyId==bookId)             
            {
            	checkout_status=0;   //0 means copy has been added to checkoutlist
            }
         }
	 
	 }
%>
    
    <tr>
       <td id="copyId<%=i%>"><%=copyId%></td>
       <td id="copyISBN<%=i%>"> <%=copyISBN%></td>
       <td id="copyName<%=i%>"><%=bookName%></td>
       <label id="checkout_status<%=i%>" style="visibility:hidden"><%=checkout_status%>  </label>
       
       
<s:set name="userRole" value="1" />   
<s:if test="#session.user.role==2">
    <s:set name="userRole" value="2" />   
</s:if>
       
       
       
       
<%      
     //non_borrowed&non_added_to_checkoulist&user is not a librarian; 
    if(copyStatus==1&&checkout_status==1) 
    {
%> 

<s:if test="#userRole==1">
     <td id="add<%=i%>"><input type="button" value="add to checkout list" onclick="add_checkoutlist(document.getElementById('copyId<%=i%>'),document.getElementById('copyName<%=i%>'),document.getElementById('copyISBN<%=i%>'))"></td>
</s:if>

<%
    }    
    else if(copyStatus==2)
    {
%>
<s:if test="#userRole==1"> 
  
  
  <td></td>
  <td ><input type="button" value="pre-order" onclick="pre_orderBook(document.getElementById('copyId<%=i%>'),document.getElementById('copyName<%=i%>'),document.getElementById('copyISBN<%=i%>'))"></td>
</s:if>
<%     	
    }
    
 %>   	
    </tr>
<%
  }
}
%>

</table>
</body>
</html>