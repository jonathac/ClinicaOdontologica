package br.com.jonatha.odontologico.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ClienteNewDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
	private String nome;
	
	
	@Past(message = "Insira uma data válida")
	@JsonFormat	(pattern = "dd/MM/yyyy")
	private Date dataNascimento;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Email(message = "Email inválido")
	private String email;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 6, max = 8, message = "O tamanho deve ser entre 6 e 8 caracteres")
	private String senha;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@CPF
	private String cpf;
	
	private boolean planoSaude;
	private String nomePlanoSaude;
	private String numeroPlanoSaude;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 3, max = 10, message = "O tamanho deve ser entre 5 e 10 caracteres")
	@Pattern(regexp = "^[0-9]*$", message = "Apenas números")
	private String cep; // consumir cep por api
/*
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
	private String logradouro;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String numero;

	private String complemento;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
	private String bairro;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
	private String cidade;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String estado;
	*/

	@NotEmpty(message="Preenchimento obrigatório")
	private String telefone1;
	private String telefone2;
	private String telefone3;
	
	public ClienteNewDTO() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public boolean isPlanoSaude() {
		return planoSaude;
	}

	public void setPlanoSaude(boolean planoSaude) {
		this.planoSaude = planoSaude;
	}

	public String getNomePlanoSaude() {
		return nomePlanoSaude;
	}

	public void setNomePlanoSaude(String nomePlanoSaude) {
		this.nomePlanoSaude = nomePlanoSaude;
	}

	public String getNumeroPlanoSaude() {
		return numeroPlanoSaude;
	}

	public void setNumeroPlanoSaude(String numeroPlanoSaude) {
		this.numeroPlanoSaude = numeroPlanoSaude;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getTelefone3() {
		return telefone3;
	}

	public void setTelefone3(String telefone3) {
		this.telefone3 = telefone3;
	}
}
