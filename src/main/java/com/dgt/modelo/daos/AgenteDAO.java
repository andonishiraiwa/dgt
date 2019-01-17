package com.dgt.modelo.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dgt.modelo.pojos.Agente;




public class AgenteDAO {
	
	private static AgenteDAO INSTANCE = null;
	private static final String SQL_GETBYPLACA = "SELECT placa, id FROM dgt.agente WHERE placa = ?;";
	
	//private static final String SQL_GETBYIDAGENTE = "SELECT id, nombre FROM dgt.agente WHERE id = ?;";
	private static final String SQL_GETBYIDAGENTE = "{call pa_agente_getById(?)}";
	
	public AgenteDAO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public synchronized static AgenteDAO getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new AgenteDAO();
		}
		return INSTANCE;
	}
	
	public Agente getByUser(Agente a) {
		try (Connection conn = ConnectionManager.getConnection();
				CallableStatement cs = conn.prepareCall(SQL_GETBYIDAGENTE);) {
			cs.setLong(1, a.getId()); //Donde el parametro 1 sea definido por el obtenido por getId

			try (ResultSet rs = cs.executeQuery()) {
				while (rs.next()) {
					a = rowMapper(rs);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;
	}

	
	
	
	
	public Agente getById(long id) {

		Agente a = null;
		String sql = SQL_GETBYIDAGENTE;
		try (Connection conn = ConnectionManager.getConnection(); PreparedStatement pst = conn.prepareStatement(sql);) {

			pst.setLong(1, id);

			try (ResultSet rs = pst.executeQuery()) {

				while (rs.next()) {
					a = rowMapper(rs);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;
	}

	private Agente rowMapper(ResultSet rs) throws SQLException {
		Agente a = new Agente();
		a.setId(rs.getLong("id"));
		a.setNombre(rs.getString("nombre"));
		a.setPlaca(rs.getString("placa"));
		
		return a;
	}

	public Agente login(String placa, String pass) {
		// TODO configurar funcion logueo
		Agente a = null;
		String sql = "SELECT id, placa, password FROM agente WHERE placa = ? AND password = ?;";

		try (Connection conn = ConnectionManager.getConnection(); PreparedStatement pst = conn.prepareStatement(sql);) {
			pst.setString(1, placa);
			pst.setString(2, pass);
			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) { // hemos encontrado al agente
					a = new Agente();
					a.setId(rs.getLong("id"));
					a.setPlaca(rs.getString("placa"));
					a.setPassword(rs.getString("password"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;
	}
	
}
