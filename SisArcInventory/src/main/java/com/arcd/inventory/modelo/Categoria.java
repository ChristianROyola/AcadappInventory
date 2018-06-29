package com.arcd.inventory.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	@NotBlank(message = "Ingrese el id de la categoria")
	private String catid;

	@Column(name = "cat_nombre")
	@NotBlank(message = "Ingrese el nombre de la categoria")
	@Size(min = 3, max = 30)
	private String nombre;

	@Column(name = "cat_descripcion")
	private String descipcion;

	//bi-directional one-to-many association to Producto
	@OneToMany(mappedBy="cate", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
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

	/**
	 * Asignacion de Categoria Producto
	 * @param products
	 */
	public void addProductos(Producto products)
	{
		if(productos==null)
			productos = new ArrayList<>();
			products.setCate(this);  //Cuando los foren se setean como nulos se debe manejar la relación java entre A y B, es decir, establecer 'a' en B en el método addB (b). 
			productos.add(products); 
	}

	@Override
	public String toString() {
		return "Categoria [catid=" + catid + ", nombre=" + nombre + ", descipcion=" + descipcion + ", productos="
				+ productos + "]";
	}
	
}
