package com.administrador.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.administrador.dao.GeneroDao;
import com.administrador.dao.LibrosDao;
import com.administrador.dto.LibroDTO;
import com.commons.models.GeneroEntity;
import com.commons.models.LibroEntity;


@Service
public class LibrosImpl implements ILibros {
	
	private static Logger log = LoggerFactory.getLogger(LibrosImpl.class);
	
	@Autowired
	private LibrosDao libroDao;
	
	@Autowired
	private GeneroDao generoDao;
	
	@Override
	public void save(LibroDTO libro) {
		
		log.info("dentro del metodo del inser del libro "+libro.getGenero());
		
		GeneroEntity genero =	generoDao.findById(libro.getGenero()).orElse(null);
		
		LibroEntity libEntity = new LibroEntity();

		libEntity.setAutor(libro.getAutor());
		libEntity.setFecha_publicacion(libro.getFecha_publicacion());
		libEntity.setCantidad(libro.getCantidad());
		libEntity.setTitulo(libro.getTitulo());
		libEntity.setGenero(genero);

		libroDao.save(libEntity);
	}

	@Override
	public List<LibroEntity> findAll() {
		
		return (List<LibroEntity>) libroDao.findAll();
	/*	
		return lsLibros.stream().map(enti -> new LibroDTO(
				enti.getId(),
				enti.getAutor(),
				enti.getTitulo(),
				enti.getFecha_publicacion(),
				enti.getCantidad(),
				enti.getGenero().getNombre_genero()
				)).collect(Collectors.toList());*/
	}

	@Override
	public void deleteById(Long id) {
		
		LibroEntity bookEntity = libroDao.findById(id).orElse(null);
		
		bookEntity.setCantidad(0);
		
		libroDao.save(bookEntity);
		
	}

	@Override
	public void updateLibro(LibroDTO libro, Long id) {
		GeneroEntity genero =	generoDao.findById(libro.getGenero()).orElse(null);
		
		LibroEntity libEntity =  libroDao.findById(id).orElse(null);
		
		libEntity.setAutor(libro.getAutor());
		libEntity.setFecha_publicacion(libro.getFecha_publicacion());
		libEntity.setTitulo(libro.getTitulo());
		libEntity.setCantidad(libro.getCantidad());
		libEntity.setGenero(genero);
		
		libroDao.save(libEntity);
	}


	@Override
	public List<GeneroEntity> findAllGenero() {
		return (List<GeneroEntity>) generoDao.findAll();
	}
	
	@Override
	public List<LibroDTO> filter(String keyword) {
		
		List<LibroEntity> lsLibros = libroDao.search(keyword);
		
		return null;
		
	/*	return lsLibros.stream().map(enti -> new LibroDTO(
				enti.getId(),
				enti.getAutor(),
				enti.getTitulo(),
				enti.getFecha_publicacion(),
				enti.getGenero().getNombre_genero()
				)).collect(Collectors.toList());*/
		
	}

	@Override
	public List<LibroEntity> findBooksAviable() {
		return libroDao.bookdAviable();
	}

	

}
