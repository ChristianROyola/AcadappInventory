package com.arcd.inventory.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.arcd.inventory.modelo.Categoria;
import com.arcd.inventory.modelo.Producto;


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

	public Categoria leerCategoria(int id) {
		Categoria c = em.find(Categoria.class, id);
		return c;
	}

	public void eliminarCategoria(int id) {
		Categoria c = leerCategoria(id);
		em.remove(c);
	}

	public List<Categoria> getCategorias(){
		String jpql = "SELECT distinct c FROM Categoria c LEFT JOIN FETCH c.productos";
		Query query = em.createQuery(jpql, Categoria.class);
		List<Categoria> categorias = query.getResultList();
		return categorias;
	}
	
	
	public List<Categoria> getCategorias2()
	{
		String jpql = "SELECT distinct c FROM Categoria c LEFT JOIN FETCH c.productos";
		Query query = em.createQuery(jpql, Categoria.class);
		List<Categoria> categorias = query.getResultList();
		return categorias;
	}
	
	public List<Categoria> getCategoriaslist()
	{
		String jpql = "SELECT c.nombre FROM Categoria c";
		Query query = em.createQuery(jpql, Categoria.class);
		List<Categoria> categorias = query.getResultList();
		return categorias;
		
		//entityManager.createQuery("SELECT avg(responseEnd - responseStart)  FROM QualiteDeService q  join q.test",Double.class );
	}
	
	public String[] listaitemcategoria() {
		String sql = "Select e.nombre from Categoria e";
		TypedQuery<Categoria> query = em.createQuery(sql, Categoria.class);
		List<Categoria> categorias = query.getResultList();
		String[] stringArray = categorias.toArray(new String[0]);
		return stringArray;
	}
	
}
