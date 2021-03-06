<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta charset="UTF-8">
<title>My Bookstore</title>
<link href="webjars/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<link href="mycss/bookstore.css" rel="stylesheet">
</head>
<body>
	<div class="card">
		<c:import url="myheader.jsp" />
	</div>
	<br/><br/>
	<br/><br/>
	<div class="container">
		<h1 class="text-center">Your Order has been placed successfully</h1>
	</div>
	<br/><br/>
	<br />
	<div class="container" align="center">
		<h1 class="text-center">
			<a href="continueShopping" class="btn btn-danger">Continue Shopping</a>
		</h1>
	</div>
	<br/><br/>
	<br/><br/>
	<c:import url="myfooter.jsp" />
</body>
</html>