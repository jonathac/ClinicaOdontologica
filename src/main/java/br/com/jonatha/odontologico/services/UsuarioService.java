package br.com.jonatha.odontologico.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jonatha.odontologico.domain.Usuario;
import br.com.jonatha.odontologico.dto.UsuarioNewDTO;
import br.com.jonatha.odontologico.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository repo;
	
	//CREATE
	public Usuario inserirUsuario(Usuario obj) {
		obj=repo.save(obj);
		return obj;
	}
	
	//READ A USER
	public Optional<Usuario> findById(Integer id) {
		Optional<Usuario> obj = repo.findById(id);
		return obj;
	}
	
	//READ ALL USERS
	public List<Usuario> findAll(){
		return repo.findAll();
	}
	
	//UPDATE
	public Usuario atualizarUsuario(Usuario obj) {
		Optional<Usuario> newObj = findById(obj.getId());
		updateData(obj, newObj.get());
		return repo.save(newObj.get());
	}
	
	public void updateData(Usuario obj, Usuario newObj) {
		newObj.setEmail(obj.getEmail());
		newObj.setNome(obj.getNome());
	}
	
	//DELETE
	public void delete(Integer id) {
		repo.deleteById(id);
	}
	
	public Usuario fromDto(UsuarioNewDTO objDto) {
		Usuario obj = new Usuario();
		obj.setNome(objDto.getNome());
		obj.setEmail(objDto.getEmail());
		obj.setSenha(objDto.getSenha());
		return obj;
	}
}
