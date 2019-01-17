<%@ include file="../includes/header.jsp"%>
<%@ include file="../includes/navbar.jsp"%>
<%@ include file="../includes/mensajes.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="mystyle.css">

<h2>Lista de multas</h2>
<main role="main" class="container"> <a href="principal.jsp" class="btn btn-info ">Atras</a>
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
<%-- 				<td><a href="multa?op=5&id=${m.id}" class="btn btn-danger">ANULAR</a></td> --%>
				<c:if test="${m.id > 0}">					
				<!-- Button trigger modal -->
				<td><button type="button" class="btn btn-outline-danger btn-block mt-4" data-toggle="modal" data-target="#exampleModal">
				  ANULAR
				</button></td>
		
				<!-- Modal -->
				<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
				  <div class="modal-dialog" role="document">
				    <div class="modal-content">
				      <div class="modal-header">
				        <h5 class="modal-title" id="exampleModalLabel">Atención</h5>
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
				          <span aria-hidden="true">&times;</span>
				        </button>
				      </div>
				      <div class="modal-body">
				        ¿ Estas serguro de que desea anular esta multa ?
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
				        <a href="multa?op=5&id=${m.id}" class="btn btn-danger">ANULAR</a>
				      </div>
				    </div>
				  </div>
				</div>
			
			</c:if>

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