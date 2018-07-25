package com.cursomodelagemconceitual.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursomodelagemconceitual.domain.Pedido;
import com.cursomodelagemconceitual.repositories.PedidoRepository;
import com.cursomodelagemconceitual.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	public List<Pedido> findAll() {

		List<Pedido> pedidos = pedidoRepository.findAll();

		return pedidos;
	}
	
	public Pedido find(Integer id) {

		Optional<Pedido> pedido = pedidoRepository.findById(id);

		return pedido.orElseThrow(() -> 
			new ObjectNotFoundException("Objeto não encontrado. Id: " + id + ", tipo: " + Pedido.class.getName())
		);
	}
}
