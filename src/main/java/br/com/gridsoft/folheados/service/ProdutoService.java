package br.com.gridsoft.folheados.service;

import java.util.Date;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gridsoft.folheados.model.Produto;
import br.com.gridsoft.folheados.repository.ProdutoRepository;

@Service
public class ProdutoService {
	@Autowired
	private ProdutoRepository repository;
	
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
	
	public List<Produto> catalogo(){
		//DateFormat dateFormat = new DateFormat();
		Date dataAtual = new Date();
		System.out.println(dataAtual);
		return repository.catalogo(dataAtual);
		
	}

	public List<Produto> buscarTodos() {
		return repository.findAll();
	}
	
}
