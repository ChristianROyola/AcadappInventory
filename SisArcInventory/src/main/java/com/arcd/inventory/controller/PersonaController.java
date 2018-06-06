package com.arcd.inventory.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.hibernate.validator.constraints.NotBlank;

import com.arcd.inventory.dao.PersonaDao;
import com.arcd.inventory.modelo.Persona;
import com.arcd.inventory.utils.SessionUtils;

@ManagedBean
@SessionScoped
public class PersonaController
{
	/*
	 * Variable para la validacion de la cedula
	 */
	private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	private Persona personas = null;

	private int id;
	private String pactual;
	private int idEditUser;

	/**
	 * Definición de variables para la validacion-coincidencia del numero de cedula
	 * ingresado
	 */
	@NotBlank(message = "Ingrese las contrasenias")
	private String contrasenia;
	private String conincidencia;
	private String Loginexiste;

	/**
	 * Varibles donde almaceno los valores de la consulta maestro-detalles
	 */

	private String nusuario;
	private String nlocal;
	private String ndescripcion;
	private String ncapacidad;
	private String ncosto;

	private int idrecuprerar; // -----------agregado

	/**
	 * Definicion de variables para la recuperacion de la
	 * lista(Persona-Local-Evento)
	 */
	private List<Persona> ListPerID;

	@Inject
	private PersonaDao pdao;

	private List<Persona> lpersonas;

	private Persona myUser;

	@PostConstruct
	public void init() {
		personas = new Persona();
		//lpersonas = listaPersonas();
		ListPerID = new ArrayList<Persona>();
		//consultaLocalEventos();
	}

	/**
	 * Creación del objeto persona, condicionamiento segun las sentencias de
	 * valiacion
	 */

	public String crear() {
		if (coincidirContrasenia() == true) {
			if (validarCedula() == true) {
				if (validarCorreo() == true) {
					personas.setPerfil("USUARIO");
					personas.setEstado("A");
					pdao.guardar(personas);
					inicializar();
					init();
					this.conincidencia = "Grabado exitoso!";
					return "form-list-admis";
				} else {
					this.conincidencia = "El formato del correo es incorrecto";
					return null;
				}
			} else {
				System.out.println("Cedula incorrecta");
				this.conincidencia = "La cedula es incorrecta";
				return null;
			}
		} else {
			this.conincidencia = "Ingrese las mismas contrasenias";
			return null;
		}	
	}
	
	/**
	 * Contexto del Administrador
	 * @return
	 */
	
	public String crear2() {
		if (coincidirContrasenia() == true) {
			if (validarCedula() == true) {
				if (validarCorreo() == true) {
					//personas.setPerfil("USUARIO"); // cambiar por administrador
					personas.setEstado("A");
					pdao.guardar(personas);
					inicializar();
					init();
					this.conincidencia = "Grabado exitoso!";
					return "form-update-admin";
				} else {
					this.conincidencia = "El formato del correo es incorrecto";
					return null;
				}
			} else {
				System.out.println("Cedula incorrecta");
				this.conincidencia = "La cedula es incorrecta";
				return null;
			}
		} else {
			this.conincidencia = "Ingrese las mismas contrasenias";
			return null;
		}	
	}
	
	/**
	 * inicilizar una Sesion HTTP y establecimiento de parametros en session,
	 * FacesContext acceso tanto al contexto de JSF como HTTP
	 */
	
	public void iniciarSesion()
	{
		if (pdao.login(personas.getCorreo(), personas.getContrasenia()).size() != 0) {

			HttpSession session = SessionUtils.getSession();
			session.setAttribute("username",
					pdao.login(personas.getCorreo(), personas.getContrasenia()).get(0).getCorreo());
			session.setAttribute("perfil",
					pdao.login(personas.getCorreo(), personas.getContrasenia()).get(0).getPerfil());
			session.setAttribute("estado",
					pdao.login(personas.getCorreo(), personas.getContrasenia()).get(0).getEstado());
			this.Loginexiste = " ";
			FacesContext contex = FacesContext.getCurrentInstance();
			if (pdao.login(personas.getCorreo(), personas.getContrasenia()).get(0).getPerfil()
					.equals("USUARIO")) {

				System.out.println("CONTEXTO USER");
				try {
					contex.getExternalContext().redirect("index-blank.xhtml");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (pdao.login(personas.getCorreo(), personas.getContrasenia()).get(0).getPerfil()
					.equals("ADMIN-SUPER")) {
				// FacesContext contexAS= FacesContext.getCurrentInstance();
				try {
					contex.getExternalContext().redirect("index.xhtml");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (pdao.login(personas.getCorreo(), personas.getContrasenia()).get(0).getPerfil()
					.equals("ADMIN")) {
				// FacesContext contexAS= FacesContext.getCurrentInstance();
				System.out.println("CONTEXTO ADMINN");
				try {
					contex.getExternalContext().redirect("index.xhtml");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		personas.setCorreo("");
		personas.setContrasenia("");
		this.Loginexiste = "El usuario o la contrasenia son incorrectos";
	}
	

	/**
	 * Comparación de los 2 campos referentes a la contrasenia,
	 * devolución(true/false), segun sea la cédula valida o no valida
	 * respectivamente.
	 * 
	 * @return
	 */
	public boolean coincidirContrasenia() {
		if (personas.getContrasenia().equals(this.contrasenia)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Setea las variable como vacias, ocupado al momento de haber creado el usuario
	 * y dejar los h:inputText del JSF en blanco
	 */
	public void inicializar() {
		personas.setCedula("");
		personas.setApellido("");
		personas.setNombre("");
		personas.setContrasenia("");
		
	}

	/*
	 * Modificacion de los objetos de tipo Persona(USUARIO/ADMIN)
	 */
	public String modificar() {
		try {
			System.out.println(personas.getPerfil());
			if (myUser.getPerfil().equals("USUARIO")) {
				personas.setContrasenia(pactual);
				//pdao.updatePersona(personas);
				//return "mainUser";
				return null;
			} else if (myUser.getPerfil().equals("ADMIN")) {
				personas.setContrasenia(pactual);
				System.out.println("ACTUALIZAR ADMIN :" + personas.getCedula());
				System.out.println("ELSE IF ADMIN");
				pdao.updatePersona(personas);
				//return "mainAdmin";
				return null;
				
			} else if (myUser.getPerfil().equals("ADMIN-SUPER")) {
				personas.setContrasenia(pactual);
				System.out.println("ACTUALIZAR ADMIN :" + personas.getCedula());
				System.out.println("ELSE IF ADMIN");
				//pdao.updatePersona(personas);
				//return "pages-blank";
				return null;
			}
			return null;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Metodo util en la validación de la cedula
	 */
	public boolean validarCedula() {
		String ced = personas.getCedula();
		int sum_t = 0;
		int res = 0;
		for (int i = 0; i < 9; i++) {
			char b = ced.charAt(i);
			int a = b - 48;
			if (i == 0) {
				a = a * 2;
			} else {
				if (i % 2 == 0) {
					a = a * 2;
				} else {
					a = a * 1;
				}
			}
			if (a > 9) {
				a = a - 9;
			}
			sum_t = sum_t + a;
		}
		res = sum_t % 10;
		if (res != 0) {
			res = 10 - res;
		}
		boolean resultado = false;
		if (res == Integer.parseInt(ced.substring(9, 10))) {
			resultado = true;
		} else {
			resultado = false;
		}
		return resultado;
	}

	/**
	 * Método para la validación de un correo electronico
	 */
	public boolean validarCorreo() {
		String email = personas.getCorreo();
		Pattern pattern = Pattern.compile(PATTERN_EMAIL);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	
	public String eliminar(int id) {
		pdao.deletePersona(id);
		System.out.println("Eliminado admin ..:" + personas);
		return "actualizar";
	}

	/**
	 * Metodo listado, devuelve un objeto Listado de tipo Persona(Devuelve todas las
	 * personas)
	 */
	public List<Persona> listaPersonas() {
		lpersonas = pdao.listPersonas();
		return lpersonas;
	}

	public Persona getPersonas() {
		return personas;
	}

	public void setPersonas(Persona personas) {
		this.personas = personas;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPactual() {
		return pactual;
	}

	public void setPactual(String pactual) {
		this.pactual = pactual;
	}

	public int getIdEditUser() {
		return idEditUser;
	}

	public void setIdEditUser(int idEditUser) {
		this.idEditUser = idEditUser;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getConincidencia() {
		return conincidencia;
	}

	public void setConincidencia(String conincidencia) {
		this.conincidencia = conincidencia;
	}

	public String getLoginexiste() {
		return Loginexiste;
	}

	public void setLoginexiste(String loginexiste) {
		Loginexiste = loginexiste;
	}

	public String getNusuario() {
		return nusuario;
	}

	public void setNusuario(String nusuario) {
		this.nusuario = nusuario;
	}

	public String getNlocal() {
		return nlocal;
	}

	public void setNlocal(String nlocal) {
		this.nlocal = nlocal;
	}

	public String getNdescripcion() {
		return ndescripcion;
	}

	public void setNdescripcion(String ndescripcion) {
		this.ndescripcion = ndescripcion;
	}

	public String getNcapacidad() {
		return ncapacidad;
	}

	public void setNcapacidad(String ncapacidad) {
		this.ncapacidad = ncapacidad;
	}

	public String getNcosto() {
		return ncosto;
	}

	public void setNcosto(String ncosto) {
		this.ncosto = ncosto;
	}

	public int getIdrecuprerar() {
		return idrecuprerar;
	}

	public void setIdrecuprerar(int idrecuprerar) {
		this.idrecuprerar = idrecuprerar;
	}

	public List<Persona> getListPerID() {
		return ListPerID;
	}

	public void setListPerID(List<Persona> listPerID) {
		ListPerID = listPerID;
	}

	public PersonaDao getPdao() {
		return pdao;
	}

	public void setPdao(PersonaDao pdao) {
		this.pdao = pdao;
	}

	public List<Persona> getLpersonas() {
		return lpersonas;
	}

	public void setLpersonas(List<Persona> lpersonas) {
		this.lpersonas = lpersonas;
	}

	public Persona getMyUser() {
		return myUser;
	}

	public void setMyUser(Persona myUser) {
		this.myUser = myUser;
	}

	/**
	 * Obtencion de la lista maestra (Persona)
	 *
	public String consultaLocalEventos() {

		System.out.println("ID: " + idrecuprerar + " " + "ENTRA");
		ListPerID = pdao.listPersonaID(idrecuprerar);
		for (Persona p : ListPerID) {
			System.out.println("CED====================================" + p.getCedula());
			nusuario = p.getNombre();
			nlocal = p.getLocales().get(0).getNombre();
			ndescripcion = p.getLocales().get(0).getDescripcion();
			ncapacidad = p.getLocales().get(0).getCapacidad();
			ncosto = p.getLocales().get(0).getCosto();
		}
		return null;
	}
	*/
	
	/**
	 * Metodo Utilizado para la verificacion de la sesion establecida, con un respectivo contexto hacia la pagina de inicio 
	
	 public void verificaSesion()
	 {
		 HttpSession session = SessionUtils.getSession();
			String nusv = (String) session.getAttribute("username");
				if(nusv!=null){
					System.out.println("si tiene sesion");
					FacesContext contex = FacesContext.getCurrentInstance();
			        try {
			        	if(myUser.getPerfil().equals("USUARIO")) {
			        		contex.getExternalContext().redirect( "mainUser.html" );	
			        	}else if(myUser.getPerfil().equals("ADMIN")){
			        		contex.getExternalContext().redirect( "mainAdmin.html" );
			        	} 
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
	 }
	  */
	
	
}
