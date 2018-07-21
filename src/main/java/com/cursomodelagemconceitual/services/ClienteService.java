package com.cursomodelagemconceitual.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursomodelagemconceitual.domain.Cliente;
import com.cursomodelagemconceitual.repositories.ClienteRepository;
import com.cursomodelagemconceitual.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public List<Cliente> findAll() {

		List<Cliente> clientes = clienteRepository.findAll();

		return clientes;
	}
	
	public Cliente find(Integer id) {

		Optional<Cliente> cliente = clienteRepository.findById(id);

		return cliente.orElseThrow(() -> 
			new ObjectNotFoundException("Objeto não encontrado. Id: " + id + ", tipo: " + Cliente.class.getName())
		);
	}
}
