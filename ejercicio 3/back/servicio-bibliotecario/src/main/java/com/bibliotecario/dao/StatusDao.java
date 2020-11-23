package com.bibliotecario.dao;

import org.springframework.data.repository.CrudRepository;

import com.commons.models.StatusEntity;

public interface StatusDao extends CrudRepository<StatusEntity, Long> {

}
