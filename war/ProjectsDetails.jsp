<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>CIRB - Project Allocations</title>
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css"/>
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
        			    <li class="dropdown"><a href='#' onclick='invokeServlet("listUsers","")' data-toggle="tab">Liste des Utilisateurs</a></li>
        			    <li onclick="location.href='Registration.jsp';"><a data-toggle="tab">Créer Utilisateur</a></li>
        			</ul>
      			</li>	
      			<li class="dropdown"> <a class="dropdown-toggle" data-toggle="dropdown" href="#"> Projets <b class="caret"></b> </a>
        			<ul class="dropdown-menu">
    					<c:forEach items="${projectslist}" var="project">
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
    <div class="span6">
      <table class="table table-bordered table-striped table-condensed">
      	<tbody>
          <tr>
            <td>Project Analytical Code</td>
            <td><c:out value="${selectedproject.analyticalCode}" /></td>
          </tr>
          <tr>
            <td>Description</td>
            <td><c:out value="${selectedproject.description}" /></td>
          </tr>
          <tr>
            <td>Director</td>
            <td><c:out value="${selectedproject.director}" /></td>
          </tr>
          <tr>
            <td>Manager</td>
            <td><c:out value="${selectedproject.manager}" /></td>
          </tr>
          <tr>
            <td>Year</td>
            <td><c:out value="${selectedproject.year}" /></td>
          </tr>
        <tbody>
      </table>
    </div>
  </div>   
</div>   

<div class="container">  
  <div class="row">
    <div class="span6">
      <table class="table table-bordered table-striped table-condensed">
        <thead>
          <tr>
            <th>Document Nb</th>
            <th>Description</th>
            <th>Engagement (EUR)</th>
          </tr>
        </thead>
        <tbody>
            <c:forEach items="${budgetsC}" var="budgetC">
                <tr>
                	<td><c:out value="${budgetC.documentNb}" /></td>
                	<td><c:out value="${budgetC.documentDescription}" /></td>
                	<td align="right"><c:out value="${budgetC.amount}" /></td>
                </tr>
            </c:forEach>
        </tbody>
      </table>
      <table class="table table-bordered table-striped table-condensed">
      	<tr>
      		<td><b>Budget Total (Engagement)</b></td>
      		<td align="right"><b><c:out value="${totalBudgetC}"/></b></td>
      	</tr>
      </table>
    </div>
    <div class="span6">
      <table class="table table-bordered table-striped table-condensed">
        <thead>
          <tr>
            <th>Document Nb</th>
            <th>Description</th>
            <th>Liquidation (EUR)</th>
          </tr>
        </thead>
        <tbody>
            <c:forEach items="${budgetsB}" var="budgetB">
                <tr>
                	<td><c:out value="${budgetB.documentNb}" /></td>
                	<td><c:out value="${budgetB.documentDescription}" /></td>
                	<td align="right"><c:out value="${budgetB.amount}" /></td>
                </tr>
            </c:forEach>
        </tbody>
      </table>
      <table class="table table-bordered table-striped table-condensed">
      	<tr>
      		<td><b>Budget Total (Liquidation)</b></td>
      		<td align="right"><b><c:out value="${totalBudgetB}"/></b></td>
      	</tr>
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
<script src="js/jquery.js"></script> 
<script src="js/bootstrap.min.js"></script>
</body>
</html>
