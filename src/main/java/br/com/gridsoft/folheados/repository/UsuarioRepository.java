package br.com.gridsoft.folheados.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.gridsoft.folheados.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	@Query("SELECT A FROM Usuario A " +
           " WHERE A.nome = :nome_filtro ")
	public List<Usuario> buscarUsuarioPorNome(@Param("nome_filtro") String nome);
	
	@Query("SELECT A FROM Usuario A" +
	       " WHERE A.nome = :nome " +
		   "   AND A.senha = :senha "	)
	public Usuario login(@Param("nome") String nome, @Param("senha") String senha);
}
