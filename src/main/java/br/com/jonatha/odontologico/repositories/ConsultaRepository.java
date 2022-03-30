package br.com.jonatha.odontologico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jonatha.odontologico.domain.Consulta;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Integer>{

}
