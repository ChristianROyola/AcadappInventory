package com.arcd.inventory.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Creacion de la tabla Personas
 * @author Compus
 *
 */
@Entity
@Table(name = "InventoryPers")
public class Persona 
{
	@Id
	@Column(name = "pers_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;

	@Column(name = "pers_nombre")
	@NotBlank(message = "Ingrese el nombre")
	private String nombre;

	@Column(name = "pers_apellido")
	@NotBlank(message = "Ingrese el apellido")
	private String apellido;

	@Column(name = "pers_cedula")
	@Pattern(regexp = "[\\s]*[0-9]*[1-9]+",message="Ingrese unicamente numeros")
	@NotBlank(message = "Por favor ingrese la cedula")
	private String cedula;

	@Column(name = "pers_correo")
	@NotBlank(message = "Ingrese el correo") 
	private String correo;

	@Column(name = "pers_perfil")
	private String perfil;

	@Column(name = "pers_contrasenia")
	@Size(min = 6, message = "Debe ingresar un minimo de 6 caracteres")
	private String contrasenia;

	@Column(name = "pers_estado")
	private String estado;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Persona [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", cedula=" + cedula
				+ ", correo=" + correo + ", perfil=" + perfil + ", contrasenia=" + contrasenia + ", estado=" + estado
				+ "]";
	}

}
