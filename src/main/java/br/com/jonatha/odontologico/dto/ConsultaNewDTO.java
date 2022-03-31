package br.com.jonatha.odontologico.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.FutureOrPresent;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ConsultaNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@FutureOrPresent(message = "Insira uma data Válida")
	private Date dataConsulta;
	
	@JsonFormat(pattern = "HH:mm")
	private Date horarioConsulta;
	
	private String procedimento;
	
	public ConsultaNewDTO() {
		
	}

	public Date getDataConsulta() {
		return dataConsulta;
	}

	public void setDataConsulta(Date dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

	public Date getHorarioConsulta() {
		return horarioConsulta;
	}

	public void setHorarioConsulta(Date horarioConsulta) {
		this.horarioConsulta = horarioConsulta;
	}

	public String getProcedimento() {
		return procedimento;
	}

	public void setProcedimento(String procedimento) {
		this.procedimento = procedimento;
	}
	
}
