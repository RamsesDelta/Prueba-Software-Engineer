package com.lector.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.commons.models.HistorialLibros;
import com.commons.models.SolicitudEntity;

public interface IHistoryBooks extends CrudRepository<HistorialLibros, Long> {

	@Query("SELECT h FROM HistorialLibros h "
			+ "INNER JOIN h.usuario u "
			+ "WHERE u.id = ?1")
	List<HistorialLibros> findReaderHistory(Long id);
	
	

}
