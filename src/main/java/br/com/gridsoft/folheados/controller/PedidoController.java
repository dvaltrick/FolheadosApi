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

import br.com.gridsoft.folheados.model.Estoque;
import br.com.gridsoft.folheados.model.Pedido;
import br.com.gridsoft.folheados.service.PedidoService;

@RestController
public class PedidoController {
	@Autowired
	private PedidoService service;
	
	@RequestMapping(value="/api/pedido",
	        method={RequestMethod.POST},
	        consumes=MediaType.APPLICATION_JSON_VALUE,
	        produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> gravar(@RequestBody Pedido pedido){
		Map<String, Object> result = new HashMap<String,Object>();
		
		try{
			pedido = service.criarPedido(pedido);
			result.put("id", pedido.getId());
		}catch(Exception e){
			result.put("erro", e.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping(value="/api/alterarStatusPedido",
	        method={RequestMethod.POST},
	        consumes=MediaType.APPLICATION_JSON_VALUE,
	        produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> alteraStatusPedido(@RequestBody HashMap<String, Object> pedido){
		Map<String, Object> result = new HashMap<String,Object>();
		
		try{
			Integer id = Integer.parseInt(pedido.get("id").toString());
			String status = pedido.get("status").toString();
			
			Pedido pedidoAlterar = service.alterarStatus(id, status);
			result.put("id", pedidoAlterar.getId());
		}catch(Exception e){
			result.put("erro", e.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping(value="/api/pedido/{id}",
	        method=RequestMethod.GET,
	        produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Pedido retornaPedido(@PathVariable("id") Integer id){
		return service.retornaPedido(id);
	}
	
}
