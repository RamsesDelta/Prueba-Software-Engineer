package com.bibliotecario.dto;

import java.time.LocalDate;

public class LibroDTO {
	
	private Long id_libro;
	
	private String autor;
	
	private String titulo;
	
	private LocalDate fecha_publicacion;

	private String genero;
	
	private String nombre_lector;
	
	private String correo;

	public LibroDTO(Long id_libro, String autor, String titulo, LocalDate fecha_publicacion, String genero,
			String nombre_lector, String correo) {
		this.id_libro = id_libro;
		this.autor = autor;
		this.titulo = titulo;
		this.fecha_publicacion = fecha_publicacion;
		this.genero = genero;
		this.nombre_lector = nombre_lector;
		this.correo = correo;
	}

	public Long getId_libro() {
		return id_libro;
	}

	public void setId_libro(Long id_libro) {
		this.id_libro = id_libro;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public LocalDate getFecha_publicacion() {
		return fecha_publicacion;
	}

	public void setFecha_publicacion(LocalDate fecha_publicacion) {
		this.fecha_publicacion = fecha_publicacion;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
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
