package br.com.gridsoft.folheados.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gridsoft.folheados.repository.VendedorRepository;

@Service
public class VendedorService {
	@Autowired
	private VendedorRepository repository;
}
