<%@ include file="../includes/header.jsp"%>
<%@ include file="../includes/navbar.jsp"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" type="text/css" href="mystyle.css">
<main role="main" class="container"> <a href="principal.jsp"
	class="btn btn-info ">Atras</a>

<ul class="navbar-nav float-right">
	<li><span class=" badge-lg badge-light mr-3 text-success h5">${agenteLogueado.nombre}<img
			src="images/placa.png" />
	</span></li>
</ul>


<br />
<br />


<h2>Multas anuladas</h2>

<table id= "tbla_multasanuladas" class="table tablaOrdenable display responsive nowrap"">
	<thead>
		<tr>
			<th scope="col"></th>
			<th scope="col">Id</th>
			<th scope="col">Matricula</th>
			<th scope="col">Agente</th>
			<th scope="col">Fecha de alta</th>
			<th scope="col">Fecha de baja</th>
			<th scope="col">Importe</th>
			<th scope="col">Concepto</th>
			<th scope="col">Modelo</th>
			<th scope="col">Km</th>
			




		</tr>
	</thead>

	<tbody>

		<c:forEach items="${anulada}" var="ma">
			<tr>
				<th></th>
				<td>${ma.id}</td>
				<td>${ma.coche.matricula}</td>
				<td>${ma.agente.nombre}</td>
				<td scope="row"><fmt:formatDate value="${ma.fecha_alta}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
				<td scope="row"><fmt:formatDate value="${ma.fecha_baja}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
				<td scope="row">${ma.importe}</td>
				<td scope="row">${ma.concepto}</td>
				<td scope="row">${ma.coche.modelo}</td>
				<td scope="row">${ma.coche.km}</td>	
			

			</tr>
	
</c:forEach>

	</tbody>

	
	<tfoot>
		<tr>
			<th scope="col"></th>
			<th scope="col">Id</th>
			<th scope="col">Matricula</th>
			<th scope="col">Agente</th>
			<th scope="col">Fecha de alta</th>
			<th scope="col">Fecha de baja</th>
			<th scope="col">Importe</th>
			<th scope="col">Concepto</th>
			<th scope="col">Modelo</th>
			<th scope="col">Km</th>
		
		</tr>
</tfoot>
</table>

<%@ include file="../includes/footer.jsp"%>