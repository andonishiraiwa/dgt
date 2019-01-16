<%@ include file="../includes/header.jsp"%>
<%@ include file="../includes/navbar.jsp"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" type="text/css" href="mystyle.css">
<main role="main" class="container"> <a href="index.jsp"
	class="btn btn-info ">Atras</a>

<ul class="navbar-nav float-right">
	<li><span class=" badge-lg badge-light mr-3 text-success h5">${agenteLogueado.nombre}<img
			src="images/placa.png" />
	</span></li>
</ul>


<br />
<br />


<h2>Multas anuladas</h2>

<table id="table_id" data-page-length='25'
	class="table tablaOrdenable display">
	<thead>
		<tr>
			<th></th>
			<th>Id</th>
			<th>Matricula</th>
		
			
					<th>Fecha de alta</th>
				
					<th>Fecha de baja</th>
					
			
		

		</tr>
	</thead>

	<tbody>

		<c:forEach items="${anulada}" var="ma">
			<tr>
				<th></th>
				<td>${ma.id}</td>
				<td>${ma.coche.matricula}</td>
			<td scope="row"><fmt:formatDate value="${ma.fecha_alta}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
				
					
					<td scope="row"><fmt:formatDate value="${ma.fecha_baja}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
					
			

			</tr>
	
</c:forEach>

	</tbody>

	
	<tfoot>
		<tr>
			<th></th>
			<th>Id</th>
			<th>Matricula</th>
	
					<th>Fecha de alta</th>
		
					<th>Fecha de alta</th>
					<th>Fecha de baja</th>
		
		</tr>
</tfoot>
</table>

<%@ include file="../includes/footer.jsp"%>