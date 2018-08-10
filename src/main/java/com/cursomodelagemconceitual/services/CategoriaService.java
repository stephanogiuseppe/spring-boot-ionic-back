package com.cursomodelagemconceitual.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.cursomodelagemconceitual.domain.Categoria;
import com.cursomodelagemconceitual.dto.CategoriaDTO;
import com.cursomodelagemconceitual.repositories.CategoriaRepository;
import com.cursomodelagemconceitual.services.exceptions.DataIntegrityException;
import com.cursomodelagemconceitual.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public List<Categoria> findAll() {

		List<Categoria> categorias = categoriaRepository.findAll();

		return categorias;
	}
	
	public Categoria find(Integer id) {

		Optional<Categoria> categoria = categoriaRepository.findById(id);

		return categoria.orElseThrow(() -> 
			new ObjectNotFoundException("Objeto não encontrado. Id: " + id + ", tipo: " + Categoria.class.getName())
		);
	}

	public Categoria insert(Categoria categoria) {
		categoria.setId(null);
		return categoriaRepository.save(categoria);
	}

	public Categoria update(Categoria categoria) {
		find(categoria.getId());
		return categoriaRepository.save(categoria);	
	}

	public void delete(Integer id) {
		find(id);
		try {
			categoriaRepository.deleteById(id);			
		} catch (DataIntegrityViolationException exception) {
			throw new DataIntegrityException("Não é possível excluir uma categoria quem contenha um ou mais produtos");
		}
	}

	public Page<Categoria> findPage(Integer page, Integer size, String orderBy, String direction) {

		PageRequest pageRequest = PageRequest.of(page, size, Direction.valueOf(direction), orderBy);
		
		return categoriaRepository.findAll(pageRequest);
	}

	public Categoria fromDTO(CategoriaDTO categoriaDTO) {
		return new Categoria(categoriaDTO.getId(), categoriaDTO.getNome());
	}
}
