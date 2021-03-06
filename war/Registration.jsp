<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Projects Allocations</title>

  <!-- Bootstrap -->
  <link href="css/bootstrap.min.css" rel="stylesheet">

  <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
  
  <!-- Le fav and touch icons -->
  <link rel="shortcut icon" href="ico/favicon.ico">

</head>
<body>

<%
   Cookie cookie = null;
   Cookie[] cookies = null;
   cookies = request.getCookies();
   if( cookies != null) {
      for (int i = 0; i < cookies.length; i++){
         cookie = cookies[i];
         if (cookie.getName().equals("last_name")) {
        	 String currentlastname = cookie.getValue();
        	 request.setAttribute("lastName", currentlastname);
         }
         if (cookie.getName().equals("first_name")) {
        	 String currentfirstname = cookie.getValue();
        	 request.setAttribute("firstName", currentfirstname);
         }
         if (cookie.getName().equals("user_role")) {
        	 String currentrole = cookie.getValue();
        	 request.setAttribute("role", currentrole);
         }
     }
   }
%>

	<div class="container">
	  <br>
	  <div class="row">
	    <div class="col-lg-4">
	    	<a href="http://www.cirb.irisnet.be" target="_blank"><img alt="CIRB" src="https://irisbox.irisnet.be/resources/img/cirb-brussels.png" class="pull-right"></a>
	    </div>
	    <div class="col-lg-8">
	    	 	<h3>PROJECTS ALLOCATIONS MANAGEMENT</h3>
	    	  <p class="text-info"><span class="glyphicon glyphicon-user"></span>
	    	  	&nbsp;<c:out value="${firstName}"/> <c:out value="${lastName}"/>
	    	  	&nbsp;&nbsp;<a href='#' onclick='invokeServlet("logout","")'>
	    	  	<span class="glyphicon glyphicon-log-out"></span></a></p>
	    	 	<hr>
	    </div>
	  </div>
	</div>
	
	<div class="container">
  	<ul class="nav nav-tabs">
  	  <li onclick="location.href='Home.jsp';"><a data-toggle="tab"><span class="glyphicon glyphicon-home"></span> Home</a></li>              
    </ul>
  </div>
      
	<div class="container">
		<div class="row">
			<br>
			<div class="col-md-4 col-md-offset-4">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">Enter user information</h3>
					</div>
					<div class="panel-body">
						<form class="form-horizontal" id="formulaire" method="POST" action='DataController?action=register' name="frmRegisterUser">
							<div class="form-group">
					    	<div class="col-xs-12">
					      	<div class="input-group input-group-sm">
					      		<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
					      			<input type="text" class="form-control" placeholder="Enter Lastname" name="lastname" 
					      						 value="<c:out value="${user.lastName}"/>"/>
					      	</div>
					      </div>
					    </div>
							<div class="form-group">
					    	<div class="col-xs-12">
					      	<div class="input-group input-group-sm">
					      		<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
											<input type="text" class="form-control" placeholder="Enter Firstname" name="firstname" 
														 value="<c:out value="${user.firstName}"/>"/>
					      	</div>
					      </div>
					    </div>
							<div class="form-group">
					    	<div class="col-xs-12">
					      	<div class="input-group input-group-sm">
					      		<span class="input-group-addon"><span class="glyphicon glyphicon-envelope"></span></span>
											<input type="text" class="form-control" placeholder="Enter email" name="usermail" 
														 value="<c:out value="${user.userMail}"/>"/>
					      	</div>
					      </div>
					    </div>
							<div class="form-group">
					    	<div class="col-xs-12">
					      	<div class="input-group input-group-sm">
					      		<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
											<input type="text" class="form-control" placeholder="Enter login" name="userlogin" 
											value="<c:out value="${user.userLogin}"/>"/>
					      	</div>
					      </div>
					    </div>
					    <div class="form-group">
						    <div class="col-xs-12">
					      		<div class="input-group input-group-sm">
					      			<span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
							  				<input type="password" class="form-control" id="inputPassword" placeholder="Password" name="userpwd" 
							  				       value="<c:out value="${user.userPwd}"/>"/>
							  		</div>
						    </div>
						  </div>
						  <div class="form-group">
      					<div class="col-xs-12">
					      	<div class="input-group input-group-sm">
					      		<span class="input-group-addon"><span class="glyphicon glyphicon-info-sign"></span></span>      					
      								<select class="form-control" id="select" name="department">
								      	<option>Projets</option>
						        	</select>
						      </div>
      					</div>
    					</div>					  
						  <div class="form-group">
      					<div class="col-xs-12">
					      	<div class="input-group input-group-sm">
					      		<span class="input-group-addon"><span class="glyphicon glyphicon-info-sign"></span></span>      					
      								<select class="form-control" id="select" name="service">
      								  <option>--</option>
								      	<option>IS</option>
								      	<option>WAP</option>
						        	</select>
						      </div>
      					</div>
    					</div>	
 						  <div class="form-group">
      					<div class="col-xs-12">
					      	<div class="input-group input-group-sm">
					      		<span class="input-group-addon"><span class="glyphicon glyphicon-info-sign"></span></span>      					
      								<select class="form-control" id="select" name="role">
								      	<option>Manager</option>
								      	<option>Project Manager</option>
								      	<option>Administrator</option>
						        	</select>
						      </div>
      					</div>
    					</div>   							  
				    	<div class="form-group">
					    	<div class="col-xs-12">
					    		<button type="submit" class="btn btn-primary btn-large pull-right"><span class="glyphicon glyphicon-saved"></span>  Register User</button>
					      </div>
					    </div>
						</form>
					</div>
  			</div> 
			</div>
		</div>
 	</div>
 	
<script type='text/javascript'>
function invokeServlet(value1,value2){
	location.href = "DataController?action="+value1+"&data="+value2;
}
</script>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>

</body>
</html>