package com.administrador.service;

import java.util.List;

import com.administrador.dto.LibroDTO;
import com.administrador.dto.UserDTO;
import com.commons.models.GeneroEntity;
import com.commons.models.LibroEntity;



public interface ILibros {
	
	void save(LibroDTO libro);
	
	List<LibroEntity> findAll();
	
	void deleteById(Long id);

	void updateLibro(LibroDTO libro,Long id);
	
	List<GeneroEntity> findAllGenero();
	
	List<LibroDTO> filter(String keyword);

	List<LibroEntity>findBooksAviable();
}
