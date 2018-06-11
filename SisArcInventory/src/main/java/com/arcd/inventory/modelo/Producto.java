package com.arcd.inventory.modelo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "InventoryProducto")
public class Producto 
{
	@Id
	@Column(name = "product_id")
	@NotBlank(message = "Ingrese el id del producto")
	private String id;
	
	@Column(name = "product_nombre")
	@NotBlank(message = "Ingrese el nombre del producto")
	private String nombre;
	
	@Column(name = "product_descripcion")
	@NotBlank(message = "Ingrese la descripción del producto")
	private String descripcion;
	
	@Column(name = "product_preciounit", precision=16, scale=2)
	@NotNull(message = "Ingrese el precio unitario por favor")
	private BigDecimal preciounit;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="cat_id",nullable = false, insertable =true)
	private Categoria cate;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public BigDecimal getPreciounit() {
		return preciounit;
	}
	public void setPreciounit(BigDecimal preciounit) {
		this.preciounit = preciounit;
	}
	
	public Categoria getCate() {
		return cate;
	}
	public void setCate(Categoria cate) {
		this.cate = cate;
	}
	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", precio=" + preciounit
				+ "]";
	}
		
}
