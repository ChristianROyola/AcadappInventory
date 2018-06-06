package com.arcd.inventory.dao;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.arcd.inventory.modelo.Product_Proveedor;

@Stateless
public class Producto_ProveedorDao {

	@Inject
	private EntityManager em;

	public void guardarprovd(Product_Proveedor e) {

		Product_Proveedor auxe = leer(e.getId());
		if (auxe != null) {
			update(e);
		} else {
			insertar(e);
		}
	}
	
	public void insertar(Product_Proveedor e) {
		em.persist(e);		
	}
	
	public Product_Proveedor leer(int id) {
		
		Product_Proveedor e = em.find(Product_Proveedor.class, id);
		return e;
	}
	
	//Metodo para Actualizar Evento
	public void update(Product_Proveedor e) {
		System.out.println("Updating............"+e.getStock()+
				e.getId());
		em.merge(e);
	} 
}
