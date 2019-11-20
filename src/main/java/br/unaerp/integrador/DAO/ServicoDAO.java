package br.unaerp.integrador.DAO;

import java.sql.SQLException;
import java.util.List;

import br.unaerp.integrador.models.Servico;

public interface ServicoDAO {
	public abstract void insert(Servico servico) throws SQLException;

	public abstract void delete(Servico servico) throws SQLException;

	public abstract void update(Servico servico) throws SQLException;

	public abstract List<Servico> list();

	public abstract Servico getById(int id);
}
