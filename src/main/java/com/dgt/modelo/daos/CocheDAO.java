package com.dgt.modelo.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dgt.modelo.pojos.Coche;

public class CocheDAO {

	private static CocheDAO INSTANCE = null;
	@SuppressWarnings("unused")
	private String indetmatricula = "";

	private static final String SQL_GETMATRICULAS = "SELECT id, matricula, modelo, km FROM coche ORDER BY id DESC LIMIT 100;";
	//private static final String SQL_GETMATRICULA = "SELECT id, matricula, modelo, km FROM coche WHERE matricula= ? ;";
	private static final String SQL_GETMATRICULA = "{call pa_coche_getByMatricula(?)}";
	// constructor privado, solo acceso por getInstance()
	private CocheDAO() {
		super();
	}

	public synchronized static CocheDAO getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new CocheDAO();
		}
		return INSTANCE;
	}

	private Coche rowMapper(ResultSet rs) throws SQLException {
		Coche c = new Coche();
		c.setId(rs.getLong("id"));
		c.setMatricula(rs.getString("matricula"));
		c.setModelo(rs.getString("modelo"));
		c.setKm(rs.getString("km"));
		return c;
	}
	
	public Coche getByMatri(String mat) {
		Coche c = null;
		String sql = SQL_GETMATRICULA;
		try (Connection conn = ConnectionManager.getConnection(); 
				CallableStatement cs  = conn.prepareCall(sql);) {

			cs.setString(1, mat); //donde el parametro matricula sea definido por lo que recoge la variable 'mat'

			try (ResultSet rs = cs.executeQuery()) {

				while (rs.next()) {
					c = rowMapper(rs);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return c; //Debug dice null
	}
	
	public ArrayList<Coche> getMatriculas() {
		ArrayList<Coche> matriculas = new ArrayList<Coche>();
		indetmatricula = "getMatriculas";
		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(SQL_GETMATRICULAS);) {

			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					try {
						matriculas.add(rowMapper(rs));
					} catch (Exception e) {
						System.out.println("coche no valido");
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return matriculas;
	}
}