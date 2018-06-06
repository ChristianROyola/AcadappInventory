package com.arcd.inventory.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import com.arcd.inventory.dao.CategoriaDao;
import com.arcd.inventory.modelo.Categoria;
import com.arcd.inventory.modelo.Producto;

@ManagedBean
@SessionScoped
public class CategoriaController {
	
	@Inject
	private CategoriaDao catedao;
	
	private Categoria categoria = null;
	private List<Categoria> lcategorias;
	
    private SelectItem[] categoriaSelectItems;
    private String categitem;
    
    private String id;
		
	@PostConstruct
	public void init(){
		categoria=new Categoria();
		//categoria.addProductos(new Producto());
		loadCategoria();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
		loadCategoriaEditar(id);//parametros
	}
	
	public SelectItem[] getCategoriaSelectItems() {
		return categoriaSelectItems;
	}

	public void setCategoriaSelectItems(SelectItem[] categoriaSelectItems) {
		this.categoriaSelectItems = categoriaSelectItems;
	}

	public String getCategitem() {
		return categitem;
	}

	public void setCategitem(String categitem)
	{
		this.categitem = categitem;
		//createCategoriaSelectItems();
        //Logger.info(String.format("Selected country: %s", categitem));
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public List<Categoria> getCategorias() {
		return lcategorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.lcategorias = categorias;
	}

	public void loadCategoria(){
		lcategorias = catedao.getCategorias2();
	}
	
	public List<Categoria> listaCategorias(){
		lcategorias = catedao.getCategorias2();
		return lcategorias;
	}
	
	public String loadCategoriaEditar(String id){
		
		System.out.println("Cargando datos de categoria a editar" + id);
		categoria = catedao.leerCategoria(id);
		//return "listadoCategoriaAcciones";	
		return null;
	}
	
	public String eliminaCategoria(String id){
		catedao.eliminarCategoria(id);
		//return "listadoCategoriaAcciones";
		return null;
	}
	
	public String guardar()
	{
		System.out.println("RECUPEDANDO ------->>>>> "+categoria);	
		try {
			
			if (this.id!=null) 
			{
				System.out.println("UPDATEEEE  ------->>>>> "+categoria);	
				catedao.actualizarCategoria(categoria);
			} else {
				System.out.println("INSERTAAAA  ------->>>>> "+categoria);
				catedao.insetarCategoria(categoria);
				inicializar();
			}
		} catch (Exception e) 
		{
			return null;
			// TODO: handle exception
		}
		//catedao.guardarCategoria(categoria);
		
		//inicializar();
		//loadCategoria();
		//return null;
		//return "listadoCategoria";
		return null;
	}
	
	public String addProdducto()
	{
		categoria.addProductos(new Producto());
		return null;
	}
	
	public void inicializar() 
	{
		categoria.setCatid("");
		categoria.setNombre("");
		categoria.setDescipcion("");
	}

	/*
	private void createCategoriaSelectItems(){
        String[] categorias = catedao.listaitemcategoria();
        if (categorias != null && categorias.length > 0) {
        	categitem = categorias[0];
        	categoriaSelectItems = new SelectItem[categorias.length];
            for (int i = 0; i < categorias.length; i++)
            	categoriaSelectItems[i] = new SelectItem(categorias[i], categorias[i]);
        } else {
        	categitem = "";
        	categoriaSelectItems = new SelectItem[0];
        }
    }*/
	
	public List<Categoria> getAllDepts() {
		//doList();
		return catedao.getCategorias2();
        
    }
	/*
	public String doList() {
		List<Categoria> categorias = catedao.getCategorias();
		for (Categoria categ : categorias) {
			System.out.println(categ.toString());
		}
		return null;
	}
	
	/*
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
	*/
}
