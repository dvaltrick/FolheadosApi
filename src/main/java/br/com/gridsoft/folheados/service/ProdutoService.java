package br.com.gridsoft.folheados.service;

import java.util.Date;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gridsoft.folheados.model.Caracteristica;
import br.com.gridsoft.folheados.model.Categoria;
import br.com.gridsoft.folheados.model.Produto;
import br.com.gridsoft.folheados.repository.CategoriaRepository;
import br.com.gridsoft.folheados.repository.ProdutoRepository;

@Service
public class ProdutoService {
	@Autowired
	private ProdutoRepository repository;
	
	@Autowired
	private CategoriaRepository categRepository;
	
	@Autowired
	private PrecoService precoService;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private CaracteristicaService caracteristicaService;
	
	public Produto gravar(Produto produtoParaGravar) throws Exception{
		try{
			if(produtoParaGravar.getCategoriaId() != null){
				Categoria categoria = categoriaService.buscar(produtoParaGravar.getCategoriaId());
				produtoParaGravar.setCategoria(categoria);
			}
			
			if(produtoParaGravar.getCaracteristicaId() != null){
				Caracteristica caracteristica = caracteristicaService.get(produtoParaGravar.getCaracteristicaId());
				produtoParaGravar.setCaracteristica(caracteristica);
			}
			
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
	
	public List<Produto> catalogo(Integer categoriaId){
		Date dataAtual = new Date();
		System.out.println(dataAtual);
		return categRepository.catalogo(categoriaId);
	}

	public List<Produto> buscarTodos() {
		return repository.listar();
	}
	
	public Produto buscarProduto(Integer id){
		return repository.get(id);
	}
	
}
