<%@ include file="../includes/header.jsp"%>
<%@ include file="../includes/navbar.jsp"%>
<%@ include file="../includes/mensajes.jsp"%>
<main role="main" class="container">

<div class="jumbotron">
	<a href="index.jsp" class="btn btn-info ">Atras</a>
	<ul class="navbar-nav float-right">
		<li><span class=" badge-lg badge-light mr-3 text-success h5">${agenteLogueado.nombre}<img
				src="images/placa.png" /> <!-- agente -->
		</span></li>
	</ul>

	<br /> <br />

	<h1>Busqueda de auto</h1>



	<form novalidate action="multa" method="post" class="form-signin">

		<c:if test="${not empty mensaje}">
			<div class="alert alert-danger alert-dismissible fade show"
				role="alert">${mensaje}</div>
		</c:if>

		<label for="matricula" class="sr-only"></label> <input type="text"
			name="matricula" class="form-control" placeholder="0000XYZ" required
			autofocus> <br /> <br /> <input type="hidden" name="op"
			value="4"> <input type="submit" class="btn btn-info"
			value="Buscar">
	</form>
</div>

</main>