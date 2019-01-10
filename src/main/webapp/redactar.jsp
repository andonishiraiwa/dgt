<%@ include file="../includes/header.jsp"%>
<%@ include file="../includes/navbar.jsp"%>
<main role="main" class="container">
<div class="jumbotron">

	<ul class="navbar-nav float-right">
		<li><span class=" badge-lg badge-light mr-3 text-success h5">Agente
				Takel Berry <img src="images/placa.png" />
			<!-- agente -->
		</span></li>
	</ul>

	<button type="button" class="btn btn-info ">Atras</button>
	<br /> <br />

	<h2>
		Especifique los detalles de la multa para <span class="text-info">0000XYZ
			${matricula}</span>
	</h2>


	<br />
	<form novalidate action="buscar" method="post" class="form-signin">

		<c:if test="${not empty mensaje}">
			<div class="alert alert-danger alert-dismissible fade show"
				role="alert">${mensaje}</div>
		</c:if>

		<label for="importe" class="h5">Importe</label><br /> <input
			type="text" name="importe" class="form-control"
			placeholder="cantidad en euros" required autofocus> <br /> <label
			for="concepto" class="h5">Concepto</label> <br />
		<input type="text" name="concepto" class="form-control"
			placeholder="indique el asunto de la multa" required autofocus>
		<br /> <br />
		<button type="button" class="btn-lg btn-info ">Registrar</button>
		<br /> <br />
	</form>
</div>

</main>