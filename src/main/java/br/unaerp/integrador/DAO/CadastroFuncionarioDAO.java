package br.unaerp.integrador.DAO;

import java.sql.SQLException;
import java.util.List;

import br.unaerp.integrador.models.Admin;

public interface CadastroFuncionarioDAO {
	public abstract void insert(Admin admin) throws SQLException;

	public abstract void update(Admin admin) throws SQLException;

	public abstract void delete(Admin admin) throws SQLException;

	public abstract Admin auth(String user, String senha);

	public abstract List<Admin> list();
}
