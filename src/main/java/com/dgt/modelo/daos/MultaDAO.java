package com.dgt.modelo.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dgt.modelo.pojos.Agente;
import com.dgt.modelo.pojos.Coche;
import com.dgt.modelo.pojos.Multa;




public class MultaDAO {
	
	private static MultaDAO INSTANCE = null;
	
	private static final String SQL_GETBYIDMULTA = "SELECT m.id AS 'id_multa', a.id AS 'id_agente', c.id AS 'id_coche', importe, concepto, fecha, a.nombre FROM dgt.multa AS m, dgt.agente AS a, dgt.coche AS c WHERE m.id_agente = a.id AND m.id_coche = c.id AND m.id = ?;";
	private static final String SQL_GETMULTA = "SELECT m.id , a.id AS 'id_agente', c.id AS 'id_coche', placa, matricula, importe, concepto, fecha_alta, fecha_modificacion, fecha_baja, a.nombre as 'agente' FROM dgt.multa AS m, dgt.agente AS a, dgt.coche AS c WHERE m.id_agente = a.id AND m.id_coche = c.id ORDER BY m.id DESC LIMIT 1000;";
	private static final String SQL_INSERTMULTA = "INSERT INTO multa (importe, concepto, id_agente, id_coche) VALUES( ?, ?, ?, ?);";
	public MultaDAO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public synchronized static MultaDAO getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new MultaDAO();
		}
		return INSTANCE;
	}
	
	public Multa getById(long id) {

		Multa m = null;

		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(SQL_GETBYIDMULTA);) {
			pst.setLong(1, id);

			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					m = rowMapper(rs);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return m;
	}
	
	public ArrayList<Multa> getMulta(){
		
		ArrayList<Multa> multas = new ArrayList<Multa>();
		
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(SQL_GETMULTA);
				ResultSet rs = pst.executeQuery()){
			
			while (rs.next()) {
				try {
					multas.add(rowMapper(rs));
				} catch (Exception e) {
					System.out.println("multa no válida");
					e.printStackTrace();
				}
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return multas;
	}
	
	public boolean insert(Multa m) throws SQLException {

		boolean resul = false;

		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(SQL_INSERTMULTA);) {

			pst.setFloat(1, m.getImporte());
			pst.setString(2, m.getConcepto());
			pst.setLong(3, m.getAgente().getId());
			pst.setLong(4, m.getCoche().getId());
			int affectedRows = pst.executeUpdate();
			if (affectedRows == 1) {
				resul = true;
			}

		}
		return resul;
	}
	

	private Multa rowMapper(ResultSet rs) throws SQLException {
		
		Multa m = new Multa();
		m.setId(rs.getLong("id"));
		m.setImporte(rs.getFloat("importe"));
		m.setConcepto(rs.getString("concepto"));
		m.setFecha(rs.getDate("fecha"));
		
Agente a = new Agente();
a.setId(rs.getLong("id_agente"));
	a.setNombre(rs.getString("agente"));
	a.setPlaca(rs.getString("placa"));
	
	m.setAgente(a);
	
	Coche c = new Coche();
	c.setId(rs.getLong("id_coche"));
	c.setMatricula(rs.getString("matricula"));
	
	m.setCoche(c);
		
		
		
		return m;
		
	}

}
