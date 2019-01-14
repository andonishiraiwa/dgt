<%@ include file="../includes/header.jsp"%>
<%@ include file="../includes/navbar.jsp"%>
<main role="main" class="container">
<div class="jumbotron">

	<ul class="navbar-nav float-right">
		<li><span class=" badge-lg badge-light mr-3 text-success h5">${agenteLogueado.nombre}<img
				src="images/placa.png" /> <!-- agente -->
		</span></li>
	</ul>

	<a href="index.jsp" class="btn btn-info ">Atras</a> <br /> <br />

	<h2>
		Especifique los detalles de la multa para <span class="text-info">
			${coche.matricula}</span>
	</h2>


	<br />
	<form novalidate action="multa" method="post" class="form-signin">

		<c:if test="${not empty mensaje}">
			<div class="alert alert-danger alert-dismissible fade show"
				role="alert">${mensaje}</div>
		</c:if>

		<label for="importe" class="h5">Importe</label><br /> <input
			type="text" name="importe" class="form-control"
			placeholder="cantidad en euros" required autofocus> <br /> <label
			for="concepto" class="h5">Concepto</label> <br />
		<textarea name="concepto" class="form-control"
			placeholder="indique el asunto de la multa" required autofocus></textarea>
		<input type="hidden" name="idcoche" value="${coche.id}"> <input
			type="hidden" name="idagente" value="${agente.id}">
		<%-- 		<input type="hidden" name="idmulta" value="${(op == '1') ? multa.id : 0}"> --%>
		<br /> <br /> <input type="hidden" name="op" value="3"> <input
			type="submit" value="Registrar"> <br /> <br />
	</form>
</div>

</main>