package br.unaerp.integrador.controllers;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.unaerp.integrador.DAO.PessoaDAOimple;
import br.unaerp.integrador.STATICS.AlertMessages;
import br.unaerp.integrador.STATICS.ValidadorCPF;
import br.unaerp.integrador.models.Pessoa;

@ManagedBean(name = "cadastro")
@ViewScoped
public class Cadastro implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Pessoa> listPessoa;
	private List<Pessoa> pessoaFilter;
	private Pessoa pessoa;
	private Pessoa pessoaEdit;

	@PostConstruct
	public void init() {
		PessoaDAOimple listaPessoa = new PessoaDAOimple();
		this.listPessoa = listaPessoa.list();
	}

	public Cadastro() {
		this.pessoa = new Pessoa();
		this.pessoaEdit = new Pessoa();
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public void submitForm() {
		pessoa.setNome(pessoa.getNome().trim());

		if (!ValidadorCPF.isCPF(pessoa.getCpf())) {
			AlertMessages.erro_cpf_invalido();
			return;
		}

		if (pessoa.getNome().equals("")) {
			AlertMessages.erro_nome_pequeno();
			return;
		}

		try {
			PessoaDAOimple novaPessoa = new PessoaDAOimple();
			novaPessoa.insert(pessoa);
			AlertMessages.sucesso_cadastro();
		} catch (SQLException e) {
			AlertMessages.erro_cadastro_existente();
			e.printStackTrace();
		}

		init();
		clear_form();
	}

	public void atualizar() {
		pessoaEdit.setNome(pessoaEdit.getNome().trim());

		if (!ValidadorCPF.isCPF(pessoaEdit.getCpf())) {
			AlertMessages.erro_cpf_invalido();
			return;
		}

		if (pessoaEdit.getNome().equals("")) {
			AlertMessages.erro_nome_pequeno();
			return;
		}

		try {
			PessoaDAOimple up = new PessoaDAOimple();
			up.update(this.pessoaEdit);
		} catch (SQLException e) {
			System.out.println(e.getErrorCode());
			System.err.println(e.getMessage());
		}
		init();
	}

	public void apagar() {
		PessoaDAOimple delete = new PessoaDAOimple();

		try {
			delete.delete(this.pessoaEdit);
		} catch (SQLException e) {
			System.out.println(e.getErrorCode());
			if (e.getErrorCode() == 1451) {
				AlertMessages.SQL_VALOR_DEPENDENTE();
			}
		}
		init();
	}

	public void edit(Pessoa pessoa) {
		this.pessoaEdit = pessoa;
	}

	private void clear_form() {
		pessoa.setCpf(null);
		pessoa.setEndereco(null);
		pessoa.setDtnNascimento(null);
		pessoa.setTelefone(null);
		pessoa.setNome(null);
	}

	public Pessoa getPessoaEdit() {
		return pessoaEdit;
	}

	public void setPessoaEdit(Pessoa pessoaEdit) {
		this.pessoaEdit = pessoaEdit;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Pessoa> getListPessoa() {
		return listPessoa;
	}

	public void setListPessoa(List<Pessoa> listPessoa) {
		this.listPessoa = listPessoa;
	}

	public List<Pessoa> getPessoaFilter() {
		return pessoaFilter;
	}

	public void setPessoaFilter(List<Pessoa> pessoaFilter) {
		this.pessoaFilter = pessoaFilter;
	}

}
