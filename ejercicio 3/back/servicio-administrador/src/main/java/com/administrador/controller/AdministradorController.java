package com.administrador.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.administrador.dto.LibroDTO;
import com.administrador.dto.UserDTO;
import com.administrador.service.ILibros;
import com.administrador.service.IUsuers;
import com.commons.models.GeneroEntity;
import com.commons.models.LibroEntity;
import com.commons.models.RolEntity;
import com.commons.models.UsuarioEntity;

@CrossOrigin
@RestController
public class AdministradorController {
	
	@Autowired
	private ILibros iLibros;
	
	@Autowired
	private IUsuers iUsers;
	
	@PostMapping("/agregar")
    public void agregar(@RequestBody LibroDTO libro){
		iLibros.save(libro);
    }
	
	@GetMapping("/listar")
    public List<LibroEntity> listar(){
        return iLibros.findAll();
    }
	
	@GetMapping("/buscar/{dato}")
    public List<LibroDTO> buscar(@PathVariable String dato){
		return iLibros.filter(dato);
    }

	@PutMapping("/actulizar/{id}")
	public void actulizarLibro(@RequestBody LibroDTO libro,@PathVariable Long id) {
		iLibros.updateLibro(libro, id);
	}
	
	@DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Long id){
		iLibros.deleteById(id);
    }
	
	@PostMapping("/insertar-usuario")
	public String insertUser(@RequestBody UsuarioEntity usuarioEntity) {
		return iUsers.insertUser(usuarioEntity);
	}
	
	@PutMapping("/actulizar-usuario/{id}")
	public void updateUser(@RequestBody UsuarioEntity usuarioEntity, @PathVariable Long id) {
		iUsers.updateUser(usuarioEntity, id);
	}
	
	@GetMapping("/listar-usuarios")
	public List<UserDTO> findAllUsers(){
		return iUsers.findAllUsers();
	}
	
	@DeleteMapping("/eliminar-usuario/{id}")
	public void deleteUser(@PathVariable Long id) {
		iUsers.deleteUser(id);
	}
	
	@GetMapping("/obtener-roles")
	public List<RolEntity> getAllRol() {
		return iUsers.getAllRol();
	}
	
	@GetMapping("/obtener-generos")
	public List<GeneroEntity> getAllGenero() {
		return iLibros.findAllGenero();
	}
	
	@GetMapping("/mostrar-libros-diponibles")
	public List<LibroEntity> booksAviable (){
		return iLibros.findBooksAviable();
	}

}
