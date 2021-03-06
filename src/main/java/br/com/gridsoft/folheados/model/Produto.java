package br.com.gridsoft.folheados.model;

import java.util.Base64;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name="produto")
@JsonIgnoreProperties(value="imagem")
public class Produto {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	
	private String descricao;
	
	@Lob
	@Column(columnDefinition="Blob")
	private byte[] imagem;

	@Transient
	private String imagemBase64;
	
	@LazyCollection(LazyCollectionOption.TRUE)
	@OneToMany(mappedBy="produto")
	@Cascade({CascadeType.ALL})
	@OrderBy("dataInicio desc")
	@JsonManagedReference(value="precos")
	private Set<Preco> precos;

	
	@LazyCollection(LazyCollectionOption.TRUE)
	@OneToMany(mappedBy="produto")
	@Cascade({CascadeType.ALL})
	@JsonManagedReference(value="estoques")
	private Set<Estoque> estoques;
	
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=Categoria.class)
	@JoinColumn(name="categoria_id", nullable=true, referencedColumnName="id")
	@JsonBackReference(value="categoria")
	private Categoria categoria;
	
	@Transient
	private Integer categoriaId;
	
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=Caracteristica.class)
	@JoinColumn(name="caracteristica_id", nullable=true, referencedColumnName="id")
	@JsonBackReference(value="caracteristica")
	private Caracteristica caracteristica;

	@Transient
	private Integer caracteristicaId;
	
	private Double preco;
	
	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Integer getCategoriaId() {
		if(categoriaId  != null || categoria == null){
			return categoriaId;
		}else{
			return categoria.getId();
		}
	}

	public void setCategoriaId(Integer categoriaId) {
		this.categoriaId = categoriaId;
	}
	
	public Integer getCaracteristicaId() {
		if(caracteristicaId  != null || caracteristica == null){
			return caracteristicaId;
		}else{
			return caracteristica.getId();
		}
	}

	public void setCaracteristicaId(Integer caracteristicaId) {
		this.caracteristicaId = caracteristicaId;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Set<Estoque> getEstoques() {
		return estoques;
	}

	public void setEstoques(Set<Estoque> estoques) {
		this.estoques = estoques;
	}

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	public Set<Preco> getPrecos() {
		return precos;
	}

	public void setPrecos(Set<Preco> precos) {
		this.precos = precos;
	}

	public String getImagemBase64() {
		if((this.imagemBase64 == null) && (this.imagem != null)){
			return Base64.getEncoder().encodeToString(this.imagem);
		}else{
			return this.imagemBase64;
		}
	}

	public void setImagemBase64(String imagemBase64) {
		this.imagemBase64 = imagemBase64;
	}

	public Caracteristica getCaracteristica() {
		return caracteristica;
	}

	public void setCaracteristica(Caracteristica caracteristica) {
		this.caracteristica = caracteristica;
	}
	
	
	
}
