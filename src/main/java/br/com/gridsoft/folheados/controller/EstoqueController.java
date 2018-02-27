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

import br.com.gridsoft.folheados.model.Estoque;
import br.com.gridsoft.folheados.service.EstoqueService;

@RestController
public class EstoqueController {
	
	@Autowired
	private EstoqueService service;
	
	@RequestMapping(value="/api/estoque",
	        method={RequestMethod.POST, RequestMethod.PUT},
	        consumes=MediaType.APPLICATION_JSON_VALUE,
	        produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> gravar(@RequestBody Estoque estoque){
		Map<String, Object> result = new HashMap<String,Object>();
		
		try{
			estoque = service.gravar(estoque);
			result.put("id", estoque.getId());
		}catch(Exception e){
			result.put("erro", e.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping(value="/api/alterarEstoque",
	        method={RequestMethod.PUT},
	        consumes=MediaType.APPLICATION_JSON_VALUE,
	        produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> alteraEstoque(@RequestBody HashMap<String, Object> alteracao){
		Map<String, Object> result = new HashMap<String,Object>();
		Estoque estoque = null;
		
		try{
			Integer idEstoque = Integer.parseInt(alteracao.get("id").toString());
			Integer quantidade = Integer.parseInt(alteracao.get("quantidade").toString());
			
			estoque = service.alteraEstoque(idEstoque, quantidade);
			result.put("estoque", estoque);
		}catch(Exception e){
			result.put("erro", e.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping(value="/api/estoque",
	        method=RequestMethod.GET,
	        produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Estoque> buscarTodos(){
		return service.buscar();
	}
	
	@RequestMapping(value="/api/estoque/{id}",
	        method=RequestMethod.GET,
	        produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Estoque buscar(@PathVariable("id") Integer id){
		return service.buscar(id);
	}
	
	@RequestMapping(value="/api/estoqueFranquia/{franquia}",
	        method=RequestMethod.GET,
	        produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Estoque> buscarEstoqueFranquia(@PathVariable("franquia") Integer franquia){
		return service.buscarPorFranquia(franquia) ;
	}
	
	@RequestMapping(value="/api/estoque/{id}",
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
