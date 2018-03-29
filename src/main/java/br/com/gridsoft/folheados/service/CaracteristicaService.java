package br.com.gridsoft.folheados.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gridsoft.folheados.model.Caracteristica;
import br.com.gridsoft.folheados.model.ItemCaracteristica;
import br.com.gridsoft.folheados.repository.CaracteristicaRepository;

@Service
public class CaracteristicaService {
	@Autowired
	private CaracteristicaRepository repository;
	
	@Autowired
	private ItemCaracteristicaService itemCarService;
	
	public Caracteristica save(Caracteristica toSave) throws Exception{
		
		try{
			Set<ItemCaracteristica> novosItens = new HashSet<ItemCaracteristica>();
			Set<ItemCaracteristica> itens = toSave.getItens();
			if(itens != null){
				for (ItemCaracteristica item : itens) {
					if(item.getId() == null ){
						ItemCaracteristica novoItem = itemCarService.save(item);
						novosItens.add(novoItem);
					}else{
						novosItens.add(item);
					}
				}
			}
			
			toSave.setItens(novosItens);
			
			toSave = repository.save(toSave);
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("Não foi possível cadastrar a caracteristica");
		}
		
		return toSave;
	}
	
	
	public List<Caracteristica> getAll(){
		return repository.findAll();
	}
	
	public Caracteristica get(Integer id){
		return repository.findOne(id);
	}
	
	public void delete(Integer id) throws Exception{
		try{
			repository.delete(id);
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("Não foi possível excluir a caracteristica");
		}
	}
	
}
