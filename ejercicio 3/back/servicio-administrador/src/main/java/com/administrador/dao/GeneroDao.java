package com.administrador.dao;

import org.springframework.data.repository.CrudRepository;

import com.commons.models.GeneroEntity;

public interface GeneroDao extends CrudRepository<GeneroEntity, Long> {
	
}
