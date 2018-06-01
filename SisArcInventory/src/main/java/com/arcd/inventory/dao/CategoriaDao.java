package com.arcd.inventory.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.arcd.inventory.modelo.Categoria;


@Stateless
public class CategoriaDao {

	@Inject
	private EntityManager em;

	public void guardarCategoria(Categoria c) {
		Categoria aux = leerCategoria(c.getCatid());
		if (aux != null) {
			actualizarCategoria(c);
		} else {
			insetarCategoria(c);
		}
	}

	public Categoria read(int id){
		Categoria categoria = em.find(Categoria.class, id);
		categoria.getProductos().size();
		return categoria;
	}
	
	public void insetarCategoria(Categoria c) {
		em.persist(c);
	}

	public void actualizarCategoria(Categoria c) {
		em.merge(c);
	}

	public Categoria leerCategoria(String id) {
		Categoria c = em.find(Categoria.class, id);
		return c;
	}

	public void eliminarCategoria(String id) {
		Categoria c = leerCategoria(id);
		em.remove(c);
	}

	public List<Categoria> getCategorias(){
		String jpql = "SELECT distinct c FROM Categoria c LEFT JOIN FETCH c.productos";
		Query query = em.createQuery(jpql, Categoria.class);
		List<Categoria> categorias = query.getResultList();
		return categorias;
	}
	
	
	public List<Categoria> getCategorias2(){
		String jpql = "SELECT distinct c FROM Categoria c";
		Query query = em.createQuery(jpql, Categoria.class);
		List<Categoria> categorias = query.getResultList();
		return categorias;
	}
	
}
