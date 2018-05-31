package com.arcd.inventory.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import com.arcd.inventory.dao.CategoriaDao;
import com.arcd.inventory.modelo.Categoria;
import com.arcd.inventory.modelo.Producto;

@ManagedBean
public class CategoriaController {
	
	@Inject
	private CategoriaDao catedao;
	
	private Categoria categoria = null;
	private List<Categoria> categorias;
    private List<Producto> productos = new ArrayList<>();
	
	private int id;
		
	@PostConstruct
	public void init(){
		categoria=new Categoria();
		loadCategoria();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
		loadCategoriaEditar(id);//parametros
	}
	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public void loadCategoria(){	
		categorias = catedao.getCategorias();
	}
		
	public String loadCategoriaEditar(int id){
		
		System.out.println("Cargando datos de categoria a editar" + id);
		categoria = catedao.leerCategoria(id);
		//return "listadoCategoriaAcciones";	
		return null;
	}
	
	public String eliminaCategoria(int id){
		catedao.eliminarCategoria(id);
		//return "listadoCategoriaAcciones";
		return null;
	}
	
	public String guardar(){
		System.out.println(categoria);	
		catedao.guardarCategoria(categoria);
		inicializar();
		//loadCategoria();
		//return null;
		//return "listadoCategoria";
		return null;
	}
	
	public void inicializar() {
		categoria.setNombre("");
		categoria.setDescipcion("");
	}
	
	public Producto addProducto(Producto producto) {
		productos.add(producto);
		producto.setCategoria(new Categoria());
		return producto;
	}
	
	public Producto removeProducto(Producto producto) {
		productos.remove(producto);
		producto.setCategoria(null);
		return producto;
	}
	
}
