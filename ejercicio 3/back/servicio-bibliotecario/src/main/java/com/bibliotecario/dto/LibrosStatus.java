package com.bibliotecario.dto;

import java.time.LocalDate;

public class LibrosStatus {
	
	private String autor;
	
	private String titulo;
	
	private LocalDate fecha_publicacion;

	private String genero;
	
	private String status;

	public LibrosStatus(String autor, String titulo, LocalDate fecha_publicacion, String genero, String status) {
		this.autor = autor;
		this.titulo = titulo;
		this.fecha_publicacion = fecha_publicacion;
		this.genero = genero;
		this.status = status;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
