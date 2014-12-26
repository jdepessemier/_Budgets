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
  <link rel="stylesheet" type="text/css" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/plug-ins/3cfcc339e89/integration/bootstrap/3/dataTables.bootstrap.css">
  <script type="text/javascript" language="javascript" src="//code.jquery.com/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" language="javascript" src="//cdn.datatables.net/1.10.4/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" language="javascript" src="//cdn.datatables.net/plug-ins/3cfcc339e89/integration/bootstrap/3/dataTables.bootstrap.js"></script>
	 
    <script> 
      $(document).ready(function(){ 
        $('#the_table').dataTable(); 
      }); 
    </script>

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
	    	 	<p class="text-info">Welcome <c:out value="${firstName}"/> <c:out value="${lastName}" /> !</p>
	    	 	<hr>
	    </div>
	  </div>
	</div>
	
  <div class="container">
  	<ul class="nav nav-tabs">
  	  <li onclick="location.href='Home.jsp';"><a data-toggle="tab"><span class="glyphicon glyphicon-home"></span> Home</a></li>
  	  <li class="dropdown"><a href='#' onclick='invokeServlet("projectsStatus","")' data-toggle="tab"><span class="glyphicon glyphicon-list"></span> Projects Summary</a></li>
          
<!--       <li class="dropdown"> <a class="dropdown-toggle" data-toggle="dropdown" href="#"> Project Management <b class="caret"></b> </a> -->
<!--       	<ul class="dropdown-menu"> -->
<!--           <li class="dropdown"><a href='#' onclick='invokeServlet("listProjects","")' data-toggle="tab">Projects List</a></li> -->
<!--           <li class="dropdown"><a href='#' onclick='invokeServlet("projectsStatus","")' data-toggle="tab">Projects Status</a></li> -->
<!--         </ul> -->
<!--       </li>                       -->
<!--       <li class="dropdown"> <a class="dropdown-toggle" data-toggle="dropdown" href="#"> Projets <b class="caret"></b> </a> -->
<!--       	<ul class="dropdown-menu"> -->
<%--         	<c:forEach items="${projects}" var="project"> --%>
<%--           	<li class="dropdown" ><a id="test" value="${project.analyticalCode}" href='#' onclick='invokeServlet("getProject","${project.analyticalCode}")' data-toggle="tab"><c:out value="${project.description}" /></a></li> --%>
<%--           </c:forEach> --%>
<!--         </ul>     -->
<!--       </li>  -->
      
      <c:choose>
      	<c:when test="${role=='Administrator'}">
        	<c:set var="visibility" scope="application" value="active"/>
        </c:when>
        <c:otherwise>
        	<c:set var="visibility" scope="application" value="disabled"/>
        </c:otherwise>
      </c:choose>                 
      
      <li class="${visibility}" class="dropdown"> <a class="${visibility}" class="dropdown-toggle" data-toggle="dropdown" href="#"><span class="glyphicon glyphicon-wrench"></span> Administration <b class="caret"></b> </a>
      	<ul  class="dropdown-menu">
        	<li onclick="location.href='Registration.jsp';"><a data-toggle="tab"><span class="glyphicon glyphicon-user"></span> Register User</a></li>
          <li class="dropdown"><a href='#' onclick='invokeServlet("listUsers","")' data-toggle="tab"><span class="glyphicon glyphicon-list"></span> List Users</a></li>
          <li class="divider"></li>
          <li onclick="location.href='DirectorsReportUpload.jsp';"><a data-toggle="tab"><span class="glyphicon glyphicon-cloud-upload"></span> Directors Report</a></li>
          <li onclick="location.href='MissionsSituationUpload.jsp';"><a data-toggle="tab"><span class="glyphicon glyphicon-cloud-upload"></span> Missions Situation</a></li>
        </ul>
      </li>                  
    </ul>
  </div> 

<div class="container">  
    	<br></br>
  <div class="row">
      <table id="the_table" class="table table-condensed table-striped table-hover ">
        <thead>
          <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>eMail</th>
            <th>Login</th>
            <th>Department</th>
            <th>Service</th>
            <th>Role</th>
            <th></th>
            <th></th>
          </tr>
        </thead>
        <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                	<td><c:out value="${user.firstName}" /></td>
                	<td><c:out value="${user.lastName}" /></td>
                	<td><c:out value="${user.userMail}" /></td>
                	<td><c:out value="${user.userLogin}" /></td>
                	<td><c:out value="${user.department}" /></td>
                	<td><c:out value="${user.service}" /></td>
                	<td><c:out value="${user.role}" /></td>
                	<td><a id="edit" value="${user.userLogin}" href='#' 
                	                 onclick='invokeServlet("editUser","${user.userLogin}")'>
                	                 <span class="glyphicon glyphicon-edit"></span>
                	    </a>
                	</td>
                	<td><a id="delete" value="${user.userLogin}" href='#' 
                	                   onclick='invokeServlet("deleteUser","${user.userLogin}")'>
                	                   <span class="glyphicon glyphicon-trash"></span>
                	    </a>
                	</td>
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

</body>
</html>
