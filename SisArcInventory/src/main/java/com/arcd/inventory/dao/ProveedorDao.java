package com.arcd.inventory.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.arcd.inventory.modelo.Proveedores;


@Stateless
public class ProveedorDao 
{
	@Inject
	private EntityManager em;
	
	/**
	 *Metodo para buscar
	 */
	public Proveedores selectPersona(int id) 
	{
		Proveedores p = em.find(Proveedores.class, id);
		return p;
	}
	
	/**
	 *Metodo para Insertar
	 */
	public void insertPersona(Proveedores p) {
		em.persist(p);
	}

	public void guardar (Proveedores p) {
		Proveedores aux = selectPersona(p.getId());
		System.out.println("ID GUARDAR:" +p.getId());
		if(aux!=null) {
			//updatePersona(p);
		}else {
			System.out.println("Grabando!");
			insertPersona(p);	
		}
	}
		
	/**
	 *SQl consulta a la base
	 */
	public List<Proveedores> listProveedores() {
		String sql = "select p from Persona p";
		TypedQuery<Proveedores> query = em.createQuery(sql, Proveedores.class);
		System.out.println("2");
		List<Proveedores> lpersonas = query.getResultList();
		return lpersonas;
	}
	
}
