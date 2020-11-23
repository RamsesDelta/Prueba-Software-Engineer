package com.lector.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.commons.models.SolicitudEntity;



public interface SolicitudDao extends CrudRepository<SolicitudEntity, Long>{

	@Query("SELECT s FROM SolicitudEntity s "
			+ "INNER JOIN s.libro l "
			+ "INNER JOIN s.usuario u "
			+ "INNER JOIN s.status st "
			+ "WHERE "
			+ "(st.id = 1 OR  st.id = 2) "
			+ "AND l.id = ?1 "
			+ "AND u.id = ?2")
	SolicitudEntity findRequestByUserAndLib(Long idLibro, Long idUsuario);
	
}
