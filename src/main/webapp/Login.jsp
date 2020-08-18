<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>

<!-- <link href="./css/bootstrap.css" rel="stylesheet">-->
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
      <li><a href="index.jsp"><span class="glyphicon glyphicon-home"></span>Home</a></li>

    </ul>
  </div>
</nav>
	
	<h4 style="color:red;">${error}</h4>
	<h4 style="color:red;">${error1}</h4>
	<br>
	<form action="login.do" method="post" class="col-xs-4">

	<div class="form-inline">
	<H4><mark>Please enter below details for login</mark></H4>
</div>
			<div class="input-group">
				<span class="input-group-addon"><i
					class="glyphicon glyphicon-user"></i></span> <input id="email"
					type="email" class="form-control" name="email" placeholder="Email">
			</div>
			<br>
			<div class="input-group">
				<span class="input-group-addon"><i
					class="glyphicon glyphicon-lock"></i></span> <input id="password"
					type="password" class="form-control" name="pswd"
					placeholder="Password">
			</div>
			<br>
			&nbsp; &nbsp;<button type="submit" class="btn btn-success">Login</button>&nbsp;&nbsp;&nbsp;
			 <a href="GeneratePassword.jsp" style="color:blue;"><u>First time Login</u></a>
			  &nbsp;&nbsp;&nbsp;<a href="ResetPassword.jsp" style="color:blue;"><u>Reset Password</u></a>
		</form>
		

</body>
</html>