<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Temple registration</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- <link href="./css/bootstrap.css" rel="stylesheet"> -->


</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<p class="navbar-text" style="color: white; font-size: 20px;">Temple
					Registration</p>
			</div>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="index.jsp"><span
						class="glyphicon glyphicon-home"></span>Home</a></li>

			</ul>
		</div>
	</nav>

	<br>
	<form action="register.do" method="post">
		<div class="container">
			<h3>
				<mark>Personal Information</mark>
			</h3>
			<table class="table table-hover">
				<tbody class="form-inline">
					<tr>
						<td><label for="name">Name :</label></td>
						<td><input type="text" class="form-control" id="name"
							name="name" placeholder="Enter Name"></td>
					</tr>
					<tr>
						<td><label for="age">Age :</label></td>
						<td><input type="text" class="form-control" name="age"
							id="age" placeholder="Enter Age"></td>
					</tr>
					<tr>
						<td><label for="address">Address :</label></td>
						<td><textarea rows="4" cols="70" class="form-control"
								name="address" placeholder="Enter Address"></textarea></td>
					</tr>
					<tr>
						<td><label for="cnumber">Contact number :</label></td>
						<td><input type="text" name="phoneNumber"
							class="form-control" id="cnumber"
							placeholder="Enter Contact number"></td>
					</tr>
					<tr>
						<td><label for="eadd">Email Address :</label></td>
						<td><input type="text" name="emailAddress"
							class="form-control" id="eadd" placeholder="Enter Email Address"><br>
						</td>
					</tr>

				</tbody>
			</table>

			<h3>
				<mark>Visiting Information</mark>
			</h3>
			<table class="table table-hover">
				<tbody class="form-inline">

					<tr>
						<td><label>Date :</label> <input type="text" name="date"
							class="form-control" id="date" placeholder="Enter date">
						</td>

						<td>
						
						<td><label>No Of People :</label>
						<input type="text" name="noOfPeople" class="form-control"
							id="people" placeholder="Enter No Of People"></td>
					</tr>
					<tr>
						<td><label>Special Entrance :</label> <select name="seLt"
							class="form-control">
								<option>--select--</option>
								<c:forEach items="${seLt}" var="sedata">
									<option value="${sedata.propValue}">${sedata.propName}</option>
								</c:forEach>
						</select></td>

						<td><label>Prasadha </label> <select name="prLt"
							class="form-control">
								<option>--select--</option>
								<c:forEach items="${prLt}" var="prdata">
									<option value="${prdata.propValue}">${prdata.propName}</option>
								</c:forEach>
						</select></td>
					</tr>
					
					<tr>
						<td><label>Pooja Type :</label> <select name="ptLt"
							class="form-control">
								<option>--select--</option>
								<c:forEach items="${ptLt}" var="ptdata">
									<option value="${ptdata.propValue}">${ptdata.propName}</option>
								</c:forEach>
						</select></td>

						<td><label>ID Card :</label> <select name="idLt"
							class="form-control">
								<option>--select--</option>
								<c:forEach items="${idLt}" var="iddata">
									<option value="${iddata.propValue}">${iddata.propName}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td><label>ID Number :</label> <input type="text"
							name="idNumber" class="form-control" id="id"
							placeholder="Enter ID number"></td>
					</tr>


				</tbody>
			</table>
		</div>

		<div class="text-center">
			<button type="submit" class="btn btn-success">Register</button>

			<button type="reset" class="btn btn-danger">Reset</button>
		</div>

	</form>
	${error}
</body>



</html>
