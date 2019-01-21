<%@ include file="../includes/header.jsp"%>
<%@ include file="../includes/navbar.jsp"%>
<script>
         
       var label;
       var concepto; //textarea
       
       const MAX_CARACTERES = 250; 
       const MIN_CARACTERES = 5;
       
       window.addEventListener('load', function() {
           
           console.log('el DOM cargado y listo');
           label   = document.getElementById('limiteCaracteres');
           concepto = document.getElementById('concepto');
            
           label.textContent = `(0/${MAX_CARACTERES})`;
           label.style.color = 'red';
           
           concepto.addEventListener("keyup", function(){
               
               var caracteres = concepto.value.length;                             
               
               if( caracteres < MIN_CARACTERES ){
                    label.style.color = 'red';   
                    
               }else if ( caracteres  > MAX_CARACTERES ){
                   concepto.value = concepto.value.substr(0,MAX_CARACTERES);
                   
               }else{
                    label.style.color = 'green';
               }
               
               caracteres = concepto.value.length;
               label.textContent = `(${caracteres}/${MAX_CARACTERES})`;
               
           });
           
           
       });
    
   </script>
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

		<c:if test="${not empty alerta}">
			<div class="alert alert-danger alert-dismissible fade show"
				role="alert">${alerta}</div>
		</c:if>


	<!-- TODO contador de palabras -->

		<label for="importe" class="h5">Importe</label><br /> 
		
		<input type="text" name="importe" class="form-control"placeholder="cantidad en euros" required autofocus> 
		<br />
		  <label for="concepto">Concepto  <span id="limiteCaracteres">(0/5)</span></label>
   <br>
   <textarea id="concepto" cols="40" rows="10"></textarea>


   
		  <br />
		<textarea name="concepto" class="form-control" placeholder="indique el asunto de la multa" required autofocus></textarea>
		<input type="hidden" name="idcoche" value="${coche.id}"> 
		<input type="hidden" name="idagente" value="${agente.id}">
		<%-- 		<input type="hidden" name="idmulta" value="${(op == '1') ? multa.id : 0}"> --%>
		
		<br /> <br /> 
		
		<input type="hidden" name="op" value="3">
		 <input	type="submit" value="Registrar">
		 
		  <br /> <br />
	</form>
</div>

</main>