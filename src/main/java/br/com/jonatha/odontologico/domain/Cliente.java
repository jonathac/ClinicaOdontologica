package br.com.jonatha.odontologico.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.jonatha.odontologico.domain.enums.Perfil;

@Entity
public class Cliente extends Usuario {
	private static final long serialVersionUID = 1L;

	@Column(unique=true)
	private String cpf;
	private boolean planoSaude;
	private String nomePlanoSaude;
	private String numeroCarteirinha;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date data_nascimento;
	
	// Relacionamento entre Cliente e Endereço
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Endereco> endereco = new ArrayList<>();


	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Consulta> consulta = new ArrayList<>();
	
	// Coleção para armazenar telefones
	@ElementCollection
	@CollectionTable(name = "TELEFONE")
	private Set<String> telefones = new HashSet<>();

	// Construtor Vazio
	public Cliente() {
		addPerfil(Perfil.CLIENTE);
	}

	public Cliente(String nome, String email, String senha, String cpf, boolean planoSaude, String nomePlanoSaude, String numeroCarteirinha,
			Date data_nascimento) {
		super(nome, email, senha);
		this.cpf = cpf;
		this.planoSaude = planoSaude;
		this.nomePlanoSaude = nomePlanoSaude;
		this.numeroCarteirinha = numeroCarteirinha;
		this.data_nascimento = data_nascimento;
		addPerfil(Perfil.CLIENTE);
	}



	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getData_nascimento() {
		return data_nascimento;
	}

	public void setData_nascimento(Date data_nascimento) {
		this.data_nascimento = data_nascimento;
	}

	public List<Endereco> getEndereco() {
		return endereco;
	}

	public void setEndereco(List<Endereco> endereco) {
		this.endereco = endereco;
	}

	public Set<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
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

	public String getNumeroCarteirinha() {
		return numeroCarteirinha;
	}

	public void setNumeroCarteirinha(String numeroCarteirinha) {
		this.numeroCarteirinha = numeroCarteirinha;
	}

	public List<Consulta> getConsulta() {
		return consulta;
	}

	public void setConsulta(List<Consulta> consulta) {
		this.consulta = consulta;
	}
	

}
