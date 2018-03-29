package br.com.gridsoft.folheados.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class ItemPedido {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=Pedido.class)
	@JoinColumn(name="pedido_id", nullable=true, referencedColumnName="id")
	@JsonBackReference(value="pedido_produto")
	private Pedido pedido;
	
	private Integer produtoId;
	
	private Float preco;
	
	private Integer quantidade;
	
	private Integer itemCaracteristicaId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Integer getProdutoId() {
		return produtoId;
	}

	public void setProdutoId(Integer produtoId) {
		this.produtoId = produtoId;
	}

	public Float getPreco() {
		return preco;
	}

	public void setPreco(Float preco) {
		this.preco = preco;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Integer getItemCaracteristicaId() {
		return itemCaracteristicaId;
	}

	public void setItemCaracteristicaId(Integer itemCaracteristicaId) {
		this.itemCaracteristicaId = itemCaracteristicaId;
	}
	
	
}
