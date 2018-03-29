package br.com.gridsoft.folheados.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.gridsoft.folheados.enumarator.StatusPedido;

@Entity
public class Pedido {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=Vendedor.class)
	@JoinColumn(name="vendedor_id")
	@JsonBackReference(value="vendedor_pedido")
	private Vendedor vendedor;
	
	@Transient
	private Integer vendedorId;
	
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=Franquia.class)
	@JoinColumn(name="franquia_id")
	@JsonBackReference(value="franquia_pedido")
	private Franquia franquia;
	
	@Transient
	private Integer franquiaId;
	
	private StatusPedido status;

	private Float total;

	@LazyCollection(LazyCollectionOption.TRUE)
	@OneToMany(mappedBy="pedido")
	@Cascade({CascadeType.ALL})
	@JsonManagedReference(value="pedido_produto")
	private Set<ItemPedido> itens;


	public Set<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public Integer getVendedorId() {
		if(vendedorId != null && vendedor == null){
			return vendedorId;
		}else{
			return vendedor.getId();
		}
	}

	public void setVendedorId(Integer vendedorId) {
		this.vendedorId = vendedorId;
	}

	public Franquia getFranquia() {
		return franquia;
	}

	public void setFranquia(Franquia franquia) {
		this.franquia = franquia;
	}

	public Integer getFranquiaId() {
		if(franquiaId != null && franquia == null){
			return franquiaId;
		}else{
			return franquia.getId();
		}
	}

	public void setFranquiaId(Integer franquiaId) {
		this.franquiaId = franquiaId;
	}

	public StatusPedido getStatus() {
		return status;
	}

	public void setStatus(StatusPedido status) {
		this.status = status;
	}

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}
	
	
	
}
