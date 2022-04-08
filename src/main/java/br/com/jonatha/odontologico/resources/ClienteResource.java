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

import br.com.jonatha.odontologico.domain.Cliente;
import br.com.jonatha.odontologico.dto.ClienteNewDTO;
import br.com.jonatha.odontologico.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {

	// Injeção de dependencias
	@Autowired
	ClienteService service;

	// CREATE
	@PostMapping()
	public ResponseEntity<Cliente> inserirCliente(@Valid @RequestBody ClienteNewDTO ClienteDTO) { 
		Cliente obj = service.fromDTO(ClienteDTO);	
		obj = service.inserirCliente(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	// READ A CLIENT
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarClienteID(@PathVariable Integer id) {
		Optional<Cliente> obj = service.findById(id);
		if (obj.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(obj);
	}

	// READ ALL CLIENT
	@GetMapping()
	public ResponseEntity<List<Cliente>> buscarTodosClientes() {
		List<Cliente> list = new ArrayList<>();
		list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	// UPDATE
	@PutMapping("/{id}")
	public ResponseEntity<Void> atualizarId(@PathVariable Integer id,@Valid @RequestBody ClienteNewDTO ClienteDTO) {
		Cliente obj = service.fromDTO(ClienteDTO);	
		if (service.findById(id).isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		obj.setId(id);
		obj = service.atualizarCliente(obj);
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
