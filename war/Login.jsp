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
	  <link href="css/Spacelab_bootstrap.min.css" rel="stylesheet">
	
	  <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
	  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	  <!--[if lt IE 9]>
	    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	  <![endif]-->
	  
	</head>
	<body>
		<div class="container">
		  <br>
		  <div class="row">
		    <div class="col-lg-4">
		    	<a href="http://www.cirb.irisnet.be" target="_blank"><img alt="CIRB" src="https://irisbox.irisnet.be/resources/img/cirb-brussels.png" class="pull-right"></a>
		    </div>
		    <div class="col-lg-8">
		    	<blockquote>
		    	 	<h2>PROJECTS ALLOCATIONS MANAGEMENT</h2>
				</blockquote> 
		    </div>
		  </div>
		</div>
	
		<div class="container">
			<div class="row">
				<br></br>
				<br></br>
				<br></br>
				<div class="col-md-4 col-md-offset-4">
					<div class="panel panel-primary">
						<div class="panel-heading">
					    	<h3 class="panel-title">Enter the application</h3>
					  	</div>
						<div class="panel-body">
					    	<form class="form-horizontal" id="formulaire" method="POST" action='DataController?action=login' name="frmLoginUser">
					        	<div class="form-group">
					            	<label for="userName" class="control-label col-xs-4">User Name</label>
					            	<div class="col-xs-8">
					                	<input type="text" class="form-control" id="userName" placeholder="Username" name="userlogin" value="<c:out value="${user.userLogin}"/>"/>
					            	</div>
					        	</div>
					        	<div class="form-group">
					            	<label for="inputPassword" class="control-label col-xs-4">Password</label>
						        	<div class="col-xs-8">
							        	<input type="password" class="form-control" id="inputPassword" placeholder="Password" name="userpwd" value="<c:out value="${user.userPwd}"/>"/>
						        	</div>
						    	</div>
				    	    	<div class="form-group">
					            	<div class="col-xs-12">
					                	<button type="submit" class="btn btn-primary btn-small pull-right">Submit</button>
					            	</div>
					        	</div>
					    	</form>
				  		</div>
			  		</div>
			  	</div>
		    </div>
		</div>
	
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
		<script src="https://code.jquery.com/jquery.js"></script>
		<!-- Include all compiled plugins (below), or include individual files as needed -->
		<script src="js/bootstrap.min.js"></script>
	    
	</body>
</html>