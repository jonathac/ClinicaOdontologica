package br.com.jonatha.odontologico.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.jonatha.odontologico.domain.Cliente;
import br.com.jonatha.odontologico.domain.Consulta;
import br.com.jonatha.odontologico.dto.ConsultaNewDTO;
import br.com.jonatha.odontologico.repositories.ConsultaRepository;

@Service
public class ConsultaService {

	@Autowired
	ConsultaRepository repo;

	@Autowired
	ClienteService clienteService;
	
	// CREATE
	public Consulta inserirConsulta(Consulta obj) {
		obj = repo.save(obj);
		return obj;
	}

	// READ A USER
	public Optional<Consulta> findById(Integer id) {
		Optional<Consulta> obj = repo.findById(id);
		return obj;
	}

	// READ ALL USERS
	public List<Consulta> findAll() {
		return repo.findAll();
	}

	// UPDATE
	public Consulta atualizarConsulta(Consulta obj) {
		Optional<Consulta> newObj = findById(obj.getId());
		updateData(obj, newObj.get());
		return repo.save(newObj.get());
	}

	public void updateData(Consulta obj, Consulta newObj) {
		newObj.setDataConsulta(obj.getDataConsulta());
		newObj.setHorarioConsulta(obj.getHorarioConsulta());
		newObj.setProcedimento(obj.getProcedimento());
	}

	// DELETE
	public void delete(Integer id) {
		repo.deleteById(id);
	}

	// CONSULTA FROM DTO
	public Consulta fromDTO(ConsultaNewDTO objDTO) {
		Consulta obj = new Consulta();
		obj.setDataConsulta(objDTO.getDataConsulta());
		obj.setHorarioConsulta(objDTO.getHorarioConsulta());
		obj.setProcedimento(objDTO.getProcedimento());
		
		return obj;
	}
	//INSERIR AUTENTICAÇÃO DE USUARIO
	// CONSULTA PAGINADA
	/*
	public Page<Consulta> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
	
		serSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Cliente cliente =  clienteService.find(user.getId());
		return repo.findByCliente(cliente, pageRequest);
	}*/
}
