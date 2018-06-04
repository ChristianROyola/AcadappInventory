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

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "InventoryProducto")
public class Producto 
{
	@Id
	@Column(name = "product_id")
	private String id;
	
	@Column(name = "product_nombre")
	@NotBlank(message = "Ingrese el nombre del producto")
	private String nombre;
	
	@Column(name = "product_descripcion")
	@NotBlank(message = "Ingrese la descripción del producto")
	private String descripcion;
	
	@Column(nullable=false, precision=65)
	private BigDecimal preciounit;
		
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

	@Override
    public int hashCode() {
        return 31;
    }
	
	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", precio=" + preciounit
				+ "]";
	}
		
}
