package com.lector.controller;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lector.dto.BookHistoryDTO;
import com.lector.dto.ReaderDTO;

import com.lector.service.IReader;


@RestController
@CrossOrigin
public class ReaderController {
	
	@Autowired
	private IReader readerService;

	@PostMapping("/registrarse")
	public String insertReader(@RequestBody ReaderDTO reader) {
		return readerService.insertReader(reader);
	}
	
	@GetMapping("/consultar-lector/{id}")
	public ReaderDTO getReader(@PathVariable Long id) {
		return readerService.consultReader(id);
	}
	
	@DeleteMapping("/baja-lector/{id}")
	public void downReader( @PathVariable Long id) {
		readerService.downReader(id);
	}
	
	@PutMapping("/actulizar-datos/{id}")
	public void updateReader(@RequestBody ReaderDTO readerDTO, @PathVariable Long id){
		readerService.updateReader(readerDTO, id);
	}
	
	@GetMapping("/consultar-hitorial/{id}")
	public List<BookHistoryDTO> getBookHistory(@PathVariable Long id) {
		return readerService.conultBookHistory(id);
	}
	
	@PutMapping("/solicitar-libro/{idLibro}/lector/{idUsuario}")
	public String resquetBook(@PathVariable Long idLibro,@PathVariable Long idUsuario) {
		return readerService.requestBook(idLibro, idUsuario);
	}
	
	@GetMapping("/login/{correo}")
	public ReaderDTO login(@PathVariable String correo) {
		return readerService.login(correo);
	}
}
