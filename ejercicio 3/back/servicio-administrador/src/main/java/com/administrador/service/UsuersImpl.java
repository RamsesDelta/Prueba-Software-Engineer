package com.administrador.service;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.mapping.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.administrador.dao.RolDao;
import com.administrador.dao.UserDao;
import com.administrador.dao.UserRolDao;
import com.administrador.dto.UserDTO;
import com.commons.models.RolEntity;
import com.commons.models.UsuarioEntity;
import com.commons.models.UsuarioRolEntity;


@Service
public class UsuersImpl implements IUsuers {
	
	private static Logger log = LoggerFactory.getLogger(UsuersImpl.class);
	
	@Autowired
	private RolDao rolDao;
	
	@Autowired
	private UserRolDao userRolDao;
	
	@Autowired
	private UserDao userDao;

	@Override
	public String insertUser(UsuarioEntity usuarioEntity) {
		
		String msg = "registro exitoso";

		if (userDao.findUsuer(usuarioEntity.getCorreo()) == null) {
			RolEntity rolEntity = rolDao.findById(usuarioEntity.getRol()).orElse(null);
			
			
			
			UsuarioEntity userEntity = userDao.save(usuarioEntity);

			UsuarioRolEntity userRolEntity = new UsuarioRolEntity();

			userRolEntity.setRol(rolEntity);
			userRolEntity.setUsuario(userEntity);

			userRolDao.save(userRolEntity);

		} else {
			msg = "usuario registrado";
		}

		return msg;
	}

	@Override
	public void updateUser(UsuarioEntity usuarioEntity,Long id) {
		
		RolEntity rolEntity = rolDao.findById(usuarioEntity.getRol()).orElse(null);
		
		UsuarioEntity userEntity = userDao.findById(id).orElse(null);
		
		userEntity.setNombre(usuarioEntity.getNombre());
		userEntity.setFecha_nacimiento(usuarioEntity.getFecha_nacimiento());
		userEntity.setCorreo(usuarioEntity.getCorreo());
		userEntity.setPassword(userEntity.getPassword());
		userEntity.setEnable(userEntity.getEnable());
			
		UsuarioRolEntity userRolEntity = userRolDao.findUsuerRol(id);

		
		userRolEntity.setRol(rolEntity);
		userRolEntity.setUsuario(userEntity);
		
		userRolDao.save(userRolEntity);	
		
	}

	@Override
	public List<UserDTO> findAllUsers() {
		
	List<UsuarioRolEntity> userRolEntity =	(List<UsuarioRolEntity>) userRolDao.findAll();
		
		return userRolEntity.stream()
				.map(ur -> new UserDTO (ur.getUsuario().getId(),
						ur.getUsuario().getNombre(),
						ur.getUsuario().getCorreo(),
						ur.getUsuario().getFecha_nacimiento(),
						ur.getUsuario().getEnable(),
						ur.getRol().getId(),
						ur.getRol().getNombre())
				).collect(Collectors.toList());
	}

	@Override
	public void deleteUser(Long id) {
		
		UsuarioEntity userEntity = userDao.findById(id).orElse(null);
		userEntity.setEnable(2);
		
		userDao.save(userEntity);
		
	}

	@Override
	public List<RolEntity> getAllRol() {
		return (List<RolEntity>) rolDao.findAll();
	}
}
