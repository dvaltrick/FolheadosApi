package br.com.gridsoft.folheados.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.gridsoft.folheados.model.Categoria;
import br.com.gridsoft.folheados.model.Produto;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
	@Query("SELECT A FROM Categoria A")
	public List<Categoria> getAll();

	@Query("SELECT B FROM Categoria A " +
	       " JOIN A.produtos B " +
	       " WHERE A.id = :categ_id ")
	public List<Produto> catalogo(@Param("categ_id") Integer categ_id);
}
