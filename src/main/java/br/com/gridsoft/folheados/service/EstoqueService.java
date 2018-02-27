package br.com.gridsoft.folheados.service;

import java.lang.reflect.Method;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gridsoft.folheados.model.Estoque;
import br.com.gridsoft.folheados.model.Franquia;
import br.com.gridsoft.folheados.repository.EstoqueRepository;

@Service
public class EstoqueService {
	@Autowired
	private EstoqueRepository repository;
	
	public Estoque gravar(Estoque estoque) throws Exception{
		Estoque retorno = null;
		
		try{
			retorno = repository.save(estoque);
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("Não foi possível salvar o estoque");
		}
		
		return retorno;
	}
	
	public List<Estoque> buscar(){
		return repository.findAll();
	}
	
	public Estoque buscar(Integer id){
		return repository.findOne(id);
	}
	
	public List<Estoque> buscarPorFranquia(Integer franquia_id){
		return repository.buscar(franquia_id);
	}
	
	public void excluir(Integer id) throws Exception{
		try{
			repository.delete(id);
		}catch(Exception e){
			throw new Exception("Não foi possível apagar o registro");
		}
	}
	
	public Estoque alteraEstoque(Integer id, Integer quantidade) throws Exception{
		Estoque alterado = null;
		try{
			Estoque estoqueAlterar = repository.findOne(id);
			
			if(estoqueAlterar != null){
				estoqueAlterar.setQuantidade(estoqueAlterar.getQuantidade() + quantidade);
			
				alterado = repository.save(estoqueAlterar);
			}else{
				throw new Exception("Estoque não cadastrado");
			}
		}catch(Exception e){
			throw new Exception("Não foi possível alterar o estoque");
		}
		
		return alterado;
	}
	
}
