package com.dgt.controladores;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.dgt.modelo.daos.ObjetivoDAO;
import com.dgt.modelo.pojos.Agente;
import com.dgt.modelo.pojos.Alerta;
import com.dgt.modelo.pojos.Coche;
import com.dgt.modelo.pojos.Multa;

/**
 * Servlet implementation class ObjetivoController
 */
@WebServlet("/objetivo")
public class ObjetivoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOG = Logger.getLogger(ObjetivoController.class);

	private static final String VIEW_OBJET = "objetivos.jsp";
	private String vista;

	private static ObjetivoDAO daoObjetivo = null;

	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setAttribute("objetivos", daoObjetivo.getMonth());
		vista = VIEW_OBJET;
		request.getRequestDispatcher(vista).forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/*private void estadisticas(HttpServletRequest request) {

		request.setAttribute("objetivos", daoObjetivo.getMonth());

		vista = VIEW_OBJET;
	}*/

}
