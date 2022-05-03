package br.edu.pds.piloto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.pds.piloto.domains.Estado;

public interface EstadoRepositorio extends JpaRepository<Estado, Long> {

}
