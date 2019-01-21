<%@ include file="../includes/header.jsp"%>
<%@ include file="../includes/navbar.jsp"%>
<%@ include file="../includes/mensajes.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="mystyle.css">

<h2>Objetivos</h2>
<main role="main" class="container"> <a href="principal.jsp"
	class="btn btn-info ">Atras</a>
<div class="container mb-3">
	<div class="row d-flex align-items-center mb-3">
		<h3>Actual</h3>
		<br>
		<hr>
		<table id="actual" class="table">
		
		<p>${objetivos}</p>
		
			<tr>
				<th>Mes</th>
				
				<td>000/ 1.000</td>
				
			</tr>
			<tr>
				<th>Año</th>
				<td>0 / 12.000</td>
			</tr>
		</table>
		<br>
		<hr>
		<h3>Histórico</h3>
		<table id="historico" class="table">
		<thead class="thead-light">
			<tr>
				<th>Mes</th>
				<th>0 / 1.000</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th>Total</th>
				<td></td>
			</tr>
		</tbody>
	</table> 	

	</div>
</div>



</main>

<%@ include file="../includes/footer.jsp"%>