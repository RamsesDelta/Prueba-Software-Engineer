package com.administrador.service;

import java.util.List;

import com.administrador.dto.UserDTO;
import com.commons.models.RolEntity;
import com.commons.models.UsuarioEntity;


public interface IUsuers {
	
	String insertUser(UsuarioEntity usuarioEntity);
	
	void updateUser(UsuarioEntity usuarioEntity, Long id);
	
	List<UserDTO> findAllUsers();
	
	void deleteUser(Long id);
	
	List<RolEntity> getAllRol();
}
