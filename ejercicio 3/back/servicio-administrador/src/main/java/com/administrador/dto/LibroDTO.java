package com.administrador.dto;

import java.time.LocalDate;

public class LibroDTO {
	
	private Long id;
	
	private String autor;
	
	private String titulo;
	
	private LocalDate fecha_publicacion;
	
	private int cantidad;

	private Long genero;

	public LibroDTO(Long id, String autor, String titulo, LocalDate fecha_publicacion, int cantidad, Long genero) {
		this.id = id;
		this.autor = autor;
		this.titulo = titulo;
		this.fecha_publicacion = fecha_publicacion;
		this.cantidad = cantidad;
		this.genero = genero;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Long getGenero() {
		return genero;
	}

	public void setGenero(Long genero) {
		this.genero = genero;
	}
}
