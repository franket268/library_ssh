

function add_checkoutlist(obj1,obj2,obj3)
{
	var id=obj1.innerHTML;
	var name=obj2.innerHTML;
	var ISBN=obj3.innerHTML;
	

        window.location.href="bookcopy!addCheckoutList?book_copy.id="+id+"&bookName="+name+"&book_copy.book.ISBN="+ISBN;
    
}

function goback(){ 
   
   window.location.href="homepage.jsp";
}

function goback2(){
	history.go(-2);
}

function delele_checkout(obj)
{
	var bookId=obj.innerHTML;
	 window.location.href="bookcopy!delete_checkout?book_copy.id="+bookId; 
}

function submit_checkoutList(obj1,obj2,obj3)
{
	var userId=obj1.innerHTML;
	var quota=obj3.innerHTML;

	if(userId=="")
	{
	   alert("please login in");
	   window.location.href="login.jsp?requestFrom=checkout"; 
	}
	else if(obj2.innerHTML=="0")
		{
		 alert("Not enough quota,please delete some books from checkout list or return your book");
		}
	
	else
		{
		alert("Successful check out ,please return the books in 30 days");
       window.location.href="bookcopy!submit_checkout?user.id="+userId+"&user.quota="+quota;
		}
}

function pre_orderBook(obj1,obj2,obj3)
{
	var id=obj1.innerHTML;
	var name=obj2.innerHTML;
	var ISBN=obj3.innerHTML;
	
    window.location.href="bookcopy!preOrder?book_copy.id="+id+"&bookName="+name+"&book_copy.book.ISBN="+ISBN;
}

function pre_order_delete()
{
	window.location.href="bookcopy!delete_preOrder";
}





function pre_order_submit(obj1,obj2,obj3)
{
	var bookId=obj1.innerHTML;
	var userId=obj2.innerHTML;
	var preorder_quota=obj3.innerHTML;
	if(userId=="")
	{
		alert("please login ");
		window.location.href="login.jsp?requestFrom=preOrder"; 
	}
	else if(preorder_quota==0)
	{
		alert("No enough pre-order quota");
	}
	else
	{
	alert("Pre-order successfully.The page will return to homepage");
	window.location.href="bookcopy!submit_preOrder?book_copy.id="+bookId+"&user.id="+userId;
	}
}
	
function returnbook(obj1,obj2)
{
	var bookId=obj1.innerHTML;
	var userId=obj2.innerHTML;
	if(confirm("Are you sure to return this book?"))
	{
        alert("Return Succsuufully ");
		window.location.href="bookcopy!returnbook?book_copy.id="+bookId+"&user.Id="+userId;
	}
	else
	{
	    return false;
	} 
	
}

function cancel_preOrder(obj1,obj2)
{
	var bookId=obj1.innerHTML;
	var userId=obj2.innerHTML;
	if(confirm("Are you sure to cancel this pre-order?"))
	{
        alert("cancel Succsuufully ");
		window.location.href="bookcopy!cancel_preOrder?book_copy.id="+bookId+"&user.id="+userId;
	}
	else
	{
	    return false;
	} 
}

function update_search(obj)
{
	var key=obj.value;
    window.location.href="librarian!search?ref=ISBN&key="+key;
}

function update_book(obj)
{
	var p=obj.innerHTML;

    

        var str=prompt("please input your password to confirm");
        var pw = hex_md5(str);   //use the MD5 encryption algorithm from md5.js
        if(pw==p)
		{
        	alert("update successfully");
        	document.update_form.submit(); 
        	
		}
		else
		{
		    alert("wrong password");
		}
		

}


function delete_book(obj1,obj2)
{
	var p=obj1.innerHTML;
	var bookId=obj2.innerHTML;
    var str=prompt("please input your password to confirm");
    var pw = hex_md5(str);   //use the MD5 encryption algorithm from md5.js
    if(pw==p)
	{
    	alert("delete successfully");
    	 window.location.href="librarian!deleteBook?bookId="+bookId;
    	
	}
	else
	{
	    alert("wrong password");
	}
}

function display_notice(obj)
{
	var display=obj;
	 if (display==1)
		  alert("display successfully");
	 else
		 alert("Cancel successfully");
	 window.location.href="librarian!display_late_notice?display="+display;
}

function add_book()
{
   alert("Add successfully");
}



