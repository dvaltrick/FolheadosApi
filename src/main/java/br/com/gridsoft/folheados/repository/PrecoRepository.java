package br.com.gridsoft.folheados.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gridsoft.folheados.model.Preco;

@Repository
public interface PrecoRepository extends JpaRepository<Preco, Integer> {

}
