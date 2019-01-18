package com.dgt.modelo.daos;

import java.sql.CallableStatement;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;

import com.dgt.modelo.pojos.Agente;
import com.dgt.modelo.pojos.Coche;
import com.dgt.modelo.pojos.Multa;

public class MultaDAO {

	private static MultaDAO INSTANCE = null;

	private static final String SQL_GETBYIDMULTA = "SELECT m.id AS 'id_multa', a.id AS 'id_agente', c.id AS 'id_coche', importe, concepto, km, modelo, fecha_alta, fecha_modificacion, fecha_baja, a.nombre FROM dgt.multa AS m, dgt.agente AS a, dgt.coche AS c WHERE m.id_agente = a.id AND m.id_coche = c.id AND m.id = ?;";

	// private static final String SQL_GETMULTA = "SELECT m.id , a.id AS
	// 'id_agente', c.id AS 'id_coche', placa, matricula, importe, concepto, km,
	// modelo, fecha_alta, fecha_modificacion, fecha_baja, a.nombre as 'agente' FROM
	// dgt.multa AS m, dgt.agente AS a, dgt.coche AS c WHERE m.id_agente = a.id AND
	// m.id_coche = c.id AND fecha_baja IS NULL ORDER BY m.id DESC LIMIT 1000;";
	private static final String SQL_GETMULTA = "{call pa_multa_getAll(?)}";

	// private static final String SQL_GETMULTA_ANULADA = "SELECT m.id , a.id AS
	// 'id_agente', c.id AS 'id_coche', placa, matricula, importe, concepto, km,
	// modelo, fecha_alta, fecha_modificacion, fecha_baja, a.nombre as 'agente' FROM
	// dgt.multa AS m, dgt.agente AS a, dgt.coche AS c WHERE m.id_agente = a.id AND
	// m.id_coche = c.id AND fecha_baja IS NOT NULL ORDER BY m.id DESC LIMIT 1000;";
	private static final String SQL_GETMULTA_ANULADA = "{call pa_multaAnulada_getAll(?)}";

	// private static final String SQL_INSERTMULTA = "INSERT INTO multa (importe,
	// concepto, id_agente, id_coche) VALUES( ?, ?, ?, ?);";
	private static final String SQL_INSERTMULTA = "{call pa_multa_insert(?,?,?,?,?)}";

	// private static final String SQL_UPDATE = "UPDATE multa SET fecha_baja =
	// CURRENT_TIMESTAMP WHERE id = ?;";
	private static final String SQL_UPDATE = "{call pa_multa_update(?)}";

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

	public ArrayList<Multa> getMulta(long id) {

		ArrayList<Multa> multas = new ArrayList<Multa>();

		try (Connection conn = ConnectionManager.getConnection();
				CallableStatement cs = conn.prepareCall(SQL_GETMULTA);) {
			cs.setLong(1, id);
			try (ResultSet rs = cs.executeQuery()) {

				while (rs.next()) {
					try {
						multas.add(rowMapper(rs));
					} catch (Exception e) {
						System.out.println("multa no válida");
						e.printStackTrace();
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return multas;
	}

	// Andoni
	public ArrayList<Multa> getMultaAnulada(long id) {

		ArrayList<Multa> multas = new ArrayList<Multa>();
		
		try (Connection conn = ConnectionManager.getConnection();
				CallableStatement cs = conn.prepareCall(SQL_GETMULTA_ANULADA);) {
			cs.setLong(1, id);
			try (ResultSet rs = cs.executeQuery()) {

			
			while (rs.next()) {
				try {
					multas.add(rowMapper(rs));
				} catch (Exception e) {
					System.out.println("multa no válida");
					e.printStackTrace();
				}
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return multas;
	}

	public boolean insert(Multa m) throws SQLException {

		boolean resul = false;

		try (Connection conn = ConnectionManager.getConnection();
				CallableStatement cs = conn.prepareCall(SQL_INSERTMULTA);) {

			cs.setFloat(1, m.getImporte());
			cs.setString(2, m.getConcepto());
			cs.setLong(3, m.getAgente().getId());
			cs.setLong(4, m.getCoche().getId());
			cs.registerOutParameter(5, Types.INTEGER);
			int affectedRows = cs.executeUpdate();
			if (affectedRows == 1) {
				resul = true;
			}

		}
		return resul;
	}

	public boolean update(Multa m) throws SQLException {

		boolean resul = false;
		try (Connection conn = ConnectionManager.getConnection();
				CallableStatement cs = conn.prepareCall(SQL_UPDATE);) {

			cs.setLong(1, m.getId());

			int affectedRows = cs.executeUpdate();
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

		// convertir fecha TimeStamp a java.util.Date

		m.setFecha_alta(rs.getTimestamp("fecha_alta"));

		// m.setFecha_alta(rs.getDate("fecha_alta"));
		m.setFecha_modificacion(rs.getTimestamp("fecha_modificacion"));

		// Timestamp timestampbaja = rs.getTimestamp("fecha_baja");
		// m.setFecha_baja(new java.util.Date(timestampbaja.getTime()));
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
