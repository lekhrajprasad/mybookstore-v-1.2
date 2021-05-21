<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%! String str = "Rate the books"; %>
<nav class="navbar nvbar-expand-lb navbar-dark bg-dark sticky-top">
	<h4>
		<a href="index" class="navbar-brand">My Book Store</a>
	</h4>
	<button class="navbar-togler" type="button" data-toggle="collapse"
		data-target="#navbarColor01" aria-controls="navbarColor01"
		aria-expanded="true" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarColor01" align="right">
		<ul class="navbar-nav mr-auto mt-2 mt-lg-1">
			<li class="nav-item active">
				<h4>
					<a href=<c:out value='showRatingsForm' /> class="nav-link"><c:out value="Rate the books" /></a>
				</h4>
			</li>
			<li class="nav-item active">
				<h4>
					<a href="showMyRatings" class="nav-link">Show My Ratings</a>
				</h4>
			</li>
			<li class="nav-item active">
				<h4>
					<a href="mylogin" class="nav-link">Login</a>
				</h4>
			</li>
			<li class="nav-item active">
				<h4>
					<a href="myregister" class="nav-link">Register</a>
				</h4>
			</li>
		</ul>
	</div>
</nav>
