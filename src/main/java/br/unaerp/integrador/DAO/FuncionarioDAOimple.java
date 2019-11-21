package br.unaerp.integrador.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.unaerp.integrador.models.Admin;

public class FuncionarioDAOimple implements CadastroFuncionarioDAO {
	private ConnectionDAO connection;
	private Statement state;

	public FuncionarioDAOimple() {
		this.connection = new ConnectionDAO();

		try {
			this.connection.connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insert(Admin admin) throws SQLException {
		String stringIserte = "INSERT INTO funcionario (nomeFuncionario , usuario , senha , nivelPermissao) VALUES (?,?,?,?)";

		PreparedStatement insert = this.connection.db().prepareStatement(stringIserte);
		insert.setString(1, admin.getNome());
		insert.setString(2, admin.getUsuario());
		insert.setString(3, admin.getSenha());
		insert.setInt(4, admin.getNivel());
		insert.executeUpdate();

		this.connection.disconnect();
	}

	public void update(Admin admin) throws SQLException {
		String query = "UPDATE funcionario SET nomeFuncionario = ? , usuario = ? , senha = ? , nivelPermissao = ? , endereco = ? where id = '"
				+ admin.getId() + "'";

		PreparedStatement insert = this.connection.db().prepareStatement(query);

		insert.setString(1, admin.getNome());
		insert.setString(2, admin.getUsuario());
		insert.setString(3, admin.getSenha());
		insert.setInt(4, admin.getNivel());

		insert.executeUpdate();

		this.connection.disconnect();
	}

	public void delete(Admin admin) throws SQLException {
		String query = "DELETE from funcionario where id ='" + admin.getId() + "'";

		PreparedStatement insert = this.connection.db().prepareStatement(query);

		insert.executeUpdate();

		this.connection.disconnect();
	}

	public List<Admin> list() {
		List<Admin> adminList = new ArrayList<Admin>();

		String stringConsulta = "SELECT * FROM funcionario";

		try {
			this.state = this.connection.db().createStatement();
			ResultSet resultado = state.executeQuery(stringConsulta.toUpperCase());

			while (resultado.next()) {
				int idFunc = resultado.getInt("idFuncionario");
				String nomeFunc = resultado.getString("nomeFuncionario");
				String usuario = resultado.getString("usuario");
				int nivel = resultado.getInt("nivelPermissao");

				Admin admin = new Admin();
				admin.setId(idFunc);
				admin.setNivel(nivel);
				admin.setNome(nomeFunc);
				admin.setUsuario(usuario);

				adminList.add(admin);

			}

			this.connection.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return adminList;
	}

	public Admin auth(String user, String senha) {
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
