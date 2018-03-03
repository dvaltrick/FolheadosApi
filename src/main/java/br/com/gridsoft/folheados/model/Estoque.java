package br.com.gridsoft.folheados.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Estoque {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=Produto.class)
	@JoinColumn(name="produto_id")
	@JsonBackReference(value="estoques")
	private Produto produto;
	
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=Franquia.class)
	@JoinColumn(name="franquia_id")
	@JsonBackReference
	private Franquia franquia;
	
	private Integer quantidade;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Franquia getFranquia() {
		return franquia;
	}

	public void setFranquia(Franquia franquia) {
		this.franquia = franquia;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	
}
