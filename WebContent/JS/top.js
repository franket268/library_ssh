
function change_loginLabel(obj1,obj2,obj3){

	if(obj1.innerHTML=="")
	{
	   obj3.style.display="none";
	}
	   else
	   {
		   obj2.style.display="none";
	   }
}

function checkout(){   
    window.location.href="checkout.jsp?from=top";
}


function pre_order(){   
    window.location.href="pre_order.jsp";
}



function get_mybook(obj)
{
	var userId=obj.innerHTML;

	if(userId=="")
	{
		alert("please login");
	}
	else
	{
	   window.location.href="user!getBookRecord";
	}

	   
	
}

function to_homepage()
{
	window.location.href="homepage.jsp";
}

function updatebook()
{
	window.location.href="bookUpdate.jsp";
}

function generate_notice()
{
	window.location.href="librarian!late_notice";
}

function generate_usage_report()
{
	window.location.href="librarian!generate_usage_report";
}


