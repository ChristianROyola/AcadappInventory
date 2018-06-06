package com.arcd.inventory.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.arcd.inventory.modelo.Persona;

@Stateless
public class PersonaDao 
{
	@Inject
	private EntityManager em;
	
	/**
	 *Metodo para Insertar
	 */
	public void insertPersona(Persona p) {
		em.persist(p);
	}

	/**
	 *Metodo para Actualizar
	 */
	public void updatePersona(Persona p) {
		System.out.println("Updating..."+p.getId()+p.getCorreo()+p.getContrasenia());
		em.merge(p);
	}
	
	/**
	 *Metodo para buscar
	 */
	public Persona selectPersona(int id) {
		Persona p = em.find(Persona.class, id);
		return p;
	}

	/**
	 *Metodo para eliminar
	 */
	public void deletePersona(int id) {
		Persona p = selectPersona(id);
		em.remove(p);
	}

	/**
	 *SQl consulta a la base
	 */
	public List<Persona> listPersonas() {
		String sql = "select p from Persona p";
		TypedQuery<Persona> query = em.createQuery(sql, Persona.class);
		System.out.println("2");
		List<Persona> lpersonas = query.getResultList();
		return lpersonas;
	}
	
	public void guardar (Persona p) {
		Persona aux = selectPersona(p.getId());
		System.out.println("ID GUARDAR:" +p.getId());
		if(aux!=null) {
			updatePersona(p);
		}else {
			System.out.println("Grabando!");
			insertPersona(p);	
		}
	}
	
	public List<Persona> login(String user, String pass) {
		System.out.println("USUARIO: "+user+", Pass: "+pass);
		String sql = "Select p from Persona p WHERE p.correo = '"+user+"' AND p.contrasenia='"+pass+"'";
		TypedQuery<Persona> query = em.createQuery(sql, Persona.class);
		List<Persona> personas = query.getResultList();
		return personas;
	}
	
	public List<Persona> verificaCorreo(String user)
	{
		String sql="Select p from Persona p WHERE p.correo = '"+user+"'";
		TypedQuery<Persona> query=em.createQuery(sql,Persona.class);
		List<Persona>personas=query.getResultList();
		return personas;
	}
	
	/**
	 * Recuperar lista de personas por el id enviado
	 */
	
	public List<Persona> listPersonaID(int id){
		String sql = "Select p from Persona p WHERE p.id = '"+id+"'";
		TypedQuery<Persona> query = em.createQuery(sql, Persona.class);
		List<Persona>personas= query.getResultList();
		return personas;
	}
	
}
