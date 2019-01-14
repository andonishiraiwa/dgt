package com.dgt.controladores;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.dgt.modelo.daos.AgenteDAO;
import com.dgt.modelo.pojos.Agente;



/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private ValidatorFactory factory;
	@SuppressWarnings("unused")
	private Validator validator;
	
	private static AgenteDAO daoAgente = null;
	Agente a = null;
	
	@Override
    public void init(ServletConfig config) throws ServletException {    
    	super.init(config);
    	
    	daoAgente = AgenteDAO.getInstance();
    	
    	factory  = Validation.buildDefaultValidatorFactory();
    	validator  = factory.getValidator();
    	
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		a = daoAgente.getById(4);
		session.setAttribute("agenteLogueado", a);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
