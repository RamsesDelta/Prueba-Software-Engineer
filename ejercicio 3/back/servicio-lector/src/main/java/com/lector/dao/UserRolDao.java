package com.lector.dao;

import org.springframework.data.repository.CrudRepository;

import com.commons.models.UsuarioRolEntity;



public interface UserRolDao extends CrudRepository<UsuarioRolEntity, Long>{
	
}
