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
import br.com.gridsoft.folheados.model.Vendedor;
import br.com.gridsoft.folheados.service.VendedorService;

@CrossOrigin(origins="*")
@RestController
public class VendedorController {
	@Autowired
	private VendedorService service;
	
	@RequestMapping(
			method={RequestMethod.POST,RequestMethod.PUT},
			value="/api/vendedor",
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String,Object> salvar(@RequestBody Vendedor vendedor){
		Map<String, Object> result = new HashMap<String, Object>();
		
		try{
			vendedor = service.salvar(vendedor);
			result.put("id", vendedor.getId());
		}catch (Exception e) {
			result.put("erro", e.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping(
			method={RequestMethod.POST},
			value="/api/vendedorPorUsuario",
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Vendedor buscarPorUsuario(@RequestBody Usuario usuario){
		return service.buscarPorUsuario(usuario);
	}
	
	@RequestMapping(
			method={RequestMethod.GET},
			value="/api/vendedor", 
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Vendedor> buscarTodos(){
		return service.buscarTodos();
	}
	
	@RequestMapping(
			method={RequestMethod.GET},
			value="/api/vendedor/{id}", 
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Vendedor buscarVendedor(@PathVariable("id") Integer id){
		return service.buscarVendedor(id);
	}
	
	@RequestMapping(
			method={RequestMethod.DELETE},
			value="/api/vendedor/{id}", 
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, String> excluir(@PathVariable("id") Integer id){
		Map<String, String> result = new HashMap<String, String>();
		
		try{
			service.excluir(id);
			result.put("success", "sucesso");
		}catch(Exception e){
			result.put("erro", e.getMessage());
		}
		
		return result;
	}

}
