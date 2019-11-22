package br.unaerp.integrador.controllers;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.time.DateUtils;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import br.unaerp.integrador.DAO.AgendamentoDAOImple;
import br.unaerp.integrador.DAO.PessoaDAOimple;
import br.unaerp.integrador.DAO.ServicoDAOImple;
import br.unaerp.integrador.models.Agenda;
import br.unaerp.integrador.models.EventCustom;

@ViewScoped
@ManagedBean(name = "scheduleBean")

public class GerenciadorCalendario implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4301507958238479375L;
	private ScheduleModel model;
	private Agenda evento;
	private ScheduleEvent event;
	private List<ScheduleEvent> scheduleEvents;
	private String valorPessoa;
	private String valorServico;
	private String serviceName;

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getValorServico() {
		return valorServico;
	}

	public void setValorServico(String valorServico) {
		ServicoDAOImple get = new ServicoDAOImple();
		this.evento.setServico(get.getById(Integer.parseInt(valorServico)));
		this.valorServico = valorServico;
	}

	public String getValorPessoa() {
		return valorPessoa;
	}

	public void setValorPessoa(String valorPessoa) {
		PessoaDAOimple get = new PessoaDAOimple();
		this.evento.setPessoa(get.getByCpf(valorPessoa));

		this.valorPessoa = valorPessoa;
	}

	public ScheduleModel getModel() {
		return model;
	}

	public void setModel(ScheduleModel model) {
		this.model = model;
	}

	public ScheduleEvent getEvent() {
		return event;
	}

	public void setEvent(ScheduleEvent event) {
		this.event = event;
	}

	public List<ScheduleEvent> getScheduleEvents() {
		return scheduleEvents;
	}

	public void setScheduleEvents(List<ScheduleEvent> scheduleEvents) {
		this.scheduleEvents = scheduleEvents;
	}

	public GerenciadorCalendario() {
		event = new EventCustom();
		model = new DefaultScheduleModel();
		evento = new Agenda();
	}

	@PostConstruct
	public void init() {
		AgendamentoDAOImple eventos = new AgendamentoDAOImple();
		List<Agenda> evnt = eventos.list();

		if (this.model != null) {

			if (this.scheduleEvents == null) {
				this.scheduleEvents = new ArrayList<ScheduleEvent>();
			}
			for (Agenda eventoAtual : evnt) {
				ScheduleEvent newEvent = new EventCustom(eventoAtual.getPessoa().getNome(), eventoAtual.getDataInicio(),
						eventoAtual.getDataFim(), eventoAtual.isDiaInteiro(), eventoAtual.getPessoa(),
						eventoAtual.getServico(), eventoAtual);
				if (!this.scheduleEvents.contains(newEvent)) {
					newEvent.setId(eventoAtual.getId().toString());
					this.scheduleEvents.add(newEvent);
					this.model.addEvent(newEvent);
				}
			}
		}
	}

	public Agenda getEvento() {
		return evento;
	}

	public void setEvento(Agenda evento) {
		this.evento = evento;
	}

	public void salvar() {
		System.out.println(this.evento.getPessoa().getNome());
		ScheduleEvent newEvent = new EventCustom(this.evento.getTitulo(), this.evento.getDataInicio(),
				this.evento.getDataFim(), this.evento.isDiaInteiro(), this.evento.getPessoa(), this.evento.getServico(),
				this.evento);

		if (evento.getId() == null) {
			model.addEvent(newEvent);
		} else {
			newEvent.setId(event.getId());
			model.updateEvent(newEvent);
		}
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Evento Salvo", "Evento Salvo");
		addMessage(message);

		Agenda evento = new Agenda();
		evento.setDataFim(this.evento.getDataFim());
		evento.setDataInicio(this.evento.getDataInicio());
		evento.setPessoa(this.evento.getPessoa());
		evento.setTitulo(this.evento.getTitulo());
		evento.setServico(this.evento.getServico());

		AgendamentoDAOImple novo = new AgendamentoDAOImple();
		try {
			novo.insert(evento);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void atualizar() {
		model.clear();
		Agenda atualizarEvento = new Agenda();
		atualizarEvento.setDataFim(this.evento.getDataFim());
		atualizarEvento.setDataInicio(this.evento.getDataInicio());
		atualizarEvento.setId(this.evento.getId());
		atualizarEvento.setPessoa(this.evento.getPessoa());
		atualizarEvento.setServico(this.evento.getServico());
		atualizarEvento.setTitulo(this.evento.getTitulo());

		AgendamentoDAOImple update = new AgendamentoDAOImple();

		try {
			update.update(atualizarEvento);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		init();
	}

	public void remover() {
		model.deleteEvent(event);
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Evento Removido", "Evento Removido");
		addMessage(message);
		AgendamentoDAOImple delete = new AgendamentoDAOImple();
		try {
			delete.delete(this.evento);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onDateSelect(SelectEvent selectEvent) {
		this.evento = new Agenda();
		Date dataSelecionada = (Date) selectEvent.getObject();

		this.evento.setDataInicio(dataSelecionada);
		this.evento.setDataFim(DateUtils.addHours(dataSelecionada, 1));

	}

	public void onEventSelect(SelectEvent selectEvent) {

		event = (EventCustom) selectEvent.getObject();
		this.evento = (Agenda) event.getData();

		this.evento.setTitulo(this.evento.getPessoa().getNome());
	}

	public void onEventResize(ScheduleEntryResizeEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Evento Redimensionado",
				"Dia:" + event.getDayDelta() + ", Horário:" + event.getMinuteDelta());
		addMessage(message);
	}

	private void addMessage(FacesMessage message) {
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}