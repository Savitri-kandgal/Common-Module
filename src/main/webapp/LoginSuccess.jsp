<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login success</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
       <p class="navbar-text" style="color:white; font-size:20px;">Welcome, ${dto.getEmailAddress()}</p>
    </div>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="index.jsp"><span class="glyphicon glyphicon-home"></span>Home</a></li>

    </ul>
  </div>
</nav>

 <h3 style="color:blue;">Welcome to Dharmasthala Temple</h4>
 
 	<!-- <a href="loadingforbook.do" style="color:blue;"><u>Book</u></a> -->
 	<br>
 	<a href="" style="color:blue;"><u>Search</u></a>
 
 
 
 
 <!--  	<h3 style="color:blue;">Name: ${dto.getName()}</h4>
 	<h3 style="color:blue;">No. Of People : ${dto.getNoOfPeople()}
	<h4 style="color:black;">Date 		: ${dto.getDate()}</h4>
	 <h4 style="color:black;">Entry Type: ${dto.getSeLt()}</h4>
	<h4 style="color:black">Prasadha 	: ${dto.getPrLt()}</h4>
	<h4 style="color:black;">Pooja 		: ${dto.getPtLt()}</h4>
	<h4 style="color:black;">ID Card	: ${dto.getIdLt()}</h4>
	<h4 style="color:black;">ID Number	: ${dto.getIdNumber()}</h4></h4>-->
</body>
</html>