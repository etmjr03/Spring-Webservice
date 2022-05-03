package br.edu.pds.piloto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.pds.piloto.domains.Cidade;

public interface CidadeRepositorio extends JpaRepository<Cidade, Long> {

}
