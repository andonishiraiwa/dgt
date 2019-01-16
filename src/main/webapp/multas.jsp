<%@ include file="../includes/header.jsp"%>
<%@ include file="../includes/navbar.jsp"%>
<%@ include file="../includes/mensajes.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="mystyle.css">

<h2>Lista de multas</h2>
<main role="main" class="container"> <a href="index.jsp" class="btn btn-info ">Atras</a>
<br>
<br>
<a href="multa?op=2&id=-1" class="btn btn-outline-success mb-2">Crear Nuevo Registro</a>
	

	
<table id= "tbla_multas" class="table tablaOrdenable display responsive nowrap">
	<thead class="thead-dark">
		<tr>
			<th scope="col"></th>
			<th scope="col">Id</th>
			<th scope="col">Matricula</th>
			<th scope="col">Fecha de alta</th>
			<th scope="col">Importe</th>
			<th scope="col">Concepto</th>
			<th scope="col">Modelo</th>
			<th scope="col">Km</th>
			<th scope="col">Anulación</th>
			

		</tr>
	</thead>
	<tbody>

		<c:forEach items="${multa}" var="m">
			<tr>
				<!-- <span class="border border-primary"> -->
				<th scope="row"></th>
				<td scope="row">${m.id}</td>
				<td scope="row">${m.coche.matricula}</td>
				<td scope="row"><fmt:formatDate value="${m.fecha_alta}" pattern="yyyy-MM-dd hh:mm:ss" /></td>
				<td scope="row">${m.importe}</td>
				<td scope="row">${m.concepto}</td>
				<td scope="row">${m.coche.modelo}</td>
				<td scope="row">${m.coche.km}</td>
				<td><a href="multa?op=5&id=${m.id}" class="btn btn-danger">ANULAR</a></td>
				

				<!-- </span> -->
			</tr>
		</c:forEach>


	</tbody>

	<tfoot class="thead-dark">
		<tr>
			<th scope="col"></th>
			<th scope="col">Id</th>
			<th scope="col">Matricula</th>
			<th scope="col">Fecha de alta</th>
			<th scope="col">Importe</th>
			<th scope="col">Concepto</th>
			<th scope="col">Modelo</th>
			<th scope="col">Km</th>
			<th scope="col">Anulación</th>
			

		</tr>


	</tfoot>
</table>




</main>







<%@ include file="../includes/footer.jsp"%>