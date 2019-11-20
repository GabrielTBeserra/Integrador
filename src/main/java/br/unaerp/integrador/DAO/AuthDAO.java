package br.unaerp.integrador.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.unaerp.integrador.models.Admin;

public class AuthDAO {
	private ConnectionDAO connection;
	private Statement state;

	public AuthDAO() {
		this.connection = new ConnectionDAO();

		try {
			this.connection.connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Admin isAdmin(String user, String senha) {
		Admin admin = new Admin();
		String stringConsulta = "SELECT * FROM funcionario where usuario='" + user + "' AND senha = '" + senha + "'";

		try {
			this.state = this.connection.db().createStatement();
			ResultSet resultado = state.executeQuery(stringConsulta.toUpperCase());

			while (resultado.next()) {
				admin.setId(resultado.getInt("idFuncionario"));
				admin.setNivel(resultado.getInt("nivelPermissao"));
				admin.setNome(resultado.getString("nomeFuncionario"));
				admin.setUsuario(resultado.getString("usuario"));
			}

			this.connection.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return admin;
	}
}
