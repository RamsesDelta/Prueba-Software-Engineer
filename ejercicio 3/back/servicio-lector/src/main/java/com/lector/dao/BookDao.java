package com.lector.dao;

import org.springframework.data.repository.CrudRepository;

import com.commons.models.LibroEntity;



public interface BookDao extends CrudRepository<LibroEntity, Long>{

}
