package com.dgt.modelo.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dgt.modelo.pojos.Agente;


public class AgenteDAO {
	
	private static AgenteDAO INSTANCE = null;
	
	private static final String SQL_GETBYIDAGENTE = "SELECT nombre FROM dgt.agente WHERE id = 4;";

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

	public Agente getById(long id) {

		Agente a = null;

		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(SQL_GETBYIDAGENTE);) {
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
		a.setId(rs.getLong("id_agente"));
		a.setNombre(rs.getString("nombre"));
		
		return a;
	}
	
}
