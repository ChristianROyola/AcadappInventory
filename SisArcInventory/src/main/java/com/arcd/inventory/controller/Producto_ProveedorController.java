package com.arcd.inventory.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import com.arcd.inventory.dao.Producto_ProveedorDao;
import com.arcd.inventory.modelo.Product_Proveedor;

@ManagedBean
@SessionScoped
public class Producto_ProveedorController {

	@Inject
	private Producto_ProveedorDao prodveedao;

	private Product_Proveedor prodv;

	private int stock;
	
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

}
