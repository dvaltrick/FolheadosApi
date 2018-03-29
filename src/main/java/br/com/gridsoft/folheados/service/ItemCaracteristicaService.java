package br.com.gridsoft.folheados.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gridsoft.folheados.model.ItemCaracteristica;
import br.com.gridsoft.folheados.repository.ItemCaracteristicaRepository;

@Service
public class ItemCaracteristicaService {
	@Autowired
	private ItemCaracteristicaRepository repository;
	
	public ItemCaracteristica save(ItemCaracteristica toSave) throws Exception{
		try{
			toSave = repository.save(toSave);
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("Não foi possível cadastrar o item da caracteristica");
		}
		
		return toSave;	
	}
	
	public void delete(Integer toDelete) throws Exception{
		try{
			repository.delete(toDelete);
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("Não foi possível excluir o item da caracteristica");
		}
	}
}
