package com.administrador.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.commons.models.LibroEntity;



public interface LibrosDao extends CrudRepository<LibroEntity, Long>{

	@Query("SELECT l FROM LibroEntity l "
			+ "INNER JOIN l.genero g "
			+ "WHERE CONCAT(l.autor, l.fecha_publicacion, l.titulo,g.nombre_genero) LIKE %?1%")
	List<LibroEntity> search(String keyword); 
	
	
	@Query("SELECT l FROM LibroEntity l "
			+ "INNER JOIN l.genero g "
			+ "WHERE l.cantidad > 0")
	List<LibroEntity> bookdAviable(); 
	
}
