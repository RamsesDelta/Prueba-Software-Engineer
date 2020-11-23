package com.lector.cliente;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.commons.models.UsuarioEntity;

@FeignClient(name = "servicio-administrador", url = "localhost:8282" )
public interface ReaderClientRest {
	
	@PostMapping("/insertar-usuario")
	public String insertReader(@RequestBody UsuarioEntity usuarioEntity);
	
	@PutMapping("/actulizar-usuario/{id}")
	public void updateReader(@RequestBody UsuarioEntity usuarioEntity, @RequestParam(name = "id") Long id);
	
	@DeleteMapping("/eliminar-usuario/{id}")
	public void deleteUser(@RequestParam(name = "id") Long id);

}
