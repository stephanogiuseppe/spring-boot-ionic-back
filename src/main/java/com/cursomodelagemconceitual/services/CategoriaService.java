package com.cursomodelagemconceitual.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursomodelagemconceitual.domain.Categoria;
import com.cursomodelagemconceitual.repositories.CategoriaRepository;
import com.cursomodelagemconceitual.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Categoria find(Integer id) {

		Optional<Categoria> categoria = categoriaRepository.findById(id);

		return categoria.orElseThrow(() -> 
			new ObjectNotFoundException("Objeto não encontrado. Id: " + id + ", tipo: " + Categoria.class.getName())
		);
	}
}
