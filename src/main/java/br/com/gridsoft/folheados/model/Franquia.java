package br.com.gridsoft.folheados.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="franquia")
public class Franquia extends Endereco {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;

	@OneToOne(cascade=CascadeType.ALL)
	private Usuario usuario;
	
	@LazyCollection(LazyCollectionOption.TRUE)
	@OneToMany(mappedBy="franquia")
	@JsonManagedReference
	private Set<Estoque> estoques;
	
	@LazyCollection(LazyCollectionOption.TRUE)
	@OneToMany(mappedBy="franquia")
	@JsonManagedReference(value="franquia_pedido")
	private Set<Pedido> pedidos;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Set<Estoque> getEstoques() {
		return estoques;
	}

	public void setEstoques(Set<Estoque> estoques) {
		this.estoques = estoques;
	}

	public Set<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(Set<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	
	
}
