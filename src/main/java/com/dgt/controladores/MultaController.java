package com.dgt.controladores;

import java.io.IOException;
import java.util.Date;
import java.sql.SQLException;
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

//import com.dgt.modelo.daos.AgenteDAO;
import com.dgt.modelo.daos.CocheDAO;
import com.dgt.modelo.daos.MultaDAO;
import com.dgt.modelo.pojos.Agente;
import com.dgt.modelo.pojos.Alerta;
import com.dgt.modelo.pojos.Coche;
import com.dgt.modelo.pojos.Multa;

//import com.dgt.modelo.pojos.Alerta;

/**
 * Servlet implementation class MultaController
 */
@WebServlet("/multa")
public class MultaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOG = Logger.getLogger(MultaController.class);

	private ValidatorFactory factory;
	private Validator validator;

	private static final String VIEW_MULTAS = "multas.jsp";
	// Andoni
	private static final String VIEW_NULAS = "anuladas.jsp";
	private static final String VIEW_FORM = "form.jsp";
	private static final String VIEW_REDACTAR = "redactar.jsp";
	private static final String VIEW_MPPAL = "index.jsp";
	private String vista;

	public static final String OP_LISTAR = "1";
	public static final String OP_IR_FORMULARIO = "2";
	public static final String OP_GUARDAR = "3"; // id == -1 insert , id > 0 update
	public static final String OP_BUSCAR_MATRICULA = "4"; // buscar por matricula, pasarle el parametro de matricula a
															// redactar.jsp
	public static final String OP_ANULAR = "5";
	// Andoni
	public static final String OP_LISTAR_ANULADAS = "6";

	private Alerta alerta;

	// parametros

	private String op;
	String opanular = "";
	private String id;
	private String importe;
	private String concepto;

	private String fecha_alta;

	private String fecha_modificacion;

	private String fecha_baja;

	private String id_agente = "";
	private String id_coche = "";

	String matricula = "";

	private String nombre;
	private Alerta mensaje;
	Coche c = null;
	Agente a = null;
	Multa m = null;
	HttpSession session;
	String multado;

	private static MultaDAO daoMulta = null;

	private static CocheDAO daoCoche = null;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		daoMulta = MultaDAO.getInstance();

		daoCoche = CocheDAO.getInstance();

		factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	public MultaController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// procesor para multas: listar, añadir

		// switch

		vista = VIEW_MULTAS;
//		alerta = null;
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
			case OP_BUSCAR_MATRICULA:
				buscar(request);
				break;
			case OP_ANULAR:
				anular(request);
				break;

			// Andoni

			case OP_LISTAR:
				listar(request);
				break;

			case OP_LISTAR_ANULADAS:
				listarAnuladas(request);
				break;
			default:
				mensaje = new Alerta(Alerta.TIPO_DANGER, "Operación incorrecta");
				vista = VIEW_MPPAL;
			}
		} catch (Exception e) {
			LOG.error(e);
			alerta = new Alerta(Alerta.TIPO_DANGER, "Error inexesperado sentimos las molestias.");

		} finally {
			// mensaje para el usuario
			request.setAttribute("alerta", alerta);
			// ir a una vista
			request.getRequestDispatcher(vista).forward(request, response);
		}
	}

	// Interaciones con DAO: Invoke

	private void buscar(HttpServletRequest request) {

		

		// validar matricula OK

		try {

			c = daoCoche.getByMatri(matricula);

		} catch (Exception e) {

		}
		if (c != null) {

			request.setAttribute("coche", c);

			request.setAttribute("fecha_alta", new Date());

			vista = VIEW_REDACTAR;
		} else {

			request.setAttribute("mensaje", "La matrícula no existe");

			vista = VIEW_FORM;
		}
	}

	private void listar(HttpServletRequest request) {

		request.setAttribute("multa", daoMulta.getMulta(a.getId()));

		vista = VIEW_MULTAS;
	}

//Andoni	

	private void listarAnuladas(HttpServletRequest request) {

		request.setAttribute("anulada", daoMulta.getMultaAnulada(a.getId()));
		vista = VIEW_NULAS;

	}

	private void guardar(HttpServletRequest request) {

		// crear video mediante parametros del formulario
		m = new Multa();
		c = new Coche();
		m.setImporte(Float.parseFloat(importe));
		m.setConcepto(concepto);
		c.setId(Long.parseLong(id_coche));
		m.setCoche(c);
		m.setAgente((Agente) session.getAttribute("agenteLogueado"));

		// validar usuario
		Set<ConstraintViolation<Multa>> violations = validator.validate(m);

//		//Validacion

		if (violations.size() > 0) { // validacion NO correcta
			vista = VIEW_FORM;
			c = new Coche();

			request.setAttribute("coche", c);
			String mensaje = "<ul>";
			for (ConstraintViolation<Multa> violation : violations) {
				mensaje += String.format("<li> %s : %s </li>", violation.getPropertyPath(), violation.getMessage());
			}
			mensaje += "</ul>";
			request.setAttribute("mensaje", mensaje);

		} else { // validacion correcta
//// condiciones : importe mayor que 1 y concepto string entre 10 y 250
			try {
				daoMulta.insert(m);

				alerta = new Alerta(Alerta.TIPO_SUCCESS, "Multa guardada con exito");
				id = null;
				listar(request);

			} catch (SQLException e) {
				alerta = new Alerta(Alerta.TIPO_WARNING, "Lo sentimos, vueva a intentar redactar la multa");
				vista = VIEW_FORM;
				request.setAttribute("multa", m);

			}
		}

	}

	private void anular(HttpServletRequest request) {
		long identificador = Long.parseLong(id);
		Multa m = new Multa();
		m.setId(identificador);
		try {
			daoMulta.update(m);
			request.setAttribute("mensaje", "multa anulada");
			LOG.debug("Multa anulada");
			// TODO ir a vista multas anuladas
			vista = VIEW_NULAS;
			// devolver a la lista de anuladas pero cargando de nuevo los datos
			listarAnuladas(request);
		} catch (Exception e) {
			request.setAttribute("mensaje", "multa no  anulada");
			LOG.debug("Multa no anulada");
		}
	}

	private void irFormulario(HttpServletRequest request) {

		vista = VIEW_FORM;
		@SuppressWarnings("unused")
		Multa m = new Multa();

		int identificador = Integer.parseInt(id);
		if (identificador > 0) {
			m = daoMulta.getById(identificador);
		}
		// request.setAttribute("video", m);
		request.setAttribute("multa", daoMulta.getMulta(a.getId()));

	}

	private void getParametros(HttpServletRequest request) {

		session = request.getSession();
		a = (Agente) session.getAttribute("agenteLogueado");

		op = request.getParameter("op");
		if (op == null) {
			op = OP_LISTAR;
		}
		opanular = request.getParameter("opanular");
		id = request.getParameter("id");

		importe = request.getParameter("importe");

		if (importe == null || importe.equals("")) {

			// asignamos por defecto 0 para que el parseFloat no falle
			importe = "0";

		}

		concepto = request.getParameter("concepto");
		fecha_alta = request.getParameter("fecha_alta");
		fecha_modificacion = request.getParameter("fecha_modificacion");
		fecha_baja = request.getParameter("fecha_baja");
		id_coche = request.getParameter("idcoche");
		id_agente = request.getParameter("idagente");
		matricula = request.getParameter("matricula");
		nombre = request.getParameter("nombre");

		multado = request.getParameter("multa");

	}

}

//package com.dgt.controladores;
//
//import java.io.IOException;
//import java.util.Date;
//import java.sql.SQLException;
//import java.util.Set;
//
//import javax.servlet.ServletConfig;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import javax.validation.ConstraintViolation;
//import javax.validation.Validation;
//import javax.validation.Validator;
//import javax.validation.ValidatorFactory;
//
//import org.apache.log4j.Logger;
//
////import com.dgt.modelo.daos.AgenteDAO;
//import com.dgt.modelo.daos.CocheDAO;
//import com.dgt.modelo.daos.MultaDAO;
//import com.dgt.modelo.pojos.Agente;
//import com.dgt.modelo.pojos.Alerta;
//import com.dgt.modelo.pojos.Coche;
//import com.dgt.modelo.pojos.Multa;
//
////import com.dgt.modelo.pojos.Alerta;
//
///**
// * Servlet implementation class MultaController
// */
//@WebServlet("/multa")
//public class MultaController extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	private final static Logger LOG = Logger.getLogger(MultaController.class);
//
//	private ValidatorFactory factory;
//	private Validator validator;
//
//	private static final String VIEW_MULTAS = "multas.jsp";
//	// Andoni
//	private static final String VIEW_NULAS = "anuladas.jsp";
//	private static final String VIEW_FORM = "form.jsp";
//	private static final String VIEW_REDACTAR = "redactar.jsp";
//	private static final String VIEW_MPPAL = "index.jsp";
//	private String vista;
//
//	public static final String OP_LISTAR = "1";
//	public static final String OP_IR_FORMULARIO = "2";
//	public static final String OP_GUARDAR = "3"; // id == -1 insert , id > 0 update
//	public static final String OP_BUSCAR_MATRICULA = "4"; // buscar por matricula, pasarle el parametro de matricula a
//															// redactar.jsp
//	public static final String OP_ANULAR = "5";
//	// Andoni
//	public static final String OP_LISTAR_ANULADAS = "6";
//
//	private Alerta alerta;
//
//	// parametros
//
//	private String op;
//	String opanular = "";
//	private String id;
//	private String importe;
//	private String concepto;
//
////	warnings
//
//	private String fecha_alta;
//	private String fecha_modificacion;
//	private String fecha_baja;
//	private String id_agente = "";
//	private String nombre;
//
//	private String id_coche = "";
//	String matricula = "";
//
//	Coche c = null;
//	Agente a = null;
//	Multa m = null;
//	HttpSession session;
//	String multado;
//
//	private static MultaDAO daoMulta = null;
//
//	private static CocheDAO daoCoche = null;
//
//	@Override
//	public void init(ServletConfig config) throws ServletException {
//		super.init(config);
//		daoMulta = MultaDAO.getInstance();
//
//		daoCoche = CocheDAO.getInstance();
//
//		factory = Validation.buildDefaultValidatorFactory();
//		validator = factory.getValidator();
//	}
//
//	public MultaController() {
//		super();
//
//	}
//
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		doProcess(request, response);
//
//	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//
//		doProcess(request, response);
//	}
//
//	private void doProcess(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//
//		// procesor para multas: listar, añadir
//
//		// switch
//
//		vista = VIEW_MULTAS;
////		alerta = null;
//		try {
//			// recoger parametros
//			getParametros(request);
//			// realizar operacion
//			switch (op) {
//			case OP_IR_FORMULARIO:
//				irFormulario(request);
//				break;
//			case OP_GUARDAR:
//				guardar(request);
//				break;
//			case OP_BUSCAR_MATRICULA:
//				buscar(request);
//				break;
//			case OP_ANULAR:
//				anular(request);
//				break;
//
//			// Andoni
//
//			case OP_LISTAR:
//				listar(request);
//				break;
//
//			case OP_LISTAR_ANULADAS:
//				listarAnuladas(request);
//				break;
//			default:
//				alerta = new Alerta(Alerta.TIPO_DANGER, "Operación incorrecta");
//				vista = VIEW_MPPAL;
//			}
//		} catch (Exception e) {
//			LOG.error(e);
//			alerta = new Alerta(Alerta.TIPO_DANGER, "Error inexesperado sentimos las molestias.");
//
//		} finally {
//			// mensaje para el usuario
//			request.setAttribute("alerta", alerta);
//			// ir a una vista
//			request.getRequestDispatcher(vista).forward(request, response);
//		}
//	}
//
//	// Interaciones con DAO: Invoke
//
//	@SuppressWarnings("static-access")
//	private void buscar(HttpServletRequest request) {
//
//		// obtener parametro de matricula
//		/*
//		 * c = daoCoche.getByMatri(matricula);
//		 * 
//		 * if(c != null) {
//		 * 
//		 * request.setAttribute("coche", c); request.setAttribute("fecha", new Date());
//		 * vista = VIEW_FORM; }
//		 */
//
//		// validar matricula OK
//
//		try {
//
//			c = daoCoche.getByMatri(matricula);
//
//		} catch (Exception e) {
//
//		}
//		if (c != null) {
//
//			request.setAttribute("coche", c);
//
//			request.setAttribute("fecha_alta", new Date());
//
//			vista = VIEW_REDACTAR;
//		} else {
//
//			request.setAttribute("mensaje", "La matrícula no existe");
//
//			vista = VIEW_FORM;
//		}
//	}
//
//	private void listar(HttpServletRequest request) {
//
//		request.setAttribute("multa", daoMulta.getMulta());
//		vista = VIEW_MULTAS;
//	}
//
////Andoni	
//
//	private void listarAnuladas(HttpServletRequest request) {
//
//		request.setAttribute("anulada", daoMulta.getMultaAnulada());
//		vista = VIEW_NULAS;
//
//	}
//
//	private void guardar(HttpServletRequest request) {
//
//		// crear video mediante parametros del formulario
//		m = new Multa();
//	c = new Coche();
//		m.setImporte(Float.parseFloat(importe));
//		m.setConcepto(concepto);
//		int coche_id = Integer.parseInt(id);
////		c.setId((long) coche_id);
//		
//		c.setMatricula(matricula);
//		m.setCoche(c);
//		m.setAgente((Agente) session.getAttribute("agenteLogueado"));
//
//		// validar usuario
//		Set<ConstraintViolation<Multa>> violations = validator.validate(m);
//
////		//Validacion
////		
////		if (violations.size() > 0) { // validacion NO correcta
////			
////			vista = VIEW_REDACTAR;
////			
////			alerta = new Alerta(Alerta.TIPO_WARNING, "Los campos introduciodos no son correctos, por favor intentalo de nuevo");	
////		
////			// volver al formulario, cuidado que no se pierdan los valores en el form
////
////			request.setAttribute("multa", m);
////			
////			
////
////		} else { // validacion correcta
////// condiciones : importe mayor que 1 y concepto string entre 10 y 250
//			try {
//				daoMulta.insert(m);
//
//				alerta = new Alerta(Alerta.TIPO_SUCCESS, "Multa guardada con exito");
//				id = null;
//				listar(request);
//
//			} catch (SQLException e) {
//				alerta = new Alerta(Alerta.TIPO_WARNING, "Lo sentimos, vueva a intentar redactar la multa");
//				vista = VIEW_FORM;
//				request.setAttribute("multa", m);
//
//			}
//		}
////
////	}
//
//	private void anular(HttpServletRequest request) {
//		long identificador = Long.parseLong(id);
//		Multa m = new Multa();
//		m.setId(identificador);
//		try {
//			daoMulta.update(m);
//			request.setAttribute("mensaje", "multa anulada");
//			LOG.debug("Multa anulada");
//			// TODO ir a vista multas anuladas
//			listarAnuladas(request);
//		} catch (Exception e) {
//			request.setAttribute("mensaje", "multa no  anulada");
//			LOG.debug("Multa no anulada");
//		}
//	}
//
//	private void irFormulario(HttpServletRequest request) {
//
//		vista = VIEW_FORM;
//		@SuppressWarnings("unused")
//		Multa m = new Multa();
//
//		int identificador = Integer.parseInt(id);
//		if (identificador > 0) {
//			m = daoMulta.getById(identificador);
//		}
//		// request.setAttribute("video", m);
//		request.setAttribute("multa", daoMulta.getMulta());
//
//	}
//
//	private void getParametros(HttpServletRequest request) {
//
//		session = request.getSession();
//		a = (Agente) session.getAttribute("agenteLogueado");
//
//		op = request.getParameter("op");
//		if (op == null) {
//			op = OP_LISTAR;
//		}
//		opanular = request.getParameter("opanular");
//		id = request.getParameter("id");
//		
//		
//		
//		importe = request.getParameter("importe");
//
//		if (importe == null || importe.equals("")) {
//
//			// asignamos por defecto 0 para que el parseFloat no falle
//			importe = "0";
//
//		}
//		matricula = request.getParameter("matricula");
//
////		}
//		concepto = request.getParameter("concepto");
//		fecha_alta = request.getParameter("fecha_alta");
//		fecha_modificacion = request.getParameter("fecha_modificacion");
//		fecha_baja = request.getParameter("fecha_baja");
////		id_coche = request.getParameter("id_coche");
//		id_agente = request.getParameter("id_agente");
//		
//		nombre = request.getParameter("nombre");
//
//		multado = request.getParameter("multa");
//
//	}
//
//}
