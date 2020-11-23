package com.lector.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.lector.dto.BookHistoryDTO;
import com.lector.dto.ReaderDTO;


public interface IReader {

	String insertReader(ReaderDTO readerEntity);
	
	void updateReader(ReaderDTO readerDTO, Long id);
	
	void downReader(Long id);
	
	List<BookHistoryDTO> conultBookHistory(Long id);
	
	ReaderDTO consultReader(Long id);
	
	String requestBook (Long idLibro,Long idUsuer);
	
	ReaderDTO login(String correo);
	
}
