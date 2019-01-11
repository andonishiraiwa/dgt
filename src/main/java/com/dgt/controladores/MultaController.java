package com.dgt.controladores;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.log4j.Logger;

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
	private static final String VIEW_FORM = "form.jsp";
	private String vista;
	
	public static final String OP_LISTAR = "1";
	public static final String OP_IR_FORMULARIO = "2";
	public static final String OP_GUARDAR = "3"; // id == -1 insert , id > 0 update
	
	
	private Alerta alerta;
	
	//parametros	
	
	private String op;
	private String id;
	private String importe;
	private String concepto;
	private String fecha;
	private String id_agente;
	private String id_coche;
	
	private String matricula;
	
	
	
	
	
    private static MultaDAO daoMulta = null;   
      
    
	
    @Override
    public void init(ServletConfig config) throws ServletException {    
    	super.init(config);
    	daoMulta = MultaDAO.getInstance();
    	
    	factory  = Validation.buildDefaultValidatorFactory();
    	validator  = factory.getValidator();
    }
    
       
   
    public MultaController() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doProcess(request, response);
	}
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//procesor para multas: listar, añadir
		
		
		//switch

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
				
				default:
					listar(request);
					break;
			}
		}
		catch (Exception e) {
			LOG.error(e);		
			alerta = new Alerta( Alerta.TIPO_DANGER, "Error inexesperado sentimos las molestias.");
			
		}finally {
			// mensaje para el usuario
			request.setAttribute("alerta", alerta);
			// ir a una vista
			request.getRequestDispatcher(vista).forward(request, response);
		}	
	}
		//Interaciones con DAO: Invoke
		

		private void listar(HttpServletRequest request) {
			
			request.setAttribute("multa", daoMulta.getMulta());		
			
		}


		private void guardar(HttpServletRequest request) {

			//crear video mediante parametros del formulario
			Multa m = new Multa();
			int identificador = Integer.parseInt(id);	
			m.setId( (long)identificador);
			m.setImporte(Float.parseFloat(importe));
			m.setConcepto(concepto);
			m.setFecha(Date.valueOf(fecha));
			
			
			Agente a = new Agente();
			a.setId( Long.parseLong(id_agente));
			m.setAgente(a);
			
			Coche c= new Coche();
			c.setId(Long.parseLong(id_coche));
			m.setCoche(c);
			
			
			
			
			//validar usuario		
			Set<ConstraintViolation<Multa>> violations = validator.validate(m);
			
			
			if ( violations.size() > 0 ) {              // validacion NO correcta
			 
			  alerta = new Alerta( Alerta.TIPO_WARNING, "Los campos introduciodos no son correctos, por favor intentalo de nuevo");		 
			  vista = VIEW_FORM; 
			  // volver al formulario, cuidado que no se pierdan los valores en el form
			  request.setAttribute("multa", m);	
			 
		 
			  
			  
			  
			}else {									  //  validacion correcta
			
				try {
					if ( identificador <= 0 ) {
						
					
									
								
						daoMulta.insert(m);
					
					alerta = new Alerta( Alerta.TIPO_SUCCESS, "Multa guardada con exito");
					listar(request);
					
				}
					
				}catch ( SQLException e) {
					alerta = new Alerta( Alerta.TIPO_WARNING, "Lo sentimos, tendrá que multar otro día");
					vista = VIEW_FORM;
					request.setAttribute("multa", m);
				
				}	
			}	
			
		}
		
		private void irFormulario(HttpServletRequest request) {
			
			vista = VIEW_FORM; 
			Multa m = new Multa();
			
			int identificador = Integer.parseInt(id);
			if ( identificador > 0 ) {			
				m = daoMulta.getById(identificador);
			}
			//request.setAttribute("video", m);
			request.setAttribute("multa", daoMulta.getMulta() );
			
					
		}
		private void getParametros(HttpServletRequest request) {

			op = request.getParameter("op");
			if( op == null ) {
				op = OP_LISTAR;
			} 
			
			
			id = request.getParameter("id");
			 importe = request.getParameter("importe");
			 concepto = request.getParameter("concepto");
			 fecha = request.getParameter("fecha");
			 
			 matricula = request.getParameter("matricula");
		

		}

	}

