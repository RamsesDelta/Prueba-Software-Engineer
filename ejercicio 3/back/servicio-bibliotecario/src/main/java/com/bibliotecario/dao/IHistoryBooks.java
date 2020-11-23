package com.bibliotecario.dao;

import org.springframework.data.repository.CrudRepository;

import com.commons.models.HistorialLibros;

public interface IHistoryBooks extends CrudRepository<HistorialLibros, Long> {

}
