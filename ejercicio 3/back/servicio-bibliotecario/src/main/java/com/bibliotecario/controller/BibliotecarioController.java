package com.bibliotecario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bibliotecario.dto.LibroDTO;
import com.bibliotecario.dto.LibrosStatus;
import com.bibliotecario.dto.SolicitudDTO;
import com.bibliotecario.service.ISolicitud;
import com.commons.models.SolicitudEntity;

@CrossOrigin
@RestController
public class BibliotecarioController {
	
	@Autowired
	private ISolicitud solicitudService;
	
	@GetMapping("/consultar-solicitudes/{idStatus}")
	public List<SolicitudDTO> listOnlyRequest( @PathVariable Long idStatus){
		return solicitudService.findAllByTypeStatus(idStatus);
	}
	
	@PutMapping("/actulizar-solicitudes/{idSolicitud}/status/{idStatus}")
	public String updateStatusRequest(@PathVariable Long idSolicitud, @PathVariable Long idStatus){
		return solicitudService.updateRequest(idSolicitud, idStatus);
	}
	
	
	
	/*
	@GetMapping("/consulta-libros-prestados")
	public List<SolicitudDTO> booksGive() {
		return solicitudService.findAllByGiveBook();
	}*/

	
/*	@GetMapping("/listar")
	public List<SolicitudEntity> listRequest() {
		return solicitudService.findRequest();
	}

// que da pendiente este 
	@GetMapping("/lista-detalle/{keyword}")
	public List<LibrosStatus> listBooksStatus(@PathVariable String keyword){
		return solicitudService.findAll(keyword);
	}*/
}
