package br.com.jonatha.odontologico.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jonatha.odontologico.domain.Cliente;
import br.com.jonatha.odontologico.domain.Endereco;
import br.com.jonatha.odontologico.dto.ClienteNewDTO;
import br.com.jonatha.odontologico.repositories.ClienteRepository;
import br.com.jonatha.odontologico.repositories.EnderecoRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository repo;
	
	@Autowired
	EnderecoRepository enderecoRepository;

	// CREATE
	@Transactional
	public Cliente inserirCliente(Cliente obj) {
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEndereco());
		return obj;
	}

	// READ A CLIENT
	public Optional<Cliente> findById(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj;
	}

	// READ ALL CLIENT
	public List<Cliente> findAll() {
		return repo.findAll();
	}

	// UPDATE
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

	// DELETE
	public void delete(Integer id) {
		repo.deleteById(id);
	}

	// CLIENT FROM DTO
	public Cliente fromDTO(ClienteNewDTO objDTO) {
		//adiconando informações ao objeto cliente
		Cliente obj = new Cliente();
		obj.setNome(objDTO.getNome());
		obj.setEmail(objDTO.getEmail());
		obj.setSenha(objDTO.getSenha());
		obj.setData_nascimento(objDTO.getDataNascimento());
		obj.setCpf(objDTO.getCpf());
		obj.setPlanoSaude(objDTO.isPlanoSaude());
		obj.setNomePlanoSaude(objDTO.getNomePlanoSaude());
		obj.setNumeroCarteirinha(objDTO.getNumeroPlanoSaude());
		
		//adicionando informações ao objeto endereço
		Endereco end = new Endereco();
		end.setCliente(obj);
		end.setCep(objDTO.getCep());
		end.setLogradouro(objDTO.getLogradouro());
		end.setNumero(objDTO.getNumero());
		end.setComplemento(objDTO.getComplemento());
		end.setBairro(objDTO.getBairro());
		end.setCidade(objDTO.getCidade());
		end.setEstado(objDTO.getEstado());
		
		obj.getTelefones().add(objDTO.getTelefone1());
		if (objDTO.getTelefone2() != null) {
			obj.getTelefones().add(objDTO.getTelefone2());
		}
		if (objDTO.getTelefone3() != null) {
			obj.getTelefones().add(objDTO.getTelefone3());
		}

		
		obj.getEndereco().add(end);
		return obj;
	}

}
