package com.dgt.modelo.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dgt.modelo.pojos.Agente;
import com.dgt.modelo.pojos.Coche;
import com.dgt.modelo.pojos.Multa;


public class ObjetivoDAO {
	
	private static ObjetivoDAO INSTANCE = null;
	
	private static final String SQL_YEAR = "SELECT  a.id AS id_agente, COUNT(*), SUM(m.importe), YEAR(fecha_alta) AS `year`  FROM multa AS m, agente AS a WHERE a.id = m.id_agente AND fecha_baja IS NULL AND YEAR(fecha_alta) = ? AND a.id = ?;";
	private static final String SQL_MONTH = "SELECT  a.id AS id_agente, a.nombre AS agente, COUNT(*), SUM(m.importe), YEAR(fecha_alta) AS `year`, MONTH(fecha_alta) AS mes, nombre_mes(MONTH(fecha_alta)) AS nombre_mes FROM multa AS m, agente AS a WHERE a.id = m.id_agente AND fecha_baja IS NULL AND YEAR(fecha_alta) = ? AND MONTH(fecha_alta) = ? AND a.id = ?;";

	
	public synchronized static ObjetivoDAO getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new ObjetivoDAO();
		}
		return INSTANCE;
	}
	
	public ArrayList<Multa> getYear(){
		
		ArrayList<Multa> multas = new ArrayList<Multa>();
		
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(SQL_YEAR);
				ResultSet rs = pst.executeQuery()) {

			while (rs.next()) {
				try {
					multas.add(rowMapper(rs));
				} catch (Exception e) {
					System.out.println("");
					e.printStackTrace();
				}
			} // while

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return multas;
		
	}
		public ArrayList<Multa> getMonth(){
			
			ArrayList<Multa> multas = new ArrayList<Multa>();
			
			try (Connection conn = ConnectionManager.getConnection();
					PreparedStatement pst = conn.prepareStatement(SQL_MONTH);
					ResultSet rs = pst.executeQuery()) {

				while (rs.next()) {
					try {
						multas.add(rowMapper(rs));
					} catch (Exception e) {
						System.out.println("");
						e.printStackTrace();
					}
				} // while

			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return multas;	
	}

	private Multa rowMapper(ResultSet rs) throws SQLException {
		Multa m = new Multa();
		
		m.setId(rs.getLong("id"));
		m.setImporte(rs.getFloat("importe"));
		m.setConcepto(rs.getString("concepto"));
		m.setFecha_alta(rs.getTimestamp("fecha_alta"));
		m.setFecha_modificacion(rs.getTimestamp("fecha_modificacion"));
		m.setFecha_baja(rs.getTimestamp("fecha_baja"));

		Agente a = new Agente();
		a.setId(rs.getLong("id_agente"));
		a.setNombre(rs.getString("agente"));
		a.setPlaca(rs.getString("placa"));

		m.setAgente(a);

		Coche c = new Coche();
		c.setId(rs.getLong("id_coche"));
		c.setMatricula(rs.getString("matricula"));
		c.setModelo(rs.getString("modelo"));
		c.setKm(rs.getString("km"));

		m.setCoche(c);
		
		return m;
	}
	
	
}
