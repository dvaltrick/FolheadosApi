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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.gridsoft.folheados.model.Caracteristica;
import br.com.gridsoft.folheados.model.Categoria;
import br.com.gridsoft.folheados.service.CaracteristicaService;

@CrossOrigin(origins="*")
@RestController
public class CaracteristicaController {
	@Autowired
	private CaracteristicaService service;
	
	@RequestMapping(value="/api/caracteristica",
					method={RequestMethod.POST, RequestMethod.PUT},
					consumes=MediaType.APPLICATION_JSON_VALUE,
					produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> gravar(@RequestBody Caracteristica caracteristica){
		Map<String, Object> result = new HashMap<String,Object>();
		
		try{
			caracteristica = service.save(caracteristica);
			result.put("id", caracteristica.getId());
		}catch(Exception e){
			e.printStackTrace();
			result.put("erro", e.getMessage());
		}
		
		return result;
		
	}
	
	@RequestMapping(value="/api/caracteristica",
	        method=RequestMethod.GET,
	        produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Caracteristica> buscarTodas(){
		return service.getAll();
	}
	
	@RequestMapping(value="/api/caracteristica/{id}",
	        method=RequestMethod.GET,
	        produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Caracteristica buscarCaracteristica(@PathVariable("id") Integer id){
		return service.get(id);
	}
	
	@RequestMapping(value="/api/caracteristica/{id}",
	        method=RequestMethod.DELETE,
	        produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, String> excluir(@PathVariable("id") Integer id){
		Map<String, String> result = new HashMap<String, String>();
		
		try{
			service.delete(id); 
			result.put("success", "sucesso");
		}catch(Exception e){
			result.put("erro", e.getMessage());
		}
		
		return result;
	}

}
