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
	//Andoni
	private static final String VIEW_MULTAS_ANULADAS = "anuladas.jsp";
	private static final String VIEW_FORM = "form.jsp";
	private static final String VIEW_REDACTAR = "redactar.jsp";
	private String vista;

	public static final String OP_LISTAR = "1";
	public static final String OP_IR_FORMULARIO = "2";
	public static final String OP_GUARDAR = "3"; // id == -1 insert , id > 0 update
	public static final String OP_BUSCAR_MATRICULA = "4"; // buscar por matricula, pasarle el parametro de matricula a
															// redactar.jsp
	public static final String OP_ANULAR = "5";
	//Andoni
	public static final String OP_LISTAR_ANULADAS = "6";

	private Alerta alerta;

	// parametros

	private String op;
	String opanular = "";
	private String id;
	private String importe;
	private String concepto;
	@SuppressWarnings("unused")
	private String fecha_alta;
	@SuppressWarnings("unused")
	private String fecha_modificacion;
	@SuppressWarnings("unused")
	private String fecha_baja;
	@SuppressWarnings("unused")
	private String id_agente = "";
	private String id_coche = "";

	String matricula = "";
	@SuppressWarnings("unused")
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
				
				//Andoni
			case OP_LISTAR_ANULADAS:
				listarAnuladas(request);
			default:
				listar(request);
				break;
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

	@SuppressWarnings("static-access")
	private void buscar(HttpServletRequest request) {

		// obtener parametro de matricula
		/*
		 * c = daoCoche.getByMatri(matricula);
		 * 
		 * if(c != null) {
		 * 
		 * request.setAttribute("coche", c); request.setAttribute("fecha", new Date());
		 * vista = VIEW_FORM; }
		 */

		try {

			c = daoCoche.getByMatri(matricula);

		} catch (Exception e) {
			mensaje = new Alerta(mensaje.TIPO_DANGER, "No hemos podido encontrar la matricula");

		}
		if (c != null) {

			request.setAttribute("coche", c);
			request.setAttribute("fecha_alta", new Date());

			vista = VIEW_REDACTAR;

		} else {
			mensaje = new Alerta(mensaje.TIPO_DANGER, "La matrícula no existe");
			vista = VIEW_FORM;
		}
	}

	private void listar(HttpServletRequest request) {

		request.setAttribute("multa", daoMulta.getMulta());

	}

//Andoni	
	
	private void listarAnuladas(HttpServletRequest request) {

		request.setAttribute("anulada", daoMulta.getMultaAnulada());

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

		if (violations.size() > 0) { // validacion NO correcta

			alerta = new Alerta(Alerta.TIPO_WARNING,
					"Los campos introduciodos no son correctos, por favor intentalo de nuevo");
			vista = VIEW_FORM;
			// volver al formulario, cuidado que no se pierdan los valores en el form
			request.setAttribute("multa", m);

		} else { // validacion correcta

			try {
				if (daoMulta.insert(m)) {

					alerta = new Alerta(Alerta.TIPO_SUCCESS, "Multa guardada con exito");
					id = null;
					listar(request);

				}

			} catch (SQLException e) {
				alerta = new Alerta(Alerta.TIPO_WARNING, "Lo sentimos, tendrá que multar otro día");
				vista = VIEW_FORM;
				request.setAttribute("multa", m);

			}
		}

	}

	private void anular(HttpServletRequest request) {
		try {
			request.setAttribute("multa", daoMulta.update(daoMulta.getById(Long.parseLong(multado))));
			op = OP_LISTAR;
			op = "opanular";
			vista = VIEW_MULTAS_ANULADAS;
		} catch (SQLException e) {
			LOG.error("No se puede anular la multa", e);
			vista = VIEW_MULTAS;
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
		request.setAttribute("multa", daoMulta.getMulta());

	}

	private void getParametros(HttpServletRequest request) {

		session = request.getSession();
		a = (Agente) session.getAttribute("agenteLogueado");

		op = request.getParameter("op");
		if (op == null) {
			op = OP_LISTAR;
		}
		opanular = request.getParameter("opanular");
		id = request.getParameter("idmulta");
		importe = request.getParameter("importe");
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
