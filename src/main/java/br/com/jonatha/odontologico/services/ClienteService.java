package br.com.jonatha.odontologico.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jonatha.odontologico.domain.Cliente;
import br.com.jonatha.odontologico.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository repo;
	
	//CREATE
	public Cliente inserirCliente(Cliente obj) {
		obj=repo.save(obj);
		return obj;
	}
	
	//READ A CLIENT
	public Optional<Cliente> findById(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj;
	}
	
	//READ ALL CLIENT
	public List<Cliente> findAll(){
		return repo.findAll();
	}
	
	//UPDATE
	public Cliente atualizarCliente(Cliente obj) {
		Optional<Cliente> newObj = findById(obj.getId());
		updateData(obj, newObj.get());
		return repo.save(newObj.get());
	}
	
	public void updateData(Cliente obj, Cliente newObj) {
		newObj.setCpf(obj.getCpf());
		newObj.setData_nascimento(obj.getData_nascimento());
		newObj.setEmail(obj.getEmail());
		newObj.setNome(obj.getNome());
		newObj.setPlanoSaude(obj.isPlanoSaude());
		newObj.setNomePlanoSaude(obj.getNomePlanoSaude());
		newObj.setNumeroCarteirinha(obj.getNumeroCarteirinha());
	}
	
	//DELETE
	public void delete(Integer id) {
		repo.deleteById(id);
	}
}
