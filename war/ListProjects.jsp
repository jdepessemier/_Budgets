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
	    <div class="col-lg-4">
	    	<a href="http://www.cirb.irisnet.be" target="_blank"><img alt="CIRB" src="https://irisbox.irisnet.be/resources/img/cirb-brussels.png" class="pull-right"></a>
	    </div>
	    <div class="col-lg-8">
	    	 	<h3>PROJECTS ALLOCATIONS MANAGEMENT</h3>
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
    	<br></br>
  <div class="row">
      <table class="table table-condensed table-striped table-hover ">
        <thead>
          <tr>
            <th>Analytical Code</th>
            <th>Description</th>
            <th>Director</th>
            <th>Manager</th>
            <th>Budget C</th>
            <th>Budget B</th>
          </tr>
        </thead>
        <tbody>
            <c:forEach items="${projects}" var="project">
                <tr>
                	<td><c:out value="${project.analyticalCode}" /></td>
                	<td><c:out value="${project.description}" /></td>
                	<td><c:out value="${project.director}" /></td>
                	<td><c:out value="${project.manager}" /></td>
                	<td><c:out value="${project.totalBudgetC} EUR" /></td>
                	<td><c:out value="${project.totalBudgetB} EUR" /></td>
                </tr>
            </c:forEach>
        </tbody>
      </table>
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
