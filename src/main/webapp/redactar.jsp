<%@ include file="../includes/header.jsp"%>
<%@ include file="../includes/navbar.jsp"%>

<main role="main" class="container">
<div class="jumbotron">

	<ul class="navbar-nav float-right">
		<li><span class=" badge-lg badge-light mr-3 text-success h5">${agenteLogueado.nombre}<img
				src="images/placa.png" /> <!-- agente -->
		</span></li>
	</ul>

	<a href="form.jsp" class="btn btn-info ">Atras</a> <br /> <br />

	<h2>
		Especifique los detalles de la multa para <span class="text-info">
			${coche.matricula}</span>
	</h2>


	<br />
	<form novalidate action="multa" method="post" class="form-signin">

		
<c:if test="${not empty mensaje}">	  
	<div class="alert alert-danger alert-dismissible fade show" role="alert">
	 ${mensaje.texto}			 
	</div>	 	
</c:if>


	<!-- TODO contador de palabras -->

	<div class="col">
			<label>Importe:</label>
			<input type="text" class="form-control" name="importe" placeholder="Importe">
			
		</div>
		<div class="col">
			<label for="concepto">Concepto  <span id="contadorLabel">(0/250)</span></label>
			<textarea id="concepto" class="form-control" name="concepto" placeholder="Concepto" rows="3"></textarea>
		</div>
		<input type="hidden" name="idcoche" value="${coche.id}"> 
		<input type="hidden" name="idagente" value="${agente.id}">
		
		<br/><br/>
		<input type="hidden" name="op" value="3">
		<input type="submit" value="Registrar">
	</form>
	
	
</div>

</main>
<%@ include file="../includes/footer.jsp"  %> 