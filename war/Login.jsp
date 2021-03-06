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

	<div class="container">
	  <br>
	  <div class="row">
	    <div class="col-md-3" id="portal-logo-wrapper"><a href="http://cirb.brussels/fr"> 
                <img src="http://cirb.brussels/fr/++theme++plonetheme.bric/images/cirb-brussels-it-is-for-you.png" alt="cirb.brussels - IT is for you" />
            </a></div>
	    <div class="col-md-9">
	    		<h2><font color=#164397>Projects Allocations Management</font></h2>
	    </div>
	  </div>
	</div>
	
	<div class="container">
		<div class="row">
			<br>
			<div class="col-md-4 col-md-offset-4">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">Please Sign in :</h3>
					</div>
					<div class="panel-body">
						<form class="form-horizontal" id="formulaire" method="POST" action='DataController?action=login' name="frmLoginUser">
					  	<div class="form-group">
					      	<div class="col-xs-12">
					      		<div class="input-group input-group-lg">
					      			<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
					        		<input type="text" class="form-control" id="userName" placeholder="Username" name="userlogin" 
					        	         value="<c:out value="${user.userLogin}"/>"/>
					        	</div>
					        </div>
					    </div>
					    <div class="form-group">
						    <div class="col-xs-12">
					      		<div class="input-group input-group-lg">
					      			<span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
							  				<input type="password" class="form-control" id="inputPassword" placeholder="Password" name="userpwd" 
							  				       value="<c:out value="${user.userPwd}"/>"/>
							  		</div>
						    </div>
						  </div>
				    	<div class="form-group">
					    	<div class="col-xs-12">
					      	<button type="submit" class="btn btn-primary btn-large pull-right"><span class="glyphicon glyphicon-log-in"></span>  Sign in</button>
					      </div>
					    </div>
					  </form>
				  </div>
			  </div>
			</div>
		</div>
	</div>
	
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
  
</body>
</html>