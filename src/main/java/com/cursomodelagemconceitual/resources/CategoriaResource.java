package com.cursomodelagemconceitual.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cursomodelagemconceitual.domain.Categoria;
import com.cursomodelagemconceitual.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService categoriaService;

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		
		Categoria categoria = categoriaService.buscar(id);
		
		// insert (test)
		// INSERT INTO CATEGORIA ( NOME) VALUES ('Escritório');
		// INSERT INTO CATEGORIA ( NOME) VALUES ('Informática');
		
		return ResponseEntity.ok().body(categoria);
	}
}
