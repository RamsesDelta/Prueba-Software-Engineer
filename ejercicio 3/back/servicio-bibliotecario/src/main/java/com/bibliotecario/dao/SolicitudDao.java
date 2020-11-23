package com.bibliotecario.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.commons.models.SolicitudEntity;



public interface SolicitudDao extends CrudRepository<SolicitudEntity, Long>{
	
	
	@Query("SELECT s FROM SolicitudEntity s "
			+ "INNER JOIN s.status st "
			+ "WHERE st.id = ?1")
	List<SolicitudEntity> findByRequestBook(Long idStatus); 
	
	
	@Query("SELECT s FROM SolicitudEntity s "
			+ "INNER JOIN s.status st "
			+ "WHERE st.id = 2")
	List<SolicitudEntity> findByGiveBook();
	
	@Query("SELECT s FROM SolicitudEntity s "
			+ "INNER JOIN s.status st INNER JOIN s.libro l  "
			+ "WHERE CONCAT(l.autor, l.fecha_publicacion, l.titulo,st.nombre_status) LIKE %?1%")
	List<SolicitudEntity> findFilter(String keyword);
	
	@Query("SELECT count(s) FROM SolicitudEntity s "
			+ "INNER JOIN s.status st INNER JOIN s.libro l "
			+ "WHERE l.id = ?1 AND st.id = ?2")
	int countStatusApproved(Long idBook, Long idStatus);
	
}
