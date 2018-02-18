package br.com.gridsoft.folheados.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.gridsoft.folheados.model.Franquia;
import br.com.gridsoft.folheados.model.Produto;
import br.com.gridsoft.folheados.service.FranquiaService;

@RestController
public class FranquiaController {
	@Autowired
	private FranquiaService service;
	
	@RequestMapping(value="/api/franquia",
	        method={RequestMethod.POST, RequestMethod.PUT},
	        consumes=MediaType.APPLICATION_JSON_VALUE,
	        produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> gravar(@RequestBody Franquia franquia){
		Map<String, Object> result = new HashMap<String,Object>();
		
		try{
			franquia = service.gravar(franquia);
			result.put("id", franquia.getId());
		}catch(Exception e){
			result.put("erro", e.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping(value="/api/franquia",
	        method=RequestMethod.GET,
	        produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Franquia> buscarTodas(){
		return service.buscarTodas();
	}
	
	@RequestMapping(value="/api/franquia/{id}",
	        method=RequestMethod.GET,
	        produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Franquia buscarFranquia(@PathVariable("id") Integer id){
		return service.buscarFranquia(id);
	}
	
	@RequestMapping(value="/api/franquia/{id}",
	        method=RequestMethod.DELETE,
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
