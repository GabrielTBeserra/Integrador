package br.unaerp.integrador.controllers;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.unaerp.integrador.DAO.ServicoDAOImple;
import br.unaerp.integrador.STATICS.AlertMessages;
import br.unaerp.integrador.models.Servico;

@ManagedBean(name = "servicos")
@ViewScoped
public class Servicos implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5854378465069525498L;
	/**
	 * 
	 */
	private List<Servico> listServico;
	private List<Servico> filterServicos;
	private Servico servico;
	private Servico servicoEdit;

	@PostConstruct
	public void init() {
		ServicoDAOImple servicos = new ServicoDAOImple();
		this.listServico = servicos.list();
	}

	public Servicos() {
		this.servicoEdit = new Servico();
		this.servico = new Servico();
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public void submitForm() {
		ServicoDAOImple novoServico = new ServicoDAOImple();

		try {
			novoServico.insert(servico);
			AlertMessages.sucesso_cadastro_servico();
		} catch (SQLException e) {
			System.out.println(e.getErrorCode());
		}

		clear_form();
		init();
	}

	public Servico getServicoEdit() {
		return servicoEdit;
	}

	public void setServicoEdit(Servico servicoEdit) {
		this.servicoEdit = servicoEdit;
	}


	public void edit(Servico servico) {
		this.servicoEdit = servico;
	}

	public List<Servico> getListServico() {
		return listServico;
	}

	public void setListServico(List<Servico> listServico) {
		this.listServico = listServico;
	}

	public List<Servico> getFilterServicos() {
		return filterServicos;
	}

	public void setFilterServicos(List<Servico> filterServicos) {
		this.filterServicos = filterServicos;
	}

	public void atualizar() {

		ServicoDAOImple up = new ServicoDAOImple();

		try {
			up.update(this.servicoEdit);
		} catch (SQLException e) {
			System.out.println(e.getErrorCode());
		}
		init();
	}

	public void apagar() {
		ServicoDAOImple delete = new ServicoDAOImple();

		try {
			delete.delete(this.servicoEdit);
		} catch (SQLException e) {
			System.out.println(e.getErrorCode());
			if(e.getErrorCode() == 1451) {
				AlertMessages.SQL_VALOR_DEPENDENTE();
			}
		}
		init();
	}

	private void clear_form() {
		servico.setNome(null);
		servico.setValor(0);
	}

}
