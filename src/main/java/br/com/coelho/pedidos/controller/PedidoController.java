package br.com.coelho.pedidos.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.coelho.pedidos.repository.enties.PedidoEntity;
import br.com.coelho.pedidos.repository.repositories.PedidoRepository;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin
public class PedidoController {

	public static final Logger logger = LoggerFactory.getLogger(PedidoController.class);

	@Autowired
	public PedidoRepository pedido;

	@GetMapping
	public ResponseEntity<?> findAll() {
		try {
			logger.info("Acessando o funcionalidade de listar pedidos");
			return new ResponseEntity<>(pedido.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Erro em listar pedidos erro: " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable(name = "id") Long id) {
		try {
			logger.info("Acessando funcionalidade de busca de pedido por id");
			Optional<PedidoEntity> pedidoOpt = pedido.findById(id);
			if (!pedidoOpt.isPresent()) {
				logger.info("Pedido com id %d não cadastrado", id);
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(pedidoOpt.get(), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Erro em buscar pedido com id %d erro: %s", id, e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
