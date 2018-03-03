package br.com.gridsoft.folheados.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gridsoft.folheados.enumarator.StatusPedido;
import br.com.gridsoft.folheados.model.Estoque;
import br.com.gridsoft.folheados.model.Pedido;
import br.com.gridsoft.folheados.repository.PedidoRepository;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository repository;

	@Autowired
	private VendedorService vendedorService;
	
	@Autowired
	private FranquiaService franquiaService;
	
	public Pedido criarPedido(Pedido pedido) throws Exception {
		Pedido pedidoSalvo = null;
		try{
			pedido.setFranquia(franquiaService.buscarFranquia(pedido.getFranquiaId()));
			pedido.setVendedor(vendedorService.buscarVendedor(pedido.getVendedorId()));
			pedido.setStatus(StatusPedido.ABERTO);
			
			pedidoSalvo = repository.save(pedido);
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("Não foi possível criar o pedido");
		}
		
		return pedidoSalvo;
	}

	public Pedido retornaPedido(Integer id) {
		return repository.findOne(id);
	}

	public Pedido alterarStatus(Integer id, String status) throws Exception {
		Pedido pedido = null;
		
		try{
			pedido = repository.findOne(id);
			pedido.setStatus(StatusPedido.valueOf(status));
			repository.save(pedido);
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("Não foi possível alterar o status do pedido");
		}
		
		return pedido;
	}
	
	
}
