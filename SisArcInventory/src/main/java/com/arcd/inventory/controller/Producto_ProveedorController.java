package com.arcd.inventory.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.arcd.inventory.dao.Producto_ProveedorDao;
import com.arcd.inventory.modelo.Product_Proveedor;
import com.arcd.inventory.modelo.Producto;

@ManagedBean
@SessionScoped
public class Producto_ProveedorController {

	@Inject
	private Producto_ProveedorDao prodveedao;

	private Product_Proveedor prodv;

	private int stock;
	
	 private List<Producto> filteredProducto;
	
	@PostConstruct
	public void init() {
		prodv = new Product_Proveedor();
	}

	public String guardar() {

		System.out.println(prodv);
		prodveedao.guardarprovd(prodv);
		//loadCategoria();
	    return null;
		//return "listadoCategoria";
	}
	
	public void actualizarStock(int stock)
	{
		this.stock = stock;
		//(realizar operacion para aumentar o disminuir el valor de stock en base a las solicitudes de uso)
	}

	public Product_Proveedor getProdv() {
		return prodv;
	}

	public void setProdv(Product_Proveedor prodv) {
		this.prodv = prodv;
	}

	public Producto_ProveedorDao getProdveedao() {
		return prodveedao;
	}

	public void setProdveedao(Producto_ProveedorDao prodveedao) {
		this.prodveedao = prodveedao;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public List<Producto> getFilteredProducto() {
		return filteredProducto;
	}

	public void setFilteredProducto(List<Producto> filteredProducto) {
		this.filteredProducto = filteredProducto;
	}	

}
