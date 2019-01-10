<%@ include file="../includes/header.jsp"%>
<main role="main" class="container">
<div class="jumbotron">

	<ul class="navbar-nav float-right">
		<li><span class="badge-lg badge-light mr-3">Agente <!-- agente --></span>
		</li>
	</ul>

	<button type="button" class="btn btn-info ">Atras</button>
	<br />
	<br />

	<h1>Especifique los detalles de la multa para ${matricula}</h1>



	<form novalidate action="buscar" method="post" class="form-signin">

		<c:if test="${not empty mensaje}">
			<div class="alert alert-danger alert-dismissible fade show"
				role="alert">${mensaje}</div>
		</c:if>

		<label for="importe" class="sr-only"></label> <input type="text"
			name="importe" class="form-control" placeholder="cantidad en euros" required
			autofocus> <br />
		<br />
		<button type="button" class="btn-lg btn-info ">Buscar</button>
		<br />
		<br />
	</form>
</div>

</main>