package com.bibliotecario.service;

import java.util.List;

import com.bibliotecario.dto.LibroDTO;
import com.bibliotecario.dto.LibrosStatus;
import com.bibliotecario.dto.SolicitudDTO;
import com.commons.models.SolicitudEntity;

public interface ISolicitud {

	List<SolicitudDTO>	 findAllByTypeStatus(Long idStatus);
	
	String updateRequest(Long idSolicitud,Long idStatus);
	
	List<SolicitudEntity> findRequest();
	
	List<SolicitudDTO>	findAllByGiveBook();
	
	List<LibrosStatus> findAll(String keyword);
}
