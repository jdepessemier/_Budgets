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
      			<li class="dropdown"> <a class="dropdown-toggle" data-toggle="dropdown" href="#"> Users Management <b class="caret"></b> </a>
        			<ul class="dropdown-menu">
      					<li class="dropdown"><a href='#' onclick='invokeServlet("DataController?action=listProjects")' data-toggle="tab">Projets</a></li>
        			</ul>
      			</li>	
      			<li class="dropdown"> <a class="dropdown-toggle" data-toggle="dropdown" href="#"> Administration <b class="caret"></b> </a>
        			<ul class="dropdown-menu">
          				<li onclick="location.href='SnapshotUpload.jsp';"><a data-toggle="tab">Upload Snapshot Data</a></li>
        			</ul>
      			</li>      			
      			<li onclick="location.href='Registration.jsp';"><a data-toggle="tab">Register Users</a></li>
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
            <th>First Name</th>
            <th>Last Name</th>
            <th>eMail</th>
            <th>Login</th>
            <th>Password</th>
          </tr>
        </thead>
        <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                	<td><c:out value="${user.firstName}" /></td>
                	<td><c:out value="${user.lastName}" /></td>
                	<td><c:out value="${user.userMail}" /></td>
                	<td><c:out value="${user.userLogin}" /></td>
                	<td><c:out value="${user.userPwd}" /></td>
                </tr>
            </c:forEach>
        </tbody>
      </table>
    </div>
  </div>   
</div>    

<script type='text/javascript'>
function invokeServlet(URL)
{
   location.href = URL;
}

</script>
<script src="js/jquery.js"></script> 
<script src="js/bootstrap.min.js"></script>
</body>
</html>
