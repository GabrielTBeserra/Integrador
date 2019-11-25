package br.unaerp.integrador.controllers;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.unaerp.integrador.DAO.FuncionarioDAOimple;
import br.unaerp.integrador.STATICS.AlertMessages;
import br.unaerp.integrador.models.Admin;

@ManagedBean(name = "cadastrofunc")
@ViewScoped
public class CadastroFuncionario implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8320082510482534410L;
	private Admin admin;
	private List<Admin> listAdmin;
	private List<Admin> adminFilter;
	private Admin adminEdit;

	@PostConstruct
	public void init() {
		FuncionarioDAOimple listaAdmins = new FuncionarioDAOimple();
		this.listAdmin = listaAdmins.list();
	}

	public CadastroFuncionario() {
		this.admin = new Admin();
		this.adminEdit = new Admin();
	}

	public void submitForm() {
		FuncionarioDAOimple newFunc = new FuncionarioDAOimple();

		try {
			newFunc.insert(admin);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		init();
		clear_form();
	}

	public void atualizar() {
		FuncionarioDAOimple newFunc = new FuncionarioDAOimple();

		try {
			newFunc.update(adminEdit);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		init();
	}

	public void apagar() {
		FuncionarioDAOimple delete = new FuncionarioDAOimple();

		try {
			delete.delete(this.adminEdit);
		} catch (SQLException e) {
			System.out.println(e.getErrorCode());
			if (e.getErrorCode() == 1451) {
				AlertMessages.SQL_VALOR_DEPENDENTE();
			}
		}
		init();
	}

	public void edit(Admin admin) {
		this.adminEdit = admin;
	}

	private void clear_form() {

	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public List<Admin> getListAdmin() {
		return listAdmin;
	}

	public void setListAdmin(List<Admin> listAdmin) {
		this.listAdmin = listAdmin;
	}

	public List<Admin> getAdminFilter() {
		return adminFilter;
	}

	public void setAdminFilter(List<Admin> adminFilter) {
		this.adminFilter = adminFilter;
	}

	public Admin getAdminEdit() {
		return adminEdit;
	}

	public void setAdminEdit(Admin adminEdit) {
		this.adminEdit = adminEdit;
	}

}
