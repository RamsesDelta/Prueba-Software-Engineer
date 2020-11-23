package com.administrador.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.commons.models.UsuarioRolEntity;



public interface UserRolDao extends CrudRepository<UsuarioRolEntity, Long>{
	
	@Query("SELECT ur FROM UsuarioRolEntity ur "
			+ "INNER JOIN ur.usuario u "
			+ "WHERE u.id = ?1")
	UsuarioRolEntity findUsuerRol(Long id);
}
