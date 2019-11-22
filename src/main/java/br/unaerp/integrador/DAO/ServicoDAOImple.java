package br.unaerp.integrador.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.unaerp.integrador.models.Servico;

public class ServicoDAOImple implements ServicoDAO {
	private ConnectionDAO connection;
	private Statement state;

	public ServicoDAOImple() {
		this.connection = new ConnectionDAO();

		try {
			this.connection.connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void insert(Servico servico) throws SQLException {

		String stringIserte = "INSERT INTO servico (nome , valor) VALUES (?,?)";

		PreparedStatement insert = this.connection.db().prepareStatement(stringIserte);
		insert.setString(1, servico.getNome());
		insert.setDouble(2, servico.getValor());

		insert.executeUpdate();

		this.connection.disconnect();

	}

	public void delete(Servico servico) throws SQLException {
		String query = "DELETE from servico where id ='" + servico.getId() + "'";

		PreparedStatement insert = this.connection.db().prepareStatement(query);

		insert.executeUpdate();

		this.connection.disconnect();
	}

	public void update(Servico servico) throws SQLException {

		String query = "UPDATE servico SET nome = ? , valor = ? where id = '" + servico.getId() + "'";

		PreparedStatement insert = this.connection.db().prepareStatement(query);

		insert.setString(1, servico.getNome());
		insert.setDouble(2, servico.getValor());

		insert.executeUpdate();

		this.connection.disconnect();

	}

	public List<Servico> list() {
		List<Servico> pessoaList = new ArrayList<Servico>();

		String stringConsulta = "SELECT * FROM servico";

		try {
			this.state = this.connection.db().createStatement();
			ResultSet resultado = state.executeQuery(stringConsulta.toUpperCase());

			while (resultado.next()) {
				int idPessoa = resultado.getInt("id");
				String nomePessoa = resultado.getString("nome");
				double valorServico = resultado.getDouble("valor");

				Servico novoServico = new Servico();

				novoServico.setValor(valorServico);
				novoServico.setNome(nomePessoa);
				novoServico.setId(idPessoa);

				pessoaList.add(novoServico);
			}

			this.connection.disconnect();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}

		return pessoaList;
	}

	public Servico getById(int id) {
		Servico servico = null;
		String stringConsulta = "SELECT * FROM servico where id='" + id + "'";

		try {
			this.state = this.connection.db().createStatement();
			ResultSet resultado = state.executeQuery(stringConsulta.toUpperCase());

			while (resultado.next()) {
				int idPessoa = resultado.getInt("id");
				String nomePessoa = resultado.getString("nome");
				double valorServico = resultado.getDouble("valor");

				servico = new Servico();

				servico.setValor(valorServico);
				servico.setNome(nomePessoa);
				servico.setId(idPessoa);

			}

			this.connection.disconnect();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}

		return servico;
	}
}
