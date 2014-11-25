<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@taglib uri="/struts-tags" prefix="s" %>
<script type="text/javascript" src="JS/top.js"></script>

<% 

   String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

    

    
    
%>     

<label id="userId_label" style="visibility:hidden"><s:property value="#session.user.id" /></label>



<!-- change_label()is the function change the login label  -->
<body onLoad="change_loginLabel(document.getElementById('label1'),document.getElementById('Top_LoginLabel'),document.getElementById('Top_LogoutLabel'))" >

<table width="900" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="110" valign="top" background="img/topbanner2.jpg" bgcolor="#EEEEEE"><table width="100%" height="30"  border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="81%" height="10"></td>
        <td colspan="2"></td>
      </tr>
      <tr>
        <td height="20">&nbsp;</td>
         <td><label id="label1" ><s:property value="#session.user.username" /></label></td>
        <td id="Top_LoginLabel"   width="10%"><a href="login.jsp?requestFrom=top">login</a></td>
        <td id="Top_LogoutLabel"   width="10%">&nbsp;&nbsp;&nbsp;&nbsp;<a href="user!logout">logout</a></td>

        </tr>
    </table>
  <br> <br>   <br>    
   <table  width="93%" height="39"  border="0" cellpadding="0" cellspacing="0">

       


<s:if  test="#session.user.role==2">
          <td align="right">
          
            <input type="button" value="homepage"  style="background:#5f9ea0;width:120px;height:30px;font-size:17px" onclick="to_homepage()">
            <input type="button" value="update book" style="background:#5f9ea0;width:120px;height:30px;font-size:17px" onclick="updatebook()">
            <input type="button" value="late return notice" style="background:#5f9ea0;height:30px;font-size:17px" onclick="generate_notice()">
            <input type="button" value="usage report" style="background:#5f9ea0;width:120px;height:30px;font-size:17px" onclick="generate_usage_report()">
          </td>

          
</s:if>
<s:else>
          <td align="right">
          
            <input type="button"  value="homepage"  style="background:#5f9ea0;width:120px;height:30px;font-size:17px" onclick="to_homepage()">
            <input type="button" value="my check-out list" style="background:#5f9ea0;height:30px;font-size:17px" onclick="checkout()">
            <input type="button" value="my book record" style="background:#5f9ea0;height:30px;font-size:17px" onclick="get_mybook(document.getElementById('userId_label'))">
           
          </td>
</s:else>
       
  		  </tr>

        </tr>
    </table></td>

  </tr>
</table>

<br>
<table width="778"  border="0" cellspacing="0" cellpadding="0" align="center">
    <tr >
      <td align="center">
        <form  action="<%=path%>/book!search" >
                            <input type="text"  style="visibility:hidden">
                            <select name="ref">  
								  <option value ="name">name</option>  
  								  <option value ="author">author</option>  
 								  
                            </select> 
                             
                            <input type="text" class="input" name="key" value="O(∩_∩)O" 
                                   onmouseover="this.style.borderColor='#FF6600'" 
                                   onmouseout="this.style.borderColor=''" 
                                   onfocus="if (value =='O(∩_∩)O'){value =''}" 
                                   onBlur="if (value ==''){value='O(∩_∩)O'}" />
                                 
                            <input type="submit" class="button" value="search" />
                             
        </form>
       </td>
    </tr>


</table>
</body>