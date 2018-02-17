package br.com.gridsoft.folheados.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.gridsoft.folheados.model.Usuario;
import br.com.gridsoft.folheados.model.Vendedor;
import br.com.gridsoft.folheados.service.VendedorService;

@RestController
public class VendedorController {
	@Autowired
	private VendedorService service;
	
	@RequestMapping(
			method={RequestMethod.POST,RequestMethod.PUT},
			value="/api/vendedor",
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE
			)
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
			method={RequestMethod.GET},
			value="/api/vendedor", 
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	@ResponseBody
	public List<Vendedor> buscarTodos(){
		return service.buscarTodos();
	}
}
