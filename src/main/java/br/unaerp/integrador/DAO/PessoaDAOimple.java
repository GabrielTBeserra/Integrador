package br.unaerp.integrador.DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.unaerp.integrador.models.Pessoa;

public class PessoaDAOimple implements PessoaDAO {
	private ConnectionDAO connection;
	private Statement state;

	public PessoaDAOimple() {
		this.connection = new ConnectionDAO();

		try {
			this.connection.connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insert(Pessoa pessoa) throws SQLException {
		java.sql.Date dataConvertida = new java.sql.Date(pessoa.getDtnNascimento().getTime());

		String stringIserte = "INSERT INTO pessoa (nome , cpf , nascimento , telefone , endereco) VALUES (?,?,?,?,?)";

		PreparedStatement insert = this.connection.db().prepareStatement(stringIserte);
		insert.setString(1, pessoa.getNome());
		insert.setString(2, removerMascara(pessoa.getCpf()));
		insert.setDate(3, dataConvertida);
		insert.setString(4, removerMascara(pessoa.getTelefone()));
		insert.setString(5, pessoa.getEndereco());
		insert.executeUpdate();

		this.connection.disconnect();

	}

	public void delete(Pessoa pessoa) throws SQLException {
		String query = "DELETE from pessoa where id ='" + pessoa.getId() + "'";

		PreparedStatement insert = this.connection.db().prepareStatement(query);

		insert.executeUpdate();

		this.connection.disconnect();
	}

	public void update(Pessoa pessoa) throws SQLException {
		java.sql.Date dataConvertida = new java.sql.Date(pessoa.getDtnNascimento().getTime());
		String query = "UPDATE pessoa SET nome = ? , cpf = ? , nascimento = ? , telefone = ? , endereco = ? where id = '"
				+ pessoa.getId() + "'";

		PreparedStatement insert = this.connection.db().prepareStatement(query);

		insert.setString(1, pessoa.getNome());
		insert.setString(2, removerMascara(pessoa.getCpf()));
		insert.setDate(3, dataConvertida);
		insert.setString(4, removerMascara(pessoa.getTelefone()));
		insert.setString(5, pessoa.getEndereco());

		insert.executeUpdate();

		this.connection.disconnect();

	}

	public List<Pessoa> list() {
		List<Pessoa> pessoaList = new ArrayList<Pessoa>();

		String stringConsulta = "SELECT * FROM pessoa";

		try {
			this.state = this.connection.db().createStatement();
			ResultSet resultado = state.executeQuery(stringConsulta.toUpperCase());

			while (resultado.next()) {
				int idPessoa = resultado.getInt("id");
				String nomePessoa = resultado.getString("nome");
				String telefone = resultado.getString("telefone");
				String endereco = resultado.getString("endereco");
				String cpfPessoa = resultado.getString("cpf");
				Date dataDeNascimento = resultado.getDate("nascimento");

				Pessoa novaPessoa = new Pessoa();

				novaPessoa.setCpf(cpfPessoa);
				novaPessoa.setDtnNascimento(new java.util.Date(dataDeNascimento.getTime()));
				novaPessoa.setNome(nomePessoa);
				novaPessoa.setId(idPessoa);
				novaPessoa.setTelefone(telefone);
				novaPessoa.setEndereco(endereco);

				pessoaList.add(novaPessoa);
			}

			this.connection.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pessoaList;
	}

	public Pessoa getByCpf(String cpf) {
		Pessoa pessoa = null;
		String stringConsulta = "SELECT * FROM pessoa where cpf='" + cpf + "'";

		try {
			this.state = this.connection.db().createStatement();
			ResultSet resultado = state.executeQuery(stringConsulta.toUpperCase());

			while (resultado.next()) {
				int idPessoa = resultado.getInt("id");
				String nomePessoa = resultado.getString("nome");
				String cpfPessoa = resultado.getString("cpf");
				Date dataDeNascimento = resultado.getDate("nascimento");

				pessoa = new Pessoa();

				pessoa.setCpf(cpfPessoa);
				pessoa.setDtnNascimento(new java.util.Date(dataDeNascimento.getTime()));
				pessoa.setNome(nomePessoa);
				pessoa.setId(idPessoa);

			}

			this.connection.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pessoa;
	}

	public Pessoa getById(int id) {
		Pessoa pessoa = null;
		String stringConsulta = "SELECT * FROM pessoa where id='" + id + "'";

		try {
			this.state = this.connection.db().createStatement();
			ResultSet resultado = state.executeQuery(stringConsulta.toUpperCase());

			while (resultado.next()) {
				int idPessoa = resultado.getInt("id");
				String nomePessoa = resultado.getString("nome");
				String cpfPessoa = resultado.getString("cpf");
				Date dataDeNascimento = resultado.getDate("nascimento");

				pessoa = new Pessoa();

				pessoa.setCpf(cpfPessoa);
				pessoa.setDtnNascimento(new java.util.Date(dataDeNascimento.getTime()));
				pessoa.setNome(nomePessoa);
				pessoa.setId(idPessoa);

			}

			this.connection.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pessoa;
	}

	private String removerMascara(String str) {
		try {
			return str.replaceAll("\\D", "");
		} catch (Exception e) {
			return str;
		}
	}
}
