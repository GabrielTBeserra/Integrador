package br.unaerp.integrador.controllers;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.unaerp.integrador.DAO.AuthDAO;
import br.unaerp.integrador.STATICS.AlertMessages;
import br.unaerp.integrador.auth.SessionContext;
import br.unaerp.integrador.models.Admin;

@ManagedBean(name = "auth")
@ViewScoped
public class Auth {
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
		AuthDAO auth = new AuthDAO();
		
		Admin admin = auth.isAdmin(this.user, this.password);
		
		
		if(admin != null) {
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
