package br.com.coelho.pedidos.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.coelho.pedidos.repository.repositories.PedidoRepository;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin
public class PedidoController {
	
	public static final Logger logger = LoggerFactory.getLogger(PedidoController.class);
	
	@Autowired
	public PedidoRepository pedido;
	
	@GetMapping
	public ResponseEntity<?> findAll(){
		try {
			logger.info("Acessando o sistema de listar pedidos");
			return new ResponseEntity<>(pedido.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Erro em listar pedidos erro: " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
