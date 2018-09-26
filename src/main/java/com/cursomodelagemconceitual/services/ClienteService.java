package com.cursomodelagemconceitual.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.cursomodelagemconceitual.domain.Cliente;
import com.cursomodelagemconceitual.dto.ClienteDTO;
import com.cursomodelagemconceitual.repositories.ClienteRepository;
import com.cursomodelagemconceitual.services.exceptions.DataIntegrityException;
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

	public Cliente insert(Cliente cliente) {
		cliente.setId(null);
		return clienteRepository.save(cliente);
	}

	public Cliente update(Cliente cliente) {
		Cliente newCliente = find(cliente.getId());
		updateData(newCliente, cliente);
		return clienteRepository.save(newCliente);	
	}

	public void delete(Integer id) {
		find(id);
		try {
			clienteRepository.deleteById(id);			
		} catch (DataIntegrityViolationException exception) {
			throw new DataIntegrityException("Não é possível excluir o cliente, pois há entidades relacionadas");
		}
	}

	public Page<Cliente> findPage(Integer page, Integer size, String orderBy, String direction) {

		PageRequest pageRequest = PageRequest.of(page, size, Direction.valueOf(direction), orderBy);
		
		return clienteRepository.findAll(pageRequest);
	}

	public Cliente fromDTO(ClienteDTO clienteDTO) {
		return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null, null);
	}

	private void updateData(Cliente newCliente, Cliente cliente) {
		newCliente.setNome(cliente.getNome());
		newCliente.setEmail(cliente.getEmail());
	}

}
