<%@ include file="../includes/header.jsp"%>
<%@ include file="../includes/navbar.jsp"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" type="text/css" href="mystyle.css">
<main role="main" class="container"> <a href="index.jsp"
	class="btn btn-info ">Atras</a>

<ul class="navbar-nav float-right">
	<li><span class=" badge-lg badge-light mr-3 text-success h5">${agenteLogueado.nombre}<img
			src="images/placa.png" /> <!-- agente -->
	</span></li>
</ul>


<br />
<br />


<table class="table table-striped">
	<thead>
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
				<c:if test="${op == 'baja'}"> <!-- condicion, actúa o no actúa -->
						<td>${m.fechaBaja}</td>
					</c:if>
				<%-- <td>${m.fecha_baja}</td> --%>

				<!-- </span> -->
			</tr>
		</c:forEach>


	</tbody>

	</main>



	<tfoot>
		<tr>
			<th></th>
			<th>Id</th>
			<th>Matricula</th>
			<th>Fecha</th>
		</tr>
	</tfoot>
</table>
<%@ include file="../includes/footer.jsp"%>