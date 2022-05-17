package br.edu.pds.piloto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.pds.piloto.domains.Permissao;

public interface PermissaoRepositorio extends JpaRepository<Permissao, Long> {

}
