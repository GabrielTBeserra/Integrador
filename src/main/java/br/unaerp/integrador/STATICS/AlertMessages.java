package br.unaerp.integrador.STATICS;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class AlertMessages {
	public static final void sucesso_cadastro() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Cadastro realizado com sucesso", "Cadastro relizado com sucesso!"));
	}

	public static final void sucesso_atualizacao() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Cadastro atualizado com sucesso", "Cadastro atualizado com sucesso!"));
	}

	public static final void erro_cadastro_existente() {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cadastro já existente", "Cadastro já existente"));
	}

	public static final void erro_cpf_invalido() {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "CPF inválido", "CPF inválido!"));
	}

	public static final void erro_nome_pequeno() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
				"Nome muito pequeno ou está em branco", "Insira um nome válido!"));
	}

	public static final void error() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
				"Ocorreu algum erro! Tente novamente mais tarde!", "Ocorreu algum erro! Tente novamente mais tarde!"));
	}

	public static final void sucesso_cadastro_servico() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Servico adicionado com sucesso!", "Servico adicionado com sucesso!"));
	}
	
	public static final void SQL_VALOR_DEPENDENTE() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
				"Existe um agendamento com esse serviço!", "Existe um agendamento com esse serviço!"));
	}
	
	public static final void SQL_VALOR_DEPENDENTE_PESSOA() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
				"Esta pessoa esta agendada!", "Esta pessoa esta agendada!"));
	}
	public static final void USUARIO_INCORRETO() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
				"USUARIO OU SENHA INCORRETOS!", "USUARIO OU SENHA INCORRETOS!"));
	}
	
}
