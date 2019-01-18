<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!doctype html>

  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
   
    <title>Identifiquese</title>

    <!-- Bootstrap core CSS -->
    <link href="https://getbootstrap.com/docs/4.1/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/login.css" rel="stylesheet">
  </head>

  <body class="text-center"> 	
  
  	  
  	
  
    <form novalidate action="login" method="post" class="form-signin">    
    
     <c:if test="${not empty mensaje}">	  
		 <div class="alert alert-danger alert-dismissible fade show" role="alert">
		  ${mensaje}			 
		 </div>	 	
	  </c:if>   
      
      <label for="placa" class="sr-only">Nº de Placa:</label>
      <input type="text" name="placa" class="form-control" value="1578" placeholder="nº de placa" required autofocus>
      
      <label for="pass" class="sr-only">Contraseña</label>
      <input type="password" name="pass" class="form-control" value="1578MJ" placeholder="minimo 6 caracteres" required>
      
      
      
      <button class="btn btn-lg btn-primary btn-block" type="submit">
      Acceder
      </button>
      
    </form>

    
  </body>
</html>
