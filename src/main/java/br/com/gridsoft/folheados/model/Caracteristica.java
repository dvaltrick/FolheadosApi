package br.com.gridsoft.folheados.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="caracteristica")
@JsonIgnoreProperties("produtos")
public class Caracteristica {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String descricao;
	
	@LazyCollection(LazyCollectionOption.TRUE)
	@OneToMany(mappedBy="caracteristica")
	@Cascade({CascadeType.ALL})
	@OrderBy("descricao")
	@JsonManagedReference(value="item_caracteristica")
	private Set<ItemCaracteristica> itens;

	@LazyCollection(LazyCollectionOption.TRUE)
	@OneToMany(mappedBy="caracteristica")
	@Cascade({CascadeType.ALL})
	@JsonManagedReference(value="caracteristica")
	private Set<Produto> produtos; 
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Set<ItemCaracteristica> getItens() {
		return itens;
	}

	public void setItens(Set<ItemCaracteristica> itens) {
		this.itens = itens;
	}

	public Set<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(Set<Produto> produtos) {
		this.produtos = produtos;
	}
	
	
	
}
