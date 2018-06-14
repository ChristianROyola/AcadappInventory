package com.arcd.inventory.modelo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "InventoryProduct_Proveedor")
public class Product_Proveedor 
{
	@Id
	@Column(name = "prodvee_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
		
	@Column(name = "prodvee_descuento",precision=16, scale=2)
	private BigDecimal descuento;

	@Column(name = "prodvee_stock",precision=16, scale=0)
	private int stock;
	
	@Column(name = "prodvee_preciofinal",precision=16, scale=2)
	private BigDecimal preciofinal;

	@ManyToOne
	@JoinColumn(name="producto_id")
	//@ManyToOne to Productos
	private Producto product;
	
	@ManyToOne
	@JoinColumn(name="proveedor_id")
	//@ManyToOne to Productos
	private Producto proveed;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getDescuento() {
		return descuento;
	}

	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public BigDecimal getPreciofinal() {
		return preciofinal;
	}

	public void setPreciofinal(BigDecimal preciofinal) {
		this.preciofinal = preciofinal;
	}

	@Override
	public String toString() {
		return "Product_Proveedor [id=" + id + ", descuento=" + descuento + ", stock=" + stock + ", preciofinal="
				+ preciofinal + "]";
	}
}
