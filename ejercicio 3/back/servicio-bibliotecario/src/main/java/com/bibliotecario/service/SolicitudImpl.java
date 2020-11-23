package com.bibliotecario.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bibliotecario.dao.BookDao;
import com.bibliotecario.dao.IHistoryBooks;
import com.bibliotecario.dao.SolicitudDao;
import com.bibliotecario.dao.StatusDao;
import com.bibliotecario.dto.LibroDTO;
import com.bibliotecario.dto.LibrosStatus;
import com.bibliotecario.dto.SolicitudDTO;
import com.commons.models.HistorialLibros;
import com.commons.models.LibroEntity;
import com.commons.models.SolicitudEntity;
import com.commons.models.StatusEntity;
import com.commons.models.UsuarioEntity;


@Service
public class SolicitudImpl implements ISolicitud{
	
	private static Logger log = LoggerFactory.getLogger(SolicitudImpl.class);
	
	@Autowired
	private SolicitudDao solicitudDao;
	
	@Autowired
	private StatusDao statusDao;
	
	@Autowired
	private BookDao bookDao;

	@Autowired
	private IHistoryBooks historyDao;
	
	///encuentra las solicitudes dependideno del estus
	@Override
	public List<SolicitudDTO> findAllByTypeStatus(Long idStatus) {
		
		List<SolicitudEntity> lsResquest = solicitudDao.findByRequestBook(idStatus);
		
	return lsResquest.stream().map(enti -> new SolicitudDTO(enti.getId(),
				enti.getLibro().getTitulo(),
				enti.getLibro().getAutor(),
				enti.getStatus().getNombre_status(),
				enti.getUsuario().getNombre(),
				enti.getUsuario().getCorreo()
				)).collect(Collectors.toList());
	}
	
	
	//actuliza el estatus de la solicitud
	@Override
	public String updateRequest(Long idSolicitud,Long idStatus) {
		
		String msg ="cambio efectuado"; 
		
		SolicitudEntity solicitudEntity = solicitudDao.findById(idSolicitud).orElse(null);
		
		if(solicitudEntity.getUsuario().getEnable() == 2   ) {
			
			StatusEntity stEntity = statusDao.findById(3L).orElse(null);
			
			solicitudEntity.setStatus(stEntity);
			
			solicitudDao.save(solicitudEntity);
			
			return "usuario dado de baja";
		
		}
		
		StatusEntity stEntity = statusDao.findById(idStatus).orElse(null);
		
		if(idStatus == 2) {
			
			
			if(this.verifiyRequestApproved(idStatus, solicitudEntity.getLibro().getId())) {
				
				solicitudEntity.setStatus(stEntity);
				
				this.insertHistoryBook(idSolicitud);
				
				msg = "cambio efectuado";
				
			}else {
				return "error exedio limite de libros";
			}
			
		}else {
			solicitudEntity.setStatus(stEntity);
		}
		
		solicitudDao.save(solicitudEntity);
		
		return msg;
	}

	
	//consulta solo prestados 
	@Override
	public List<SolicitudDTO> findAllByGiveBook() {

		List<SolicitudEntity> lsResquest = solicitudDao.findByGiveBook();
				
		return lsResquest.stream().map(enti -> new SolicitudDTO(enti.getId(),
				enti.getLibro().getTitulo(),
				enti.getLibro().getAutor(),
				enti.getStatus().getNombre_status(),
				enti.getUsuario().getNombre(),
				enti.getUsuario().getCorreo()
				)).collect(Collectors.toList());
	}
	
	

	@Override
	public List<SolicitudEntity> findRequest() {
		
		return (List<SolicitudEntity>) solicitudDao.findAll();
		
	}
	
	
	//este es la busqueda del filtrado 
	@Override
	public List<LibrosStatus> findAll(String keyword) {
		
		List<SolicitudEntity> lsResquest = solicitudDao.findFilter(keyword);
				
		return lsResquest.stream().
				map(enti -> new LibrosStatus(
						enti.getLibro().getAutor(),
						enti.getLibro().getTitulo(),
						enti.getLibro().getFecha_publicacion(),
						enti.getLibro().getGenero().getNombre_genero(),
						enti.getStatus().getNombre_status()
				))
				.collect(Collectors.toList());
	}

	//verifica que si hay libros disponibles
	private boolean verifiyRequestApproved(Long idStatus, Long idBook){
		boolean requestApproved = false;
		
		LibroEntity bookEntity = bookDao.findById(idBook).orElse(null);
		
		int amountBook = bookEntity.getCantidad();
		
		int amountStatusBook =  solicitudDao.countStatusApproved(idBook, idStatus);
		
		if( amountStatusBook < amountBook) {
			requestApproved = true;
		}
		
		return requestApproved;
	}
	
	private void insertHistoryBook(Long idRquest) {
		
		SolicitudEntity requestEntity =  solicitudDao.findById(idRquest).orElse(null);
		
		HistorialLibros historiEntity = new HistorialLibros();
		
		historiEntity.setUsuario(requestEntity.getUsuario());
		historiEntity.setLibro(requestEntity.getLibro());
		
		historyDao.save(historiEntity);
	}
}
