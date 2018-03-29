package br.com.gridsoft.folheados.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.gridsoft.folheados.model.Usuario;
import br.com.gridsoft.folheados.model.Vendedor;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Integer> {
	@Query("SELECT A FROM Vendedor A " +
           "  JOIN FETCH A.usuario B " +
		   " WHERE B.nome = :nome "	 )
	public Vendedor buscaVendedorUsuario(@Param("nome") String nome);
}
