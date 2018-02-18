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

import br.com.gridsoft.folheados.model.Produto;
import br.com.gridsoft.folheados.service.ProdutoService;

@RestController
public class ProdutoController {
	@Autowired
	private ProdutoService service;
	
	@RequestMapping(value="/api/produto",
			        method={RequestMethod.POST, RequestMethod.PUT},
			        consumes=MediaType.APPLICATION_JSON_VALUE,
			        produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> gravar(@RequestBody Produto produto){
		Map<String, Object> result = new HashMap<String,Object>();
		
		try{
			produto = service.gravar(produto);
			result.put("id", produto.getId());
		}catch(Exception e){
			result.put("erro", e.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping(value="/api/produto",
			        method=RequestMethod.GET,
			        produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Produto> buscarTodos(){
		return service.buscarTodos();
	}
	
	@RequestMapping(value="/api/catalogo",
			        method=RequestMethod.GET,
			        produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Produto> catalogo(){
		return service.catalogo();
	}
	
}
