package com.bibliotecario.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


public class SolicitudDTO {
	
	private Long id;
	
	private String nombre_libro;
	
	private String nombre_libro_autor;
	
	private String status;
	
	private String nombre_lector;

	private String correo;

	
	
	public SolicitudDTO(Long id, String nombre_libro, String nombre_libro_autor, String status,
			String nombre_lector, String correo) {
		this.id = id;
		this.nombre_libro = nombre_libro;
		this.nombre_libro_autor = nombre_libro_autor;
		this.status = status;
		this.nombre_lector = nombre_lector;
		this.correo = correo;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre_libro() {
		return nombre_libro;
	}

	public void setNombre_libro(String nombre_libro) {
		this.nombre_libro = nombre_libro;
	}

	public String getNombre_libro_autor() {
		return nombre_libro_autor;
	}

	public void setNombre_libro_autor(String nombre_libro_autor) {
		this.nombre_libro_autor = nombre_libro_autor;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNombre_lector() {
		return nombre_lector;
	}

	public void setNombre_lector(String nombre_lector) {
		this.nombre_lector = nombre_lector;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	
	
}
