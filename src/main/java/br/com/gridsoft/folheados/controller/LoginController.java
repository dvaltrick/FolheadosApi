package br.com.gridsoft.folheados.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
	@ResponseBody
	public Map<String, Object> login(@RequestBody Usuario login){
		Map<String, Object> retornoLogin = new HashMap<String, Object>();
		
		try{
			Usuario logado = service.login(login);
			retornoLogin.put("usuario", (Object) logado);
		}catch(Exception e){
			e.printStackTrace();
			retornoLogin.put("erro", e.getMessage());
		}
		
		return retornoLogin;
		
	}
}
