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
    <div class="row">
      <div class="span3">
      	<a href="http://www.cirb.irisnet.be" target="_blank"><img alt="CIRB" src="https://irisbox.irisnet.be/resources/img/cirb-brussels.png" class="pull-right"></a>
      </div>
      <div class="span9">
        <h2>Projects Allocations Management</h2>
      </div>
    </div>
    <div class="row">
    	<div class="tabbable"> 
    		<ul class="nav nav-tabs">
      			<li class="dropdown"> <a class="dropdown-toggle" data-toggle="dropdown" href="#"> User Management <b class="caret"></b> </a>
        			<ul class="dropdown-menu">
        			    <li onclick="location.href='Registration.jsp';"><a data-toggle="tab">Créer Utilisateur</a></li>
        			</ul>
      			</li>	
      			<li class="dropdown"> <a class="dropdown-toggle" data-toggle="dropdown" href="#"> Projets <b class="caret"></b> </a>
        			<ul class="dropdown-menu">
    					<c:forEach items="${projects}" var="project">
    					    <li class="dropdown" ><a id="test" value="${project.analyticalCode}" href='#' onclick='invokeServlet("getProject","${project.analyticalCode}")' data-toggle="tab"><c:out value="${project.description}" /></a></li>
    					</c:forEach>
        			</ul>	
      			</li>        		
      			<li class="dropdown"> <a class="dropdown-toggle" data-toggle="dropdown" href="#"> Administration <b class="caret"></b> </a>
        			<ul class="dropdown-menu">
          				<li onclick="location.href='SnapshotUpload.jsp';"><a data-toggle="tab">Upload Snapshot Data</a></li>
        			</ul>
      			</li>      			
    		</ul>
		</div>  
    </div>
</div>
<div class="container">  
  <div class="row">
    <div class="span12">
      <table class="table table-bordered table-striped table-condensed">
        <thead>   
          <tr>
            <th class="text-center" colspan = "3"></th>
            <th class="text-center" colspan = "3">Budget C</th>
            <th class="text-center" colspan = "3">Budget B</th>
          </tr>
          <tr>
            <th class="text-center">CA</th>
            <th class="text-center">Description</th>
            <th class="text-center">Manager</th>
            <th class="text-center">revu</th>
            <th class="text-center">réalisé</th>
            <th class="text-center">disponible</th>
            <th class="text-center">revu</th>
            <th class="text-center">réalisé</th>
            <th class="text-center">disponible</th>
          </tr>

        </thead>
        <tbody>
            <c:forEach items="${snapshots}" var="snapshot">
                <tr>
                	<td class="text-center"><c:out value="${snapshot.projectCA}" /></td>
                	<td class="text-center"><c:out value="${snapshot.projectDescription}" /></td>
                	<td class="text-center"><c:out value="${snapshot.projectManager}" /></td>
                	<td class="text-right"><c:out value="${snapshot.reviewedBudgetC} €" /></td>
                	<td class="text-right"><c:out value="${snapshot.realizedBudgetC} €" /></td>
                	<td class="text-right"><c:out value="${snapshot.availableBudgetC} €" /></td>
                	<td class="text-right"><c:out value="${snapshot.reviewedBudgetB} €" /></td>
                	<td class="text-right"><c:out value="${snapshot.realizedBudgetB} €" /></td>
                	<td class="text-right"><c:out value="${snapshot.availableBudgetB} €" /></td>
                </tr>
            </c:forEach>
        </tbody>
      </table>
    </div>
  </div>   
</div>    

<script type='text/javascript'>
function invokeServlet(value1,value2)
{
	    location.href = "DataController?action="+value1+"&data="+value2;
}

</script>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://code.jquery.com/jquery.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>
