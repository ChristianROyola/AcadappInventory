package com.arcd.inventory.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "InventoryCategoria")
public class Categoria {

	@Id
	@Column(name = "cat_id")
	private String catid;

	@Column(name = "cat_nombre")
	@NotBlank(message = "Ingrese la categoria")
	@Size(min = 3, max = 30)
	private String nombre;

	@Column(name = "cat_descripcion")
	private String descipcion;

	//bi-directional many-to-one association to Producto
	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	@JoinColumn(name="categoria_id", referencedColumnName="cat_id")
	private List<Producto> productos;
	
	public String getCatid() {
		return catid;
	}

	public void setCatid(String catid) {
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
	
	public void addProductos(Producto products)
	{
		if(productos==null)
			productos = new ArrayList<>();
		productos.add(products);
	}

	@Override
	public String toString() {
		return "Categoria [catid=" + catid + ", nombre=" + nombre + ", descipcion=" + descipcion + ", productos="
				+ productos + "]";
	}
	
}
