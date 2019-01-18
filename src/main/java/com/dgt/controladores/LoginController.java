package com.dgt.controladores;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.log4j.Logger;

import com.dgt.modelo.daos.AgenteDAO;
import com.dgt.modelo.pojos.Agente;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private final static Logger LOG = Logger.getLogger(LoginController.class);

	private ValidatorFactory factory;

	private Validator validator;
	public static final String VIEW_LOGIN = "index.jsp";
	private static AgenteDAO daoAgente = null;
	Agente a = null;

	@Override
	public void init(ServletConfig config) throws ServletException {
		
		super.init(config);

		daoAgente = AgenteDAO.getInstance();

		factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("principal.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String placa = request.getParameter("placa");
		String pass = request.getParameter("pass");
		String nombre = request.getParameter("nombre");
		// String idioma = request.getParameter("idioma");
		String view = VIEW_LOGIN;
		boolean redirect = false;

		try {

			HttpSession session = request.getSession();

//			//guardar cookie
//			Cookie cIdioma = new Cookie("cIdioma", idioma);
//			cIdioma.setMaxAge(60*10); //TODO poner que no expire nunca			
//			response.addCookie(cIdioma);

			// validar
			Agente a = new Agente();
			a.setPlaca(placa);
			a.setNombre(nombre);
			a.setPassword(pass);

			// TODO configurar contraseñas
//			a.setPassword(pass);

			Set<ConstraintViolation<Agente>> violations = validator.validate(a);

			if (violations.size() > 0) { // validacion NO PASA

				String errores = "<ul>";
				for (ConstraintViolation<Agente> violation : violations) {
					errores += String.format("<li> %s : %s </li>", violation.getPropertyPath(), violation.getMessage());
				}
				errores += "</ul>";
				request.setAttribute("mensaje", errores);

			} else { // validacion OK

				a = daoAgente.login(placa, pass);
				if (a == null) {

					request.setAttribute("mensaje", "Login incorrecto");
					
				} else {

					session.setMaxInactiveInterval(60 * 5);
					session.setAttribute("agenteLogueado", a);;
					redirect = true;
					LOG.debug("guardamos en session usuario e idioma");
				}
			}

		} catch (Exception e) {
			LOG.error(e);
		} finally {

			if (redirect) {
				response.sendRedirect(request.getContextPath() + "/principal.jsp");
			} else {
				request.getRequestDispatcher(view).forward(request, response);
			}
		}

	}

}
