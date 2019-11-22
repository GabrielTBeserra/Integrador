package br.unaerp.integrador.controllers;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.unaerp.integrador.auth.SessionContext;


@ManagedBean(name = "logout")
@ViewScoped
public class Logout implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -4571933881837628799L;

	public void logout() {
		SessionContext.getInstance().encerrarSessao();
		
	}
		
}
