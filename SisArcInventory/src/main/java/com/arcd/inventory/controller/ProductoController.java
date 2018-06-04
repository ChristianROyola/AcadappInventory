package com.arcd.inventory.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import com.arcd.inventory.dao.CategoriaDao;
import com.arcd.inventory.dao.ProductoDao;
import com.arcd.inventory.modelo.Categoria;
import com.arcd.inventory.modelo.Producto;


@ManagedBean
@SessionScoped
public class ProductoController 
{
	@Inject
	private CategoriaDao catedao;
	
	@Inject
	private ProductoDao productodao;
	
	private Producto producto;
	
	private List<Producto> lproducto;
	
	//Busquedad de Productos
	private List<Producto> listadoFiltrado;

	private String filtro;
	
	private Categoria c;
	
	private String selectedDept;
	
	@PostConstruct
	public void init() 
	{
		producto = new Producto(); 
		c = new Categoria();
		loadProducto();
	}
	
	public void loadProducto() 
	{
		lproducto = productodao.listProductos();
	}
	
	/**
	 * Mantenimiento Controlladores del ProductoController
	 */
	
	public String loadProductoEdit(String id) {
		producto = productodao.leerProducto(id);
		//return "CrearEvento";
		return null;
	}
	
	public String insertar() {
		productodao.guardarProducto(producto);
		loadProducto();
		inicializar();
		//return "listarEventos";
		return null;
	}
	
	public String actualizar() {
		productodao.updateProducto(producto);
		return null;
	}
	
	public String leer(String id) {
		producto = productodao.leerProducto(id);
		return null;
	}
	public String eliminar(String id) {
		productodao.eliminarProducto(id);
		//return "eliminarEvento";
		return null;
	}
	  
	public List<Producto> listaProductos(){
		lproducto = productodao.listProductos();
		return lproducto;
	}
	
	public String buscar()
	{	
		System.out.println("INGRESO AL METODO ==================");
		listadoFiltrado = productodao.getProductosNombre(filtro);	
		return null;
	}
	
	public void inicializar() 
	{
		producto.setId("");
		producto.setNombre("");
		producto.setDescripcion("");
		producto.setPreciounit(null);
	}
	

	public CategoriaDao getCatedao() {
		return catedao;
	}

	public void setCatedao(CategoriaDao catedao) {
		this.catedao = catedao;
	}

	public ProductoDao getProductodao() {
		return productodao;
	}

	public void setProductodao(ProductoDao productodao) {
		this.productodao = productodao;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public List<Producto> getLproducto() {
		return lproducto;
	}

	public void setLproducto(List<Producto> lproducto) {
		this.lproducto = lproducto;
	}

	public List<Producto> getListadoFiltrado() {
		return listadoFiltrado;
	}

	public void setListadoFiltrado(List<Producto> listadoFiltrado) {
		this.listadoFiltrado = listadoFiltrado;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public Categoria getC() {
		return c;
	}

	public void setC(Categoria c) {
		this.c = c;
	}

	public String getSelectedDept() {
		return selectedDept;
	}

	public void setSelectedDept(String selectedDept) {
		this.selectedDept = selectedDept;
	}

}
