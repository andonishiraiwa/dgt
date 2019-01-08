package com.dgt.controladores;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.dgt.modelo.daos.MultaDAO;

/**
 * Servlet implementation class MultaController
 */
@WebServlet("/MultaController")
public class MultaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public MultaController() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
//Pasar datos de multa por parametros (procesarlas con DAO y luego llevarlas al jsp desde aquí)
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//procesor para multas: listar, añadir
		
		
		//switch

		vista = VIEW_INDEX;
		alerta = null;
		try {
			// recoger parametros
			getParametros(request);
			// realizar operacion
			switch (op) {
				case OP_IR_FORMULARIO:
					irFormulario(request);
					break;
				case OP_GUARDAR:
					guardar(request);
					break;	
				
				default:
					listar(request);
					break;
			}
		
		//Interaciones con DAO: Invoke
		

		private void listar(HttpServletRequest request) {
			
			request.setAttribute("multas", MultaDAO.getAll());		
			
		}


		
		
		
		private void getParametros(HttpServletRequest request) {

			op = request.getParameter("op");
			if( op == null ) {
				op = OP_LISTAR;
			} 
			
			
			id = request.getParameter("id");
			 = request.getParameter("nombre");
			 = request.getParameter("codigo");
			 = request.getParameter("id_usuario");
		
			
		}

	}
}
