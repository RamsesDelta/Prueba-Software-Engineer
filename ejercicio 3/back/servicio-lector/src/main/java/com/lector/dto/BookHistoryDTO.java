package com.lector.dto;

import java.time.LocalDate;

public class BookHistoryDTO {

	private String autor;
	
	private String titulo;
	
	private LocalDate fecha_publicacion;
	
	private String nombre_genero;
	
	public BookHistoryDTO(String autor, String titulo, LocalDate fecha_publicacion, String nombre_genero) {
		this.autor = autor;
		this.titulo = titulo;
		this.fecha_publicacion = fecha_publicacion;
		this.nombre_genero = nombre_genero;
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

	public String getNombre_genero() {
		return nombre_genero;
	}

	public void setNombre_genero(String nombre_genero) {
		this.nombre_genero = nombre_genero;
	}
	
}
