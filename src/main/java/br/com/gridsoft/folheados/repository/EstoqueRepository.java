package br.com.gridsoft.folheados.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.gridsoft.folheados.model.Estoque;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Integer> {
	@Query("SELECT A FROM Estoque A  " +
           " JOIN FETCH A.franquia B " +
		   " WHERE B.id = :franquia_id " )
	public List<Estoque> buscar(@Param("franquia_id") Integer franquia_id);

}
