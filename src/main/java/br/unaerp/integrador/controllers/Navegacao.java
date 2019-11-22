package br.unaerp.integrador.controllers;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.unaerp.integrador.auth.SessionContext;

@ViewScoped
@ManagedBean(name = "pages")
public class Navegacao implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 768635549877384988L;

	/**
	 * 
	 */
	

	@ManagedProperty(value = "#{servicos}")
	private String admin;

	private String page = "/pages/agendamentos.xhtml";

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public void cadastrar() {
		setPage("/pages/cadastrar.xhtml");
	}

	public void funcionario() {
		setPage("/pages/funcionario.xhtml");
	}
	
	public void agendamento() {
		setPage("/pages/agendamentos.xhtml");
	}

	public void servicos() {
		setPage("/pages/servicos.xhtml");
	}


	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = SessionContext.getInstance().getAttribute("nivel").toString();
	}

}
