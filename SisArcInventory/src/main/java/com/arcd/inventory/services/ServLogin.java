package com.arcd.inventory.services;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.arcd.inventory.dao.PersonaDao;
import com.arcd.inventory.modelo.Persona;

@Path("/session")
public class ServLogin {
	@Inject
	private PersonaDao dao;

	@GET
	@Path("/login")
	@Produces("application/json")

	public List<Persona> getAdmin(@QueryParam("user") String email, @QueryParam("password") String pass) {
		RegistroRest res = new RegistroRest();
		List<Persona> admin = null;
		try {
			admin = dao.loginadmin(email, pass);
			res.setId(1);
			res.setDescripcion("Login Exitoso!!!");
		} catch (Exception e) {
			res.setId(0);
			res.setDescripcion("Error al Ingresar!!!");
		}
		return admin;
	}

	/*
	 * @POST
	 * 
	 * @Path("/guardar")
	 * 
	 * @Produces("application/json")
	 * 
	 * @Consumes("application/json") public Respuesta saveCategoria(Categoria cat) {
	 * Respuesta resp = new Respuesta(); try { dao.insert(cat); resp.setCodigo(1);
	 * resp.setMensaje("Registro satisfactorio"); }catch(Exception e) {
	 * resp.setCodigo(-1); resp.setMensaje("Error en registro"); } return resp; }
	 */

	@POST
	@Path("/register")
	@Produces("application/json")
	@Consumes("application/json")
	public RegistroRest register(Persona admin) {
		RegistroRest res = new RegistroRest();
		try {
			dao.guardar(admin);
			res.setId(1);
			res.setDescripcion("Registrado con Exito!!!");
		} catch (Exception e) {
			res.setId(0);
			res.setDescripcion("Error al Registrar!!!");
		}
		return res;
	}

	@GET
	@Path("/admins")
	@Produces("application/json")
	public List<AdminTemp> getAdmins() {
		List<Persona> admins = dao.listPersonas();
		List<AdminTemp> ads = new ArrayList<>();

		for (Persona ad : admins) {
			AdminTemp temp = new AdminTemp();
			temp.setId(ad.getCedula());
			temp.setNombre(ad.getNombre());
			temp.setApellido(ad.getApellido());
			temp.setCorreo(ad.getCorreo());
			temp.setEstado(ad.getEstado());
			temp.setPerfil(ad.getPerfil());

			ads.add(temp);
		}
		return ads;
	}

}
