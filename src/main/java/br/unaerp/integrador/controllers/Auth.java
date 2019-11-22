package br.unaerp.integrador.controllers;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.unaerp.integrador.DAO.FuncionarioDAOimple;
import br.unaerp.integrador.STATICS.AlertMessages;
import br.unaerp.integrador.auth.SessionContext;
import br.unaerp.integrador.models.Admin;

@ManagedBean(name = "auth")
@ViewScoped
public class Auth implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5540465277215160971L;
	private String user;
	private String password;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void login() {
		FuncionarioDAOimple auth = new FuncionarioDAOimple();

		Admin admin = auth.auth(this.user, this.password);

		if (admin != null) {
			SessionContext.getInstance().setAttribute("user", admin.getUsuario());
			SessionContext.getInstance().setAttribute("nivel", admin.getNivel());
			SessionContext.getInstance().setAttribute("nome", admin.getNome());
			SessionContext.getInstance().setAttribute("id", admin.getId());
			SessionContext.getInstance().setTempoDeSessao(10);

			String context = SessionContext.getInstance().getAttribute("context").toString();
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect(context + "/");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			AlertMessages.USUARIO_INCORRETO();
		}
	}

}
