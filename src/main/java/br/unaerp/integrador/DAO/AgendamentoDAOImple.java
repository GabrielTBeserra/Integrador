package br.unaerp.integrador.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import br.unaerp.integrador.controllers.Agenda;

public class AgendamentoDAOImple implements AgendamentoDAO {

	private ConnectionDAO connection;
	private Statement state;

	public AgendamentoDAOImple() {
		this.connection = new ConnectionDAO();

		try {
			this.connection.connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insert(Agenda evento) throws SQLException {
		String stringIserte = "INSERT INTO agendamentos (IDServico , IDCliente , horarioInicio , horarioFinal) VALUES (?,?,?,?)";

		PreparedStatement insert = this.connection.db().prepareStatement(stringIserte);
		insert.setInt(1, evento.getServico().getId());
		insert.setInt(2, evento.getPessoa().getId());
		insert.setTimestamp(3, new Timestamp(evento.getDataInicio().getTime()));
		insert.setTimestamp(4, new Timestamp(evento.getDataFim().getTime()));
		insert.executeUpdate();

		this.connection.disconnect();

	}

	public void delete(Agenda evento) throws SQLException {
		String query = "DELETE FROM agendamentos where IDAgendamento = '" + evento.getId() + "'";

		try {
			PreparedStatement insert = this.connection.db().prepareStatement(query);
			insert.executeUpdate();

		} catch (SQLException error) {
			error.printStackTrace();
		}

		this.connection.disconnect();
	}

	public void update(Agenda evento) throws SQLException {

		String query = "UPDATE agendamentos SET horarioInicio = ? , horarioFinal = ? , IDServico = ? where IDAgendamento = '"
				+ evento.getId() + "'";

		PreparedStatement insert = this.connection.db().prepareStatement(query);

		insert.setTimestamp(1, new Timestamp(evento.getDataInicio().getTime()));
		insert.setTimestamp(2, new Timestamp(evento.getDataFim().getTime()));
		insert.setInt(3, evento.getServico().getId());

		insert.executeUpdate();

		this.connection.disconnect();

	}

	public List<Agenda> list() {
		List<Agenda> eventoList = new ArrayList<Agenda>();

		String stringConsulta = "SELECT * FROM agendamentos";

		try {
			this.state = this.connection.db().createStatement();
			ResultSet resultado = state.executeQuery(stringConsulta.toUpperCase());

			while (resultado.next()) {
				int IDAgendamento = resultado.getInt("IDAgendamento");
				int idServico = resultado.getInt("IDServico");
				int idPessoa = resultado.getInt("IDCliente");
				Timestamp dataInicio = resultado.getTimestamp("horarioInicio");
				Timestamp dataFinal = resultado.getTimestamp("horarioFinal");

				PessoaDAOimple getPessoa = new PessoaDAOimple();
				ServicoDAOImple getServico = new ServicoDAOImple();
				Agenda novoEvento = new Agenda();
				novoEvento.setDataFim(new java.util.Date(dataFinal.getTime()));
				novoEvento.setDataInicio(new java.util.Date(dataInicio.getTime()));
				novoEvento.setPessoa(getPessoa.getById(idPessoa));
				novoEvento.setServico(getServico.getById(idServico));
				novoEvento.setId(Long.valueOf(IDAgendamento));

				eventoList.add(novoEvento);
			}

			this.connection.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return eventoList;
	}

}
