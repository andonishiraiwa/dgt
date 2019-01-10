<%@ include file="../includes/header.jsp"%>
<%@ include file="../includes/navbar.jsp"  %>
<main role="main" class="container">

<div class="jumbotron">
<ul class="navbar-nav float-right">
		<li><span class=" badge-lg badge-light mr-3 text-success h5">Agente Takel Berry <img src="images/placa.png"/><!-- agente --></span>
		</li>
	</ul>

	<button type="button" class="btn btn-info ">Atras</button>
	<br />
	<br />

	<h1>Busqueda de auto</h1>



	<form novalidate action="buscar" method="post" class="form-signin">

		<c:if test="${not empty mensaje}">
			<div class="alert alert-danger alert-dismissible fade show"
				role="alert">${mensaje}</div>
		</c:if>

		<label for="matricula" class="sr-only"></label> <input type="text"
			name="matricula" class="form-control" placeholder="0000XYZ" required
			autofocus> <br />
		<br />
		<button type="button" class="btn-lg btn-info ">Buscar</button>
		<br />
		<br />
	</form>
</div>

</main>