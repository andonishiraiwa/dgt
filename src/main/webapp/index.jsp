<%@ include file="../includes/header.jsp"%>
<%@ include file="../includes/navbar.jsp"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<body>

	<main role="main" class="container">
	<div class="jumbotron">

		<ul class="navbar-nav float-right">
			<li><span class=" badge-lg badge-light mr-3 text-success h5">${agenteLogueado.nombre}<img
					src="images/placa.png" /> <!-- agente -->
			</span></li>
		</ul>
		<%-- <h3>${agenteLogueado.nombre}</h3> --%>

		<br /> <br />





		<h1>Bienvenido</h1>
		<br /> <br /> <a href="form.jsp"
			class="btn-lg btn-danger btn-block text-center">Multar</a> <br /> <br />
		<a href="multa" class="btn-lg btn-success btn-block text-center">Ver
			multas</a>
			
			<a href="multa?op=6" class="btn-lg btn-success btn-block text-center">Ver
			multas anuladas</a>
			
			
	</div>
	</main>

</body>
</html>