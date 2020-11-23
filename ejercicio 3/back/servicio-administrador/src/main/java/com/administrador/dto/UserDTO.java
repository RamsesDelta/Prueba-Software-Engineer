package com.administrador.dto;

import java.time.LocalDate;

public class UserDTO {
	
	private Long id;
	
	private String nombre;
	
	private String correo;
	
	private LocalDate fecha_nacimiento; 
	
	private int enable;
	
	private Long rol;
	
	private String nombre_rol;

	public UserDTO(Long id, String nombre, String correo, LocalDate fecha_nacimiento, int enable, Long rol,
			String nombre_rol) {
		this.id = id;
		this.nombre = nombre;
		this.correo = correo;
		this.fecha_nacimiento = fecha_nacimiento;
		this.enable = enable;
		this.rol = rol;
		this.nombre_rol = nombre_rol;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public LocalDate getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public int getEnable() {
		return enable;
	}

	public void setEnable(int enable) {
		this.enable = enable;
	}

	public Long getRol() {
		return rol;
	}

	public void setRol(Long rol) {
		this.rol = rol;
	}

	public String getNombre_rol() {
		return nombre_rol;
	}

	public void setNombre_rol(String nombre_rol) {
		this.nombre_rol = nombre_rol;
	}
	
}
