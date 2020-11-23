package com.administrador.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.commons.models.UsuarioEntity;



public interface UserDao extends CrudRepository<UsuarioEntity, Long> {
	
	
	@Query("SELECT u FROM UsuarioEntity u "
			+ "WHERE u.correo =?1 ")
	UsuarioEntity findUsuer(String correo);
	
}
