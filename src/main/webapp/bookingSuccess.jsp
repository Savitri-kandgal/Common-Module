<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
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
       <p class="navbar-text" style="color:white; font-size:20px;">Temple Registration</p>
    </div>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="index.jsp"><span class="glyphicon glyphicon-home"></span> Home</a></li>

    </ul>
  </div>
</nav>
	
  <h3 style="color:red;">Congratulation, Booking successful</h3>
	<h4>Date 		  :${dto.getDate()}</h4>
	<h4>No. Of People :${dto.getNoOfPeople()}</h4>
	<h4>Entry Type 	  :${dto.getSeLt()}</h4>
	<h4>Prasadha      :${dto.getPrLt()}</h4>
	<h4>Pooja Type	  :${dto.getPtLt()}</h4>
	

	<!--  <h2 style="color:blue;">Congratulation, Registration successful</h2>-->
		<h3 style="color:blue;">Mail sent successfully to your email address</h3>
		
		
</body>
</html>