package br.com.gridsoft.folheados.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.gridsoft.folheados.model.Usuario;
import br.com.gridsoft.folheados.service.UsuarioService;

@RestController
public class LoginController {
	@Autowired
	UsuarioService service;
	
	@RequestMapping(
			value="/api/login",
			method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> login(Usuario login){
		Map<String, Object> retornoLogin = new HashMap<String, Object>();
		
		return retornoLogin;
		
	}
}
