<link rel="stylesheet" type="text/css" href="mystyle.css">
<table id="example" class="display" style="width:100%">
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
		
		<td>${m.id} </td>
	<td>${m.matricula}</td>
	<td> ${m.fecha}</td>
	
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

