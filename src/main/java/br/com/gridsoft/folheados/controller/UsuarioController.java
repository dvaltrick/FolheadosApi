package br.com.gridsoft.folheados.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.gridsoft.folheados.model.Usuario;
import br.com.gridsoft.folheados.service.UsuarioService;

@CrossOrigin(origins="*")
@RestController
public class UsuarioController {
	@Autowired
	private UsuarioService service;
	
	@RequestMapping(
			method=RequestMethod.POST,
			value="/api/usuario",
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	@ResponseBody
	public Map<String,Object> novo(@RequestBody Usuario usuario){
		Map<String, Object> result = new HashMap<String, Object>();
		
		try{
			usuario = service.novo(usuario);
			result.put("id", usuario.getId());
		}catch (Exception e) {
			result.put("erro", e.getMessage());
		}
		
		return result;
		
	}
	
	@RequestMapping(
			method=RequestMethod.PUT,
			value="/api/usuario",
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	@ResponseBody
	public Map<String,Object> alterar(@RequestBody Usuario usuario){
		Map<String, Object> result = new HashMap<String, Object>();
		
		try{
			usuario = service.novo(usuario);
			result.put("id", usuario.getId());
		}catch (Exception e) {
			result.put("erro", e.getMessage());
		}
		
		return result;
		
	}
	
	@RequestMapping(
			method=RequestMethod.DELETE,
			value="/api/usuario/{id}",
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	@ResponseBody
	public Map<String,Object> excluir(@PathVariable("id") Integer id){
		Map<String, Object> result = new HashMap<String, Object>();
		
		try{
			service.deletar(id);
			result.put("success", "sucesso");
		}catch (Exception e) {
			result.put("erro", e.getMessage());
		}
		
		return result;
		
	}
	
	@RequestMapping(
			method=RequestMethod.GET,
			value="/api/usuario",
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Usuario> buscarTodosUsuarios(){
		return service.buscarTodosUsuarios();
	}
	
	
}
