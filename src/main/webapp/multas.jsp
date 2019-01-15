<%@ include file="../includes/header.jsp"%>
<%@ include file="../includes/navbar.jsp"%>
<%@ include file="../includes/mensajes.jsp"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<h2>Lista de multas</h2>
    <main role="main" class="container">
	
		<a href="privado/usuarios?op=2&id=-1" class="btn btn-outline-success mb-2">Crear Nuevo Registro</a>
		
		<table class="table tablaOrdenable responsive nowrap">
		  <thead class="thead-dark">
		   <tr>
			<th></th>
			<th>Id</th>
			<th>Matricula</th>
			<!-- <th>Fecha</th> -->
			<c:choose>
				<c:when test="${op != 'baja'}">
					<th>Fecha de alta</th>
				</c:when>
				<c:otherwise>
					<th>Fecha de alta</th>
					<th>Fecha de baja</th>
					<th class="dt-body-right sorting" tabindex="0"
						aria-controls="example" rowspan="1" colspan="1"
						style="width: 0px; display: none;"
						aria-label="Salary: activate to sort column ascending">Salary</th>
				</c:otherwise>
			</c:choose>

		</tr>
		  </thead>
		  <tbody>
		 	<c:forEach items="${multa}" var="m">
			<tr>
				<!-- <span class="border border-primary"> -->
				<th></th>
				<td>${m.id}</td>
				<td>${m.coche.matricula}</td>
				<td>${m.fecha_alta}</td>
				<c:if test="${op == 'baja'}">
					<!-- condicion, actúa o no actúa -->
					<td>${m.fechaBaja}</td>
					<td id="tablaOrdenable" style="display: none;">$162,700</td>
				</c:if>
				<td>${m.fecha_baja}</td>

				<!-- </span> -->
			</tr>
		</c:forEach>


	</tbody>
	
	<tfoot>
		<tr>
			<th></th>
			<th>Id</th>
			<th>Matricula</th>
			<c:choose>
				<c:when test="${op != 'baja'}">
					<th>Fecha de alta</th>
				</c:when>
				<c:otherwise>
					<th>Fecha de alta</th>
					<th>Fecha de baja</th>
				</c:otherwise>
			</c:choose>
		</tr>
		
		
	</tfoot>
		</table>


<table id="example" class="display" style="width: 100%">
	<thead>
		<tr>
			<th></th>
			<th>Name</th>
			<th>Position</th>
			<th>Office</th>
			<th>Salary</th>
		</tr>
	</thead>
	<tfoot>
		<tr>
			<th></th>
			<th>Name</th>
			<th>Position</th>
			<th>Office</th>
			<th>Salary</th>
		</tr>
	</tfoot>
</table>


	</main>				






<%-- <link rel="stylesheet" type="text/css" href="mystyle.css">
<main role="main" class="container"> <a href="index.jsp"
	class="btn btn-info ">Atras</a>

<ul class="navbar-nav float-right">
	<li><span class=" badge-lg badge-light mr-3 text-success h5">${agenteLogueado.nombre}<img
			src="images/placa.png" /> <!-- agente -->
	</span></li>
</ul>

 
<br />
<br />
 <main role="main" class="container">

<!-- El elemento de la tabla donde hay que situar el boton verde/rojo <td class=" details-control bg-info"></td> -->

<table class="table tablaOrdenable responsive nowrap">
		  <thead class="thead-dark">
		<tr>
			<th></th>
			<th>Id</th>
			<th>Matricula</th>
			<!-- <th>Fecha</th> -->
			<c:choose>
				<c:when test="${op != 'baja'}">
					<th>Fecha de alta</th>
				</c:when>
				<c:otherwise>
					<th>Fecha de alta</th>
					<th>Fecha de baja</th>
					<th class="dt-body-right sorting" tabindex="0"
						aria-controls="example" rowspan="1" colspan="1"
						style="width: 0px; display: none;"
						aria-label="Salary: activate to sort column ascending">Salary</th>
				</c:otherwise>
			</c:choose>

		</tr>
	</thead>

	<tbody>

		<c:forEach items="${multa}" var="m">
			<tr>
				<!-- <span class="border border-primary"> -->
				<th></th>
				<td>${m.id}</td>
				<td>${m.coche.matricula}</td>
				<td>${m.fecha_alta}</td>
				<c:if test="${op == 'baja'}">
					<!-- condicion, actúa o no actúa -->
					<td>${m.fechaBaja}</td>
					<td id="tablaOrdenable" style="display: none;">$162,700</td>
				</c:if>
				<td>${m.fecha_baja}</td>

				<!-- </span> -->
			</tr>
		</c:forEach>


	</tbody>





	<tfoot>
		<tr>
			<th></th>
			<th>Id</th>
			<th>Matricula</th>
			<c:choose>
				<c:when test="${op != 'baja'}">
					<th>Fecha de alta</th>
				</c:when>
				<c:otherwise>
					<th>Fecha de alta</th>
					<th>Fecha de baja</th>
				</c:otherwise>
			</c:choose>
		</tr>
		
		
	</tfoot>
</table>



<!-- https://datatables.net/examples/api/row_details.html -->

<table id="example" class="display" style="width: 100%">
	<thead>
		<tr>
			<th></th>
			<th>Name</th>
			<th>Position</th>
			<th>Office</th>
			<th>Salary</th>
		</tr>
	</thead>
	<tfoot>
		<tr>
			<th></th>
			<th>Name</th>
			<th>Position</th>
			<th>Office</th>
			<th>Salary</th>
		</tr>
	</tfoot>
</table>
	</main> --%>
<%@ include file="../includes/footer.jsp"%>