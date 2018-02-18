package br.com.gridsoft.folheados.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="preco")
public class Preco {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private Float preco;
	
	private Date dataInicio;
	
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=Produto.class)
	@JoinColumn(name="produto_id")
	@JsonBackReference
	private Produto produto;
	
	@Transient
	private Integer produtoId;
	
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Float getPreco() {
		return preco;
	}

	public void setPreco(Float preco) {
		this.preco = preco;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public Integer getProdutoId() {
		if(produtoId == null && produto != null){
			return produto.getId();
		}else{
			return produtoId;
		}
	}

	public void setProdutoId(Integer produtoId) {
		this.produtoId = produtoId;
	}

	
}
