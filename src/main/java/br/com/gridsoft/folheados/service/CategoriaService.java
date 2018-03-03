package br.com.gridsoft.folheados.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gridsoft.folheados.model.Categoria;
import br.com.gridsoft.folheados.repository.CategoriaRepository;

@Service
public class CategoriaService {
	@Autowired 
	private CategoriaRepository repository;
	
	public Categoria gravar(Categoria categoria) throws Exception{
		try{
			categoria = repository.save(categoria);
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("Não foi possível gravar a categoria");
		}
		
		return categoria;
	}
	
	public List<Categoria> buscar(){
		return repository.findAll();
	}
	
	public Categoria buscar(Integer id){
		return repository.findOne(id);
	}
	
	public void excluir(Integer id) throws Exception{
		try{
			repository.delete(id);
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("Não foi possível excluir a categoria");
		}
	}
	
}
