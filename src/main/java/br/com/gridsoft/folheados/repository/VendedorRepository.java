package br.com.gridsoft.folheados.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gridsoft.folheados.model.Vendedor;

public interface VendedorRepository extends JpaRepository<Vendedor, Integer> {

}
