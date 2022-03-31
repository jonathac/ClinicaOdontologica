package br.com.jonatha.odontologico.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.jonatha.odontologico.domain.Usuario;
import br.com.jonatha.odontologico.dto.UsuarioNewDTO;
import br.com.jonatha.odontologico.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {

	// Injeção de dependencias
	@Autowired
	UsuarioService service;

	// CREATE
	@PostMapping()
	public ResponseEntity<Void> inserirUsuario(@Valid @RequestBody UsuarioNewDTO objDto) {
		Usuario obj = service.fromDto(objDto);
		obj = service.inserirUsuario(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	// READ A USER
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarUsuarioID(@PathVariable Integer id) {
		Optional<Usuario> obj = service.findById(id);
		if (obj.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(obj);
	}

	// READ ALL USERS
	@GetMapping()
	public ResponseEntity<List<Usuario>> buscarTodosUsuarios() {
		List<Usuario> list = new ArrayList<>();
		list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	// UPDATE
	@PutMapping("/{id}")
	public ResponseEntity<Void> atualizarId(@PathVariable Integer id, @RequestBody Usuario obj) {
		if (service.findById(id).isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		obj.setId(id);
		Usuario newObj = service.atualizarUsuario(obj);
		return ResponseEntity.noContent().build();
	}

	// DELETE
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteId(@PathVariable Integer id) {
		if (service.findById(id).isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
