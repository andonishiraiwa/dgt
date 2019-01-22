  <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
     <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>	
	<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
	<script src="https://cdn.datatables.net/responsive/2.2.3/js/dataTables.responsive.min.js"></script>
	
	<script>
	
		$(document).ready(function() {
	    	$('.tablaOrdenable').DataTable(
	    			
	    			{
	    				"pageLength": 25,
	    	            "language": {
	    	                "url": "datatables/spanish.json"
	    	            }
	    	        }
	    	
	    	);
		} );
		</script>
		<script>
		
		  let label;
		    let concepto; //textarea
		    
		    const MAX_CARARCTERES = 250; // TODO cambiar por 250 despues de las pruebas
		    const MIN_CARARCTERES = 10;
		    
		    window.addEventListener('load', function() {
		        
		        console.log('el DOM cargado y listo');
		        label   = document.getElementById('contadorLabel');
		        concepto = document.getElementById('concepto');
		         
		        label.textContent = `(0/` + MAX_CARARCTERES + `)`;
		        label.style.color = 'red';
		        
		        concepto.addEventListener("keyup", function(){
		            
		            let caracteres = concepto.value.length;                             
		            
		            if( caracteres < MIN_CARARCTERES ){
		                 label.style.color = 'red';   
		            }else if ( caracteres  > MAX_CARARCTERES ){
		                concepto.value = concepto.value.substr(0,MAX_CARARCTERES);
		            }else{
		                 label.style.color = 'green';
		            }
		            
		            caracteres = concepto.value.length;
		            label.textContent = `(` + caracteres + `/` + MAX_CARARCTERES + `)`;
		        });
		    });
		
	</script>
	
  </body>
</html>
