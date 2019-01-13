package com.dgt.modelo.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dgt.modelo.pojos.Agente;


public class AgenteDAO {
	
	private static AgenteDAO INSTANCE = null;
	
	private static final String SQL_GETBYIDAGENTE = "SELECT id, nombre FROM dgt.agente WHERE id = ?;";

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
				PreparedStatement pst = conn.prepareStatement(SQL_GETBYIDAGENTE);) {
			pst.setLong(1, a.getId()); //Donde el parametro 1 sea definido por el obtenido por getId

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
		
		return a;
	}
	
}
