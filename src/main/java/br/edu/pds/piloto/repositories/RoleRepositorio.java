package br.edu.pds.piloto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.pds.piloto.domains.Role;

public interface RoleRepositorio extends JpaRepository<Role, Long> {

}
