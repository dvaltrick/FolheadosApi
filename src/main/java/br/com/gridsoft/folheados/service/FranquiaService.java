package br.com.gridsoft.folheados.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gridsoft.folheados.enumarator.Perfil;
import br.com.gridsoft.folheados.model.Franquia;
import br.com.gridsoft.folheados.repository.FranquiaRepository;

@Service
public class FranquiaService {
	@Autowired
	private FranquiaRepository repository;
	
	public Franquia gravar(Franquia franquiaParaGravar) throws Exception{
		
		try{
			if(franquiaParaGravar.getUsuario() != null){
				franquiaParaGravar.getUsuario().setPerfil(Perfil.FRANQUEADO);
			}
			
			franquiaParaGravar = repository.save(franquiaParaGravar);
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("Não foi possível gravar a franquia.");
		}
		
		return franquiaParaGravar;
	}
	
	public List<Franquia> buscarTodas(){
		return repository.findAll();
	}
	
	public Franquia buscarFranquia(Integer id){
		return repository.findOne(id);
	}
	
	public void excluir(Integer id) throws Exception{
		try{
			repository.delete(id);
		}catch(Exception e){
			throw new Exception("Não foi possível excluir a empresa");
		}
	}
	
}
