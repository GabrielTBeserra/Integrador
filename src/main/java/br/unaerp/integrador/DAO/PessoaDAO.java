package br.unaerp.integrador.DAO;

import java.sql.SQLException;
import java.util.List;

import br.unaerp.integrador.models.Pessoa;

public interface PessoaDAO {
	public abstract void insert(Pessoa pessoa) throws SQLException;

	public abstract void delete(Pessoa pessoa) throws SQLException;

	public abstract void update(Pessoa pessoa) throws SQLException;

	public abstract List<Pessoa> list();

	public abstract Pessoa getById(int id);

	public abstract Pessoa getByCpf(String cpf);
}
