package br.edu.pds.piloto.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class LoginController {
	@GetMapping("/login")
	public ModelAndView logar() {
		ModelAndView mv = new ModelAndView("Login");
		
		return mv;
	}
	
}
