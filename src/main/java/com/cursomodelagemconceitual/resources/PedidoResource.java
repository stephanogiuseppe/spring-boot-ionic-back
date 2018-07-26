package com.cursomodelagemconceitual.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cursomodelagemconceitual.domain.Pedido;
import com.cursomodelagemconceitual.services.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {
	
	@Autowired
	private PedidoService pedidoService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Pedido> find(@PathVariable Integer id) {
		
		Pedido pedido = pedidoService.find(id);
		
		return ResponseEntity.ok().body(pedido);
	}
	
	@RequestMapping
	public ResponseEntity<?> findAll() {
		
		List<Pedido> pedidos = pedidoService.findAll();
		
		return ResponseEntity.ok().body(pedidos);
	}
}
