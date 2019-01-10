<%@ include file="../includes/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" type="text/css" href="mystyle.css">

${multa}

<table class="table table-striped">
	<thead>
		<tr>
			<th></th>
			<th>Id</th>
			<th>Matricula</th>
			<th>Fecha</th>


		</tr>
	</thead>

	<tbody>

		<c:forEach items="${multa}" var="m">
			<span class="border border-primary">

				<td>${m.id}</td>
				<td>${m.matricula}</td>
				<td>${m.fecha}</td>

			</span>

		</c:forEach>


	</tbody>





	<tfoot>
		<tr>
			<th></th>
			<th>Id</th>
			<th>Matricula</th>
			<th>Fecha</th>
		</tr>
	</tfoot>
</table>
</body>
</html>

