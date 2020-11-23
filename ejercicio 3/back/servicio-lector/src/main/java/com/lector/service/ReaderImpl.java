package com.lector.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.hibernate.mapping.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.commons.models.HistorialLibros;
import com.commons.models.SolicitudEntity;
import com.commons.models.UsuarioEntity;
import com.commons.models.UsuarioRolEntity;
import com.lector.cliente.ReaderClientRest;
import com.lector.dao.BookDao;
import com.lector.dao.IHistoryBooks;
import com.lector.dao.SolicitudDao;
import com.lector.dao.StatusDao;
import com.lector.dao.UserDao;
import com.lector.dao.UserRolDao;
import com.lector.dto.BookHistoryDTO;
import com.lector.dto.ReaderDTO;

@Service
public class ReaderImpl implements IReader {

	private static Logger log = LoggerFactory.getLogger(ReaderImpl.class);

	@Autowired
	private UserRolDao userRolDao;

	@Autowired
	private SolicitudDao requestDao;

	@Autowired
	private BookDao bookDao;

	@Autowired
	private StatusDao statusDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private ReaderClientRest readerClientFeign;
	
	@Autowired
	private IHistoryBooks historyDao;

	
	//registra lector
	@Override
	public String insertReader(ReaderDTO readerDTO) {

		UsuarioEntity userEntity = new UsuarioEntity();

		userEntity.setNombre(readerDTO.getNombre());
		userEntity.setCorreo(readerDTO.getCorreo());
		userEntity.setFecha_nacimiento(readerDTO.getFecha_nacimiento());
		userEntity.setPassword(readerDTO.getPassword());
		userEntity.setEnable(1);
		userEntity.setRol(1L);

		return readerClientFeign.insertReader(userEntity);

	}

	//actuliza datos lector
	@Override
	public void updateReader(ReaderDTO readerDTO, Long id) {

		UsuarioEntity userEntity = new UsuarioEntity();

		userEntity.setNombre(readerDTO.getNombre());
		userEntity.setCorreo(readerDTO.getCorreo());
		userEntity.setFecha_nacimiento(readerDTO.getFecha_nacimiento());
		userEntity.setRol(1L);

		readerClientFeign.updateReader(userEntity, id);

	}

	//darse de baja lector
	@Override
	public void downReader(Long id) {
		readerClientFeign.deleteUser(id);
	}

	
	//consulta sus datos el lector
	@Override
	public ReaderDTO consultReader(Long id) {

		UsuarioEntity userEntity = userDao.findById(id).orElse(null);

		if (userEntity.getEnable() == 2) {
			return null;
		}

		return new ReaderDTO(userEntity.getId(), userEntity.getNombre(), userEntity.getFecha_nacimiento(),
				userEntity.getCorreo(), null);
	}

	//consulta libros prestados lector
	@Override
	public List<BookHistoryDTO> conultBookHistory(Long id) {

		List<HistorialLibros> history = historyDao.findReaderHistory(id);

		return history.stream()
				.map(ht -> new BookHistoryDTO(ht.getLibro().getAutor(), ht.getLibro().getTitulo(),
						ht.getLibro().getFecha_publicacion(), ht.getLibro().getGenero().getNombre_genero()))
				.collect(Collectors.toList());
	}

	
	//solicita libro el lector
	@Override
	public String requestBook(Long idLibro, Long idUsuer) {

		String response = "usuario no registrado";

		if (validateReaderRegistrer(idUsuer)) {
			if (validRequest(idLibro, idUsuer)) {

				SolicitudEntity requestEntity = new SolicitudEntity();

				requestEntity.setUsuario(userDao.findById(idUsuer).orElse(null));

				requestEntity.setLibro(bookDao.findById(idLibro).orElse(null));

				requestEntity.setStatus(statusDao.findById(1L).orElse(null));

				requestDao.save(requestEntity);

				response = "solicitud aprobada";

			} else {
				response = "ya hiciste la solicitud o tienes el libro";
			}
		}

		return response;

	}

	//hace el login
	@Override
	public ReaderDTO login(String correo) {

		UsuarioEntity userEntity = userDao.findReader(correo);

		if (userEntity == null) {
			return null;
		} else {
			return new ReaderDTO(userEntity.getId(), userEntity.getNombre(), userEntity.getFecha_nacimiento(),
					userEntity.getCorreo(), null);
		}

	}

	//valida solicitud ya echa 
	private boolean validRequest(Long idLib, Long idUsuer) {

		if (requestDao.findRequestByUserAndLib(idLib, idUsuer) == null) {
			return true;
		}

		return false;
	}

	
	//verifica lector antes de pedir libro
	private boolean validateReaderRegistrer(Long idUsuer) {

		if (userDao.findById(idUsuer).isEmpty()) {
			return false;
		} else {
			return true;
		}

	}

}
