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

import br.com.gridsoft.folheados.model.Categoria;
import br.com.gridsoft.folheados.model.Franquia;
import br.com.gridsoft.folheados.service.CategoriaService;

@CrossOrigin(origins="*")
@RestController
public class CategoriaController {
	@Autowired
	private CategoriaService service;
	
	@RequestMapping(value="/api/categoria",
	        method={RequestMethod.POST, RequestMethod.PUT},
	        consumes=MediaType.APPLICATION_JSON_VALUE,
	        produces=MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
	public Map<String, Object> gravar(@RequestBody Categoria categoria){
		Map<String, Object> result = new HashMap<String,Object>();
		
		try{
			categoria = service.gravar(categoria);
			result.put("id", categoria.getId());
		}catch(Exception e){
			result.put("erro", e.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping(value="/api/categoria",
	        method=RequestMethod.GET,
	        produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Categoria> buscarTodas(){
		return service.buscar();
	}
	
	@RequestMapping(value="/api/categoria/{id}",
	        method=RequestMethod.GET,
	        produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Categoria buscarCategoria(@PathVariable("id") Integer id){
		return service.buscar(id);
	}
	
	@RequestMapping(value="/api/categoria/{id}",
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
