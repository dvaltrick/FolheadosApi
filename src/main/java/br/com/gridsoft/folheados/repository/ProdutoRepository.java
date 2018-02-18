package br.com.gridsoft.folheados.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.gridsoft.folheados.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
	@Query("SELECT A FROM Produto A " +
           "  JOIN FETCH A.precos B " +
		   " WHERE B.dataInicio <= :data_atual ")
	public List<Produto> catalogo(@Param("data_atual") Date data_atual );
	

	@Query("SELECT DISTINCT A FROM Produto A " +
           "  LEFT JOIN A.precos B ")
	public List<Produto> listar();
}
