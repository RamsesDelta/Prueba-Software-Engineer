package com.lector.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.commons.models.SolicitudEntity;
import com.commons.models.UsuarioEntity;

public interface UserDao extends CrudRepository<UsuarioEntity, Long> {
	
	@Query("SELECT u FROM UsuarioEntity u "
			+ "WHERE u.correo =?1 ")
	UsuarioEntity findReader(String correo);
	
}
