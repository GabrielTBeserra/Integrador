package br.unaerp.integrador.controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.unaerp.integrador.auth.SessionContext;


@ManagedBean(name = "logout")
@ViewScoped
public class Logout {
	public void logout() {
		SessionContext.getInstance().encerrarSessao();
		
	}
		
}
