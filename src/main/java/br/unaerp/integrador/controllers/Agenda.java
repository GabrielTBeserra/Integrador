package br.unaerp.integrador.controllers;

import java.util.Date;
import java.util.Objects;

import br.unaerp.integrador.models.Pessoa;
import br.unaerp.integrador.models.Servico;

public class Agenda {
	private Long id;
	private String titulo;
	private Date dataInicio;
	private Date dataFim;
	private Pessoa pessoa;
	private Servico servico;
	private boolean diaInteiro;

	public Agenda() {
		this.titulo = "";
		this.diaInteiro = false;
	}

	public Agenda(Long id, String titulo, Date dataInicio, Date dataFim, boolean diaInteiro, Pessoa pessoa,
			Servico servico) {
		this.id = id;
		this.titulo = titulo;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.diaInteiro = diaInteiro;
		this.pessoa = pessoa;
		this.servico = servico;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public boolean isDiaInteiro() {
		return diaInteiro;
	}

	public void setDiaInteiro(boolean diaInteiro) {
		this.diaInteiro = diaInteiro;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 29 * hash + Objects.hashCode(this.id);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Agenda other = (Agenda) obj;
		if (!Objects.equals(this.id, other.id)) {
			return false;
		}
		return true;
	}
}