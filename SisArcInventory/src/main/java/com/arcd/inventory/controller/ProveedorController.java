package com.arcd.inventory.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import com.arcd.inventory.dao.ProveedorDao;
import com.arcd.inventory.modelo.Proveedores;

@ManagedBean
@SessionScoped
public class ProveedorController {
	private int idrecuprerar; // -----------agregado
	private String coincidencia;
	private Date fecha;

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
		// Lproveedores = listaProveedores();
		// ListProvId = new ArrayList<Proveedores>();
		// consultaLocalEventos();
	}

	public void setIdrecuprerar(int idrecuprerar) {
		this.idrecuprerar = idrecuprerar;
		// loadid(idrecuprerar);
		// consultaLocalEventos();
	}

	/**
	 * Creacion del objeto Proveeddores condicinamiento segun las sentencias de
	 * validacion
	 */
	public void crear() {
		if (validarCorreo() == true) {
			proveedores.setFechregistro(fecha);
			pdao.guardar(proveedores);
			inicializar();
			init();
			this.coincidencia = "Grabado exitoso!";
		} else {
			this.coincidencia = "El formato del correo es incorrecto";
		}
	}

	public void cambioFecha(SelectEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		facesContext.addMessage(null,
				
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Fecha seleccionada", format.format(event.getObject())));
	}

	public void click() {
		PrimeFaces.current().ajax().update("form:display");
		PrimeFaces.current().executeScript("PF('dlg').show()");
	}

	/**
	 * Metodo para la validacion de un correo electronico
	 */
	public boolean validarCorreo() {
		String email = proveedores.getCorreo();
		Pattern pattern = Pattern.compile(PATTERN_EMAIL);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	/**
	 * Setea las variable como vacias, ocupado al momento de haber creado el usuario
	 * y dejar los h:inputText del JSF en blanco
	 **/
	public void inicializar() {
		proveedores.setNombre("");
		proveedores.setCorreo("");
		proveedores.setDireccion("");
		proveedores.setTelefono("");
	}

	/**
	 * Metodo listado, devuelve un objeto Listado de tipo Persona(Devuelve todas las
	 * personas)
	 */
	public List<Proveedores> listaProveedores() {
		Lproveedores = pdao.listProveedores();
		return Lproveedores;
	}

	public String getCoincidencia() {
		return coincidencia;
	}

	public void setCoincidencia(String coincidencia) {
		this.coincidencia = coincidencia;
	}

	public List<Proveedores> getListProvId() {
		return ListProvId;
	}

	public void setListProvId(List<Proveedores> listProvId) {
		ListProvId = listProvId;
	}

	public List<Proveedores> getLproveedores() {
		return Lproveedores;
	}

	public void setLproveedores(List<Proveedores> lproveedores) {
		Lproveedores = lproveedores;
	}

	public ProveedorDao getPdao() {
		return pdao;
	}

	public void setPdao(ProveedorDao pdao) {
		this.pdao = pdao;
	}

	public Proveedores getProveedores() {
		return proveedores;
	}

	public void setProveedores(Proveedores proveedores) {
		this.proveedores = proveedores;
	}

	public int getIdrecuprerar() {
		return idrecuprerar;
	}

	
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "ProveedorController [idrecuprerar=" + idrecuprerar + ", coincidencia=" + coincidencia + ", ListProvId="
				+ ListProvId + ", Lproveedores=" + Lproveedores + ", pdao=" + pdao + ", proveedores=" + proveedores
				+ "]";
	}


	
}
