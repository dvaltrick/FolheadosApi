package br.com.gridsoft.folheados.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gridsoft.folheados.model.Vendedor;
import br.com.gridsoft.folheados.repository.VendedorRepository;

@Service
public class VendedorService {
	@Autowired
	private VendedorRepository repository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	public Vendedor salvar(Vendedor vendedorParaSalvar) throws Exception{
		vendedorParaSalvar = tratarCriacaoDeUsuario(vendedorParaSalvar);
		try{
			vendedorParaSalvar = repository.save(vendedorParaSalvar);
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("Não foi possível salvar um novo vendedor");
		} 
		
		return vendedorParaSalvar;
	}
	
	public List<Vendedor> buscarTodos(){
		return repository.findAll();
	}
	
	public Vendedor buscarVendedor(Integer id){
		return repository.findOne(id);
	}
	
	public void excluir(Integer id) throws Exception{
		try{
			repository.delete(id);
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("Não foi possível realizar a exclusão do vendedor");
		}
	}
	
	private Vendedor tratarCriacaoDeUsuario(Vendedor vendedorParaTratar) throws Exception{
		if(vendedorParaTratar.getUsuario().getId() == null ){
			vendedorParaTratar.setUsuario(usuarioService.novo(vendedorParaTratar.getUsuario()));
		}
		
		return vendedorParaTratar;
	}
	
}
