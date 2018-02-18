package br.com.gridsoft.folheados.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.gridsoft.folheados.model.Preco;
import br.com.gridsoft.folheados.service.PrecoService;

@RestController
public class PrecoController {
	@Autowired
	private PrecoService service;
	
	@RequestMapping(value="/api/preco",
	        method={RequestMethod.POST, RequestMethod.PUT},
	        consumes=MediaType.APPLICATION_JSON_VALUE,
	        produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> gravar(@RequestBody Preco preco){
		Map<String, Object> result = new HashMap<String,Object>();
		
		try{
			preco = service.gravar(preco);
			result.put("id", preco.getId());
		}catch(Exception e){
			result.put("erro", e.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping(value="/api/preco/{id}",
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
