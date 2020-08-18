<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Re-sending mail</title>
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
<br>

<form action="resendingmail.do" method="post" class="input-group">
<br>
	<div class="form-inline">
	<H4><mark>Please enter email id to receive details</mark></H4>
</div>
			<div class="input-group">
				<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span> 
				
					<input id="email" type="email" class="form-control" name="email" placeholder="Email">
			</div>
	  <br>
	  <button type="submit" class="btn btn-success">Submit Request</button>
	  </form>
	  ${error} 
</body>
</html>