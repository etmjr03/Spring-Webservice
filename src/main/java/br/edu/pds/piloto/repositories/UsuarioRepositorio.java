package br.edu.pds.piloto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.pds.piloto.domains.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

}
