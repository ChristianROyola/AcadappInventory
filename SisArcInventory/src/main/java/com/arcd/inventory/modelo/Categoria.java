package com.arcd.inventory.modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "InventoryCategoria")
public class Categoria {

	@Id
	@Column(name = "cat_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int catid;

	@Column(name = "cat_nombre")
	@NotBlank(message = "Ingrese la categoria")
	@Size(min = 3, max = 30)
	private String nombre;

	@Column(name = "cat_descripcion")
	private String descipcion;

	//bi-directional many-to-one association to Producto
	@OneToMany(mappedBy="categoria",
			 cascade = CascadeType.ALL, 
			 orphanRemoval = true)
	
	private List<Producto> productos;
	
	public int getCatid() {
		return catid;
	}

	public void setCatid(int catid) {
		this.catid = catid;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescipcion() {
		return descipcion;
	}

	public void setDescipcion(String descipcion) {
		this.descipcion = descipcion;
	}

	
	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	@Override
	public String toString() {
		return "Categoria [id=" + catid + ", nombre=" + nombre + ", descipcion=" + descipcion + "]";
	}

	
}
