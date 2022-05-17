package br.edu.pds.piloto.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.pds.piloto.domains.Permissao;
import br.edu.pds.piloto.repositories.PermissaoRepositorio;
import br.edu.pds.piloto.repositories.RoleRepositorio;
import br.edu.pds.piloto.repositories.UsuarioRepositorio;

@Controller
public class PermissaoController {
	
	@Autowired
	private PermissaoRepositorio permissaoRepo;
	
	@Autowired
	private UsuarioRepositorio usuarioRepo;
	
	@Autowired
	private RoleRepositorio roleRepo;
	
	
	@GetMapping("/listarPermissao")
	public ModelAndView listar() {		
		ModelAndView mv = new ModelAndView("permissoes");
		mv.addObject("permissoes", permissaoRepo.findAll());
		
		return mv;
	}
	
	@GetMapping("/cadastrarPermissao")
	public ModelAndView cadastrar(Permissao permissao) {
		ModelAndView mv = new ModelAndView("permissao");
		mv.addObject("permissao", permissao);
		mv.addObject("usuarios", usuarioRepo.findAll());
		mv.addObject("roles", roleRepo.findAll());
		
		return mv;
	}
	
	@PostMapping("/salvarPermissao")
	public ModelAndView salvar(@Valid Permissao permissao, BindingResult result) {		
		if(result.hasErrors()) {
			return cadastrar(permissao);
		}
		
		permissaoRepo.saveAndFlush(permissao);
		
		return new ModelAndView("redirect:/listarPermissao");
	}
	
	@GetMapping("/editarPermissao/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		Optional<Permissao> permissao = permissaoRepo.findById(id);
		
		return cadastrar(permissao.get());		
	}
	
	@GetMapping("/excluirPermissao/{id}")
	public ModelAndView excluir(@PathVariable("id") Long id) {
		Optional<Permissao> permissao = permissaoRepo.findById(id);
		permissaoRepo.delete(permissao.get());
		
		return new ModelAndView("redirect:/listarPermissao");		
	}
}
