<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<p class="navbar-text" style="color: white; font-size: 20px;">Welcome,</p>
			</div>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="index.jsp"><span
						class="glyphicon glyphicon-home"></span>Home</a></li>

			</ul>
		</div>
	</nav>
	<h4>
		<a href="" style="color: red;"><u>Search</u></a>
	</h4>
	<h3 style="color: blue;">Welcome to Dharmasthala Temple</h3>


	<form action="bookingVisit.do" method="post" class="col-xs-4">
	
	<h4 style="color:blue;">Welcome,</h4>
		<div class="form-inline">
			<H4>
				<mark>Booking tickets for visit</mark>
			</H4>

			<table class="table table-hover">
				<tbody class="form-inline">
					<tr>
						<td><label>Date :</label></td>

						<td><input type="text" name="date" class="form-control"
							id="date" placeholder="Enter date"></td>

					</tr>
					<tr>
						<td><label>No Of People :</label></td>

						<td> <input type="text"
							name="noOfPeople" class="form-control" id="people"
							placeholder="Enter No Of People"></td>
					</tr>
					<tr>

						<td><label>Special Entrance :</label></td>

						<td><select name="seLt" class="form-control">
								<option>--select--</option>
								<c:forEach items="${seLt}" var="sedata">
									<option value="${sedata.propValue}">${sedata.propName}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td><label>Prasadha </label></td>

						<td><select name="prLt" class="form-control">
								<option>--select--</option>
								<c:forEach items="${prLt}" var="prdata">
									<option value="${prdata.propValue}">${prdata.propName}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>

						<td><label>Pooja Type :</label></td>

						<td><select name="ptLt" class="form-control">
								<option>--select--</option>
								<c:forEach items="${ptLt}" var="ptdata">
									<option value="${ptdata.propValue}">${ptdata.propName}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td><label>ID Card :</label></td>

						<td><select name="idLt" class="form-control">
								<option>--select--</option>
								<c:forEach items="${idLt}" var="iddata">
									<option value="${iddata.propValue}">${iddata.propName}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>

						<td><label>ID Number :</label></td>

						<td><input type="text" name="idNumber" class="form-control"
							id="id" placeholder="Enter ID number"></td>

					</tr>

				</tbody>
			</table>
		</div>

		<div class="text-center">
			<button type="submit" class="btn btn-success">Book</button>
		</div>
	</form>
</body>
</html>