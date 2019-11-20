package br.unaerp.integrador.DAO;

import java.sql.SQLException;
import java.util.List;

import br.unaerp.integrador.controllers.Agenda;

public interface AgendamentoDAO {
	public abstract void insert(Agenda evento) throws SQLException;

	public abstract void delete(Agenda evento) throws SQLException;

	public abstract void update(Agenda evento) throws SQLException;

	public abstract List<Agenda> list();
}
