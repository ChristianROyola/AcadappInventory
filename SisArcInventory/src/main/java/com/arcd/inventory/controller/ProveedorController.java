package com.arcd.inventory.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import com.arcd.inventory.dao.ProveedorDao;
import com.arcd.inventory.modelo.Proveedores;


@ManagedBean
@SessionScoped
public class ProveedorController {
	private int idrecuprerar; // -----------agregado
	private String coincidencia;

	/**
	 * DEFINICION DE VARIABLES PARA LA RECUPERACION DE LA
	 * LISTA(PERSONA-LOCAL-EVENTO)
	 */
	private List<Proveedores> ListProvId;
	private List<Proveedores> Lproveedores;

	@Inject
	private ProveedorDao pdao;

	private Proveedores proveedores;

	@PostConstruct
	public void init() {
		proveedores = new Proveedores();
		Lproveedores = listaProveedores();
		ListProvId = new ArrayList<Proveedores>();
		// consultaLocalEventos();
	}

	public void setIdrecuprerar(int idrecuprerar) {
		this.idrecuprerar = idrecuprerar;
		// loadid(idrecuprerar);
		// consultaLocalEventos();
	}

	/**
	 * Creacion del objeto Persona condicinamiento segun las sentencias de
	 * validacion
	 */
	public void crear()
	{
		if (validarCorreo() == true) {
			pdao.guardar(proveedores);
			inicializar();
			init();
			this.coincidencia = "Grabado exitoso!";
		} else {
			this.coincidencia = "El formato del correo es incorrecto";
		}
	}
	
	/**
	 * Metodo para la validacion de un correo electronico
	 * */
	public boolean validarCorreo() {
		String email = proveedores.getCorreo();
		Pattern pattern = Pattern.compile(PATTERN_EMAIL);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
	}
	
	private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";   
	
	/**
	 * Setea las variable como vacias, ocupado al momento de haber creado el usuario y dejar los h:inputText del JSF en blanco
	 **/
	public void inicializar() {
		proveedores.setNombre("");
		proveedores.setCorreo("");
		proveedores.setDireccion("");
		proveedores.setTelefono("");	
	}
	

	/**
	 * Metodo listado, devuelve un objeto Listado de tipo Persona(Devuelve todas las personas)
	 */
	public List<Proveedores> listaProveedores() {
		Lproveedores = pdao.listProveedores();
		return Lproveedores;
	}

}
