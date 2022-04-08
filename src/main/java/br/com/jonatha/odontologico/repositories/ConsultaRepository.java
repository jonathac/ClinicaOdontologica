package br.com.jonatha.odontologico.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.jonatha.odontologico.domain.Cliente;
import br.com.jonatha.odontologico.domain.Consulta;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Integer>{

	@Transactional(readOnly=true)
	Page<Consulta> findByCliente(Cliente cliente, Pageable pageRequest);
	
}
