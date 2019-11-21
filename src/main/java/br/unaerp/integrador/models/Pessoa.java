package br.unaerp.integrador.models;

import java.util.Date;

public class Pessoa {
	private String nome;
	private String cpf;
	private String telefone;
	private String endereco;
	private Date dtnNascimento;
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDtnNascimento() {
		return dtnNascimento;
	}

	public void setDtnNascimento(Date dtnNascimento) {
		this.dtnNascimento = dtnNascimento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
}
