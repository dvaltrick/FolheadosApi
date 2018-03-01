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
	
	@Autowired
	private FranquiaService franquiaService;
	
	@Autowired
	private ProdutoService produtoService;
	
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
	
	public Estoque gravarEstoque(Integer idFranquia, Integer idProduto, Integer quantidade) throws Exception{
		
		verificaProblemasEstoque(idFranquia, idProduto);
		
		Estoque estoqueSalvo = null;
		try{
			Estoque estoqueGravar = repository.buscarEstoque(idFranquia, idProduto);
			if(estoqueGravar == null){
				estoqueGravar = new Estoque();
				estoqueGravar.setFranquia(franquiaService.buscarFranquia(idFranquia));
				estoqueGravar.setProduto(produtoService.buscarProduto(idProduto));
				estoqueGravar.setQuantidade(quantidade);
			} else{
				estoqueGravar.setQuantidade(estoqueGravar.getQuantidade() + quantidade);
			}
			
			estoqueSalvo = repository.save(estoqueGravar);
		}catch(Exception e){
			throw new Exception("Não foi possível alterar o estoque");
		}
		
		return estoqueSalvo;
	}
	
	
	private void verificaProblemasEstoque(Integer idFranquia, Integer idProduto) throws Exception{
		if(franquiaService.buscarFranquia(idFranquia) == null){
			throw new Exception("Franquia não cadastrada");
		}
		
		if(produtoService.buscarProduto(idProduto) == null){
			throw new Exception("Produto não cadastrado");
		}
	}
	
}
