package br.com.gridsoft.folheados.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.tomcat.util.codec.binary.Base64;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gridsoft.folheados.model.Preco;
import br.com.gridsoft.folheados.model.Produto;
import br.com.gridsoft.folheados.repository.ProdutoRepository;

@Service
public class ProdutoService {
	@Autowired
	private ProdutoRepository repository;
	
	@Autowired
	private PrecoService precoService;
	
	public Produto gravar(Produto produtoParaGravar) throws Exception{
		try{
			if(produtoParaGravar.getImagemBase64() != null){
				produtoParaGravar.setImagem(Base64.decodeBase64(produtoParaGravar.getImagemBase64().getBytes()));
			}
			
			produtoParaGravar = repository.save(produtoParaGravar);
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("Não foi possível gravar o produto");
		}
		
		return produtoParaGravar;
	}
	
	public void excluir(Integer id) throws Exception{
		try{
			repository.delete(id);
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("Não foi possível excluir o produto");
		}
	}
	
	public List<Produto> catalogo(){
		Date dataAtual = new Date();
		System.out.println(dataAtual);
		return repository.catalogo(dataAtual);
	}

	public List<Produto> buscarTodos() {
		return repository.listar();
	}
	
	public Produto buscarProduto(Integer id){
		return repository.findOne(id);
	}
	
}
