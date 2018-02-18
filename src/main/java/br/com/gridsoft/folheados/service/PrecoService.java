package br.com.gridsoft.folheados.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gridsoft.folheados.model.Preco;
import br.com.gridsoft.folheados.repository.PrecoRepository;

@Service
public class PrecoService {
	@Autowired
	private PrecoRepository repository;
	
	@Autowired
	private ProdutoService produtoService;
	
	public Preco gravar(Preco precoParaSalvar) throws Exception{
		try{
			precoParaSalvar.setProduto(produtoService.buscarProduto(precoParaSalvar.getProdutoId()));
			precoParaSalvar = repository.save(precoParaSalvar);
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("Não foi possível vincular o preço");
		}
		
		return precoParaSalvar;
	}
	
	public void excluir(Integer id) throws Exception{
		try{
			repository.delete(id);
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("Não foi possível excluir o preço do produto.");
		}
	}
	
}
