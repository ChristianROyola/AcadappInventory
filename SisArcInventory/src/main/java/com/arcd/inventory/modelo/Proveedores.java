package com.arcd.inventory.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Creacion de la tabla Proveedores
 * @author Compus
 *
 */
@Entity
@Table(name = "InventoryProveedor")
public class Proveedores 
{
	@Id
	@Column(name = "prov_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	
	@Column(name = "prov_nombre")
	@NotBlank(message = "Ingrese nombre de proveedor")
	private String nombre;
	
	@Column(name = "prov_direccion")
	@NotBlank(message = "Ingrese dirección de proveedor")
	private String direccion;
	
	@Column(name = "prov_telefono")
	@NotBlank(message = "Ingrese telefono de proveedor") 
	private String telefono;
	
	@Column(name = "prov_correo")
	@NotBlank(message = "Ingrese correo de proveedor")
	private String correo;
		
	@Column(name = "prov_fecharegistro")
	@NotNull(message = "Ingrese fecha de registro de proveedor")
	private Date fechregistro;
	
	@Column(name = "prov_comentario")
	private String descripcion;

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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Date getFechregistro() {
		return fechregistro;
	}

	public void setFechregistro(Date fechregistro) {
		this.fechregistro = fechregistro;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Proveedores [id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + ", telefono=" + telefono
				+ ", correo=" + correo + ", fechregistro=" + fechregistro + ", descripcion=" + descripcion + "]";
	}
}
