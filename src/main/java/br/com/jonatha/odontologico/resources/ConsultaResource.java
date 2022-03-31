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
import br.com.jonatha.odontologico.domain.Consulta;
import br.com.jonatha.odontologico.dto.ConsultaNewDTO;
import br.com.jonatha.odontologico.services.ClienteService;
import br.com.jonatha.odontologico.services.ConsultaService;

@RestController
@RequestMapping("/consultas")
public class ConsultaResource {

	// Injeção de dependencias
	@Autowired
	ConsultaService service;

	@Autowired
	ClienteService clienteService;
	
	// CREATE
	@PostMapping("/{id}")
	public ResponseEntity<?> inserirConsulta(@Valid @RequestBody ConsultaNewDTO objDTO, @PathVariable Integer id) {
		Optional<Cliente> cli = clienteService.findById(id);
		if (cli.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Consulta obj = service.fromDTO(objDTO);
		obj.setCliente(cli.get());
		obj = service.inserirConsulta(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	// READ A CLIENT
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarConsultaID(@PathVariable Integer id) {
		Optional<Consulta> obj = service.findById(id);
		if (obj.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(obj);
	}

	// READ ALL CLIENT
	@GetMapping()
	public ResponseEntity<List<Consulta>> buscarTodosConsultas() {
		List<Consulta> list = new ArrayList<>();
		list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	// UPDATE
	@PutMapping("/{id}")
	public ResponseEntity<Void> atualizarId(@PathVariable Integer id, @RequestBody ConsultaNewDTO objDTO) {
		Consulta obj = service.fromDTO(objDTO);
		if (service.findById(id).isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		obj.setId(id);
		obj.setCliente(service.findById(id).get().getCliente());
		obj = service.atualizarConsulta(obj);
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
