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
			
			System.out.println("CategoriaDao -> guardarCategoria aux=! null");
			actualizarCategoria(c);
			//actualizarCategoria(c);
		} else {
			System.out.println("Persiste con categoria -----> "+c.getCatid());
			insetarCategoria(c);
		}
	}

	public Categoria read(int id){
		Categoria categoria = em.find(Categoria.class, id);
		categoria.getProductos().size();
		return categoria;
	}
	
	public void insetarCategoria(Categoria c) 
	{ 
		System.out.println("Recibe Categoria a persistir -----> "+c.getCatid()+" "+c.getNombre() +" "+ c.getDescipcion());
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
		
	public List<Categoria> getCategorias2()
	{
		String jpql = "SELECT distinct c FROM Categoria c LEFT JOIN FETCH c.productos";
		Query query = em.createQuery(jpql, Categoria.class);
		List<Categoria> categorias = query.getResultList();
		return categorias;
	}
	
	public List<Categoria> getCategoriaslist()
	{
		Query query = em.createQuery("SELECT c FROM Categoria c", Categoria.class);
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
	
	public List<Categoria> getCategoriasProduct(String id)
	{
		Query query = em.createQuery("SELECT c FROM Categoria c WHERE c.catid = "+id, Categoria.class);
		List<Categoria> categorias = query.getResultList();
		return categorias;
		//entityManager.createQuery("SELECT avg(responseEnd - responseStart)  FROM QualiteDeService q  join q.test",Double.class );
	}
}
