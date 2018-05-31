package com.arcd.inventory.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.Query;

import com.arcd.inventory.modelo.Producto;


@Stateless
public class ProductoDao {

	@Inject
	private EntityManager em;

	/**
	 * Metodo para editar o almacenar un producto
	 * 
	 * @param e
	 */
	public void guardarProducto(Producto e) {
		Producto auxe = leerProducto(e.getId());
		if (auxe != null) {
			updateProducto(e);
		} else {
			insertarProducto(e);
		}
	}
	
	public void insertarProducto(Producto e) {
		em.persist(e);		
	}

	/**
	 * Metodo para Leer productos
	 */

	public Producto leerProducto(int id) {
		Producto e = em.find(Producto.class, id);
		return e;
	}

	/**
	 * Metodo para actualizar los productos (Complementar)
	 * 
	 * @param e
	 */
	public void updateProducto(Producto e) {
		System.out.println("Updating............" + e.getId() + e.getNombre());
		em.merge(e);
	}

	/**
	 * Metodo para eliminar un producto
	 * 
	 * @param id
	 */
	public void eliminarProducto(int id) {
		Producto e = leerProducto(id);
		em.remove(e);
	}

	/**
	 * Metodo para la lista de los productos
	 * @return
	 */
	public List<Producto> listProductos() {
		String sql = "Select e from Producto e";
		TypedQuery<Producto> query = em.createQuery(sql, Producto.class);
		List<Producto> lproductos = query.getResultList();
		return lproductos;
	}

	/**
	 * Filtro de busqueda por productos
	 * @param filtro
	 * @return lista de productos
	 */
	public List<Producto> getProductosNombre(String filtro) {
		String sql = "SELECT p FROM Producto p " + "WHERE nombre like ? ";
		
		Query q = em.createQuery(sql, Producto.class);
		q.setParameter(1, "%" + filtro + "%");
		List<Producto> producto = q.getResultList();
		return producto;

	}

}
