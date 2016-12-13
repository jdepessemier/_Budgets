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
	    <div class="col-md-3" id="portal-logo-wrapper"><a href="http://cirb.brussels/fr"> 
                <img src="http://cirb.brussels/fr/++theme++plonetheme.bric/images/cirb-brussels-it-is-for-you.png" alt="cirb.brussels - IT is for you" />
            </a></div>
	    <div class="col-md-6">
	    		<h2><font color=#164397>Projects Allocations Management</font></h2>
	    </div>
	    <div class="col-md-3">
	    	  <p class="text-info"><span class="glyphicon glyphicon-user"></span>
	    	  	&nbsp;<c:out value="${firstName}"/> <c:out value="${lastName}"/>
	    	  	&nbsp;&nbsp;<a href='#' onclick='invokeServlet("logout","")'>
	    	  	<span class="glyphicon glyphicon-log-out"></span></a></p>
	    </div>
	  </div>
	</div>
                
  <div class="container">
  
  	<c:choose>
    	<c:when test="${role=='Administrator'}">
      	<c:set var="visibility" scope="application" value="active"/>
      </c:when>
      <c:otherwise>
        <c:set var="visibility" scope="application" value="disabled"/>
      </c:otherwise>
    </c:choose> 
  
  	<ul class="nav nav-tabs">
  	
<!--   	  <li class="dropdown"><a href='#' onclick='invokeServlet("projectsStatus","")' data-toggle="tab"><span class="glyphicon glyphicon-list"></span> Projects Summary</a> -->
<!--       </li> -->
 
       <li class="dropdown"> <a class="dropdown-toggle" data-toggle="dropdown" href="#"><span class="glyphicon glyphicon-list"></span> Projects Management <b class="caret"></b> </a>
      	<ul  class="dropdown-menu">
      		<li><a href='#' onclick='invokeTimeSheetServlet("MissionsList","")' data-toggle="tab"><span class="glyphicon glyphicon-list"></span> Missions List</a></li>
      	</ul>
      </li>
      
      <li class="dropdown"> <a class="dropdown-toggle" data-toggle="dropdown" href="#"><span class="glyphicon glyphicon-list"></span> Teams Management <b class="caret"></b> </a>
      	<ul  class="dropdown-menu">
      		<li><a href='#' onclick='invokeTimeSheetServlet("GetActiveTeamMembers","")' data-toggle="tab"><span class="glyphicon glyphicon-list"></span> Team Members</a></li>
<!--       		<li><a href='#' onclick='invokeTimeSheetServlet("TimesheetsSummaries","")' data-toggle="tab"><span class="glyphicon glyphicon-list"></span> TimeSheets Summary</a></li> -->
<!--       		<li><a href='#' onclick='invokeTimeSheetServlet("TimesheetsDetails","")' data-toggle="tab"><span class="glyphicon glyphicon-list"></span> TimeSheets Details</a></li> -->
      	</ul>
      </li>                                  
      
      <li class="${visibility}" class="dropdown"> <a class="${visibility}" class="dropdown-toggle" data-toggle="dropdown" href="#"><span class="glyphicon glyphicon-wrench"></span> Administration <b class="caret"></b> </a>
      	<ul  class="dropdown-menu">
        	<li onclick="location.href='Registration.jsp';"><a data-toggle="tab"><span class="glyphicon glyphicon-user"></span> Register User</a></li>
          <li class="dropdown"><a href='#' onclick='invokeServlet("listUsers","")' data-toggle="tab"><span class="glyphicon glyphicon-list"></span> Users List</a></li>
          <li class="divider"></li>
          <li><a href='#' onclick='invokeTimeSheetServlet("GetTeamMembers","")' data-toggle="tab"><span class="glyphicon glyphicon-list"></span> Team Members List</a></li>
          <li class="divider"></li>
<!--           <li onclick="location.href='DirectorsReportUpload.jsp';"><a data-toggle="tab"><span class="glyphicon glyphicon-cloud-upload"></span> Directors Report</a></li> -->
<!--           <li onclick="location.href='MissionsSituationUpload.jsp';"><a data-toggle="tab"><span class="glyphicon glyphicon-cloud-upload"></span> Missions Situation</a></li> -->
          <li onclick="location.href='TeamUpload.jsp';"><a data-toggle="tab"><span class="glyphicon glyphicon-cloud-upload"></span> Team Members Upload</a></li>
<!--           <li onclick="location.href='TimeSheetsUpload.jsp';"><a data-toggle="tab"><span class="glyphicon glyphicon-cloud-upload"></span> TimeSheets Upload</a></li> -->
          <li onclick="location.href='SummariesUpload.jsp';"><a data-toggle="tab"><span class="glyphicon glyphicon-cloud-upload"></span> Summary Data Upload</a></li>
        </ul>
      </li>                  
    </ul>
  </div>  
 
<script type='text/javascript'>
function invokeServlet(value1,value2){
	location.href = "DataController?action="+value1+"&data="+value2;
}

function invokeTimeSheetServlet(value1,value2){
	location.href = "TSController?action="+value1+"&data="+value2;
}



</script>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>

</body>
</html>
