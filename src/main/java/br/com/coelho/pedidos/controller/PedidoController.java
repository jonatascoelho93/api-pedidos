package br.com.coelho.pedidos.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@PostMapping
	public ResponseEntity<?> cadastrarPedido(@RequestBody PedidoEntity pedidoEntity) {
		try {
			logger.info("Acessando funcionalidade de cadastrar pedido");
			return new ResponseEntity<>(pedido.save(pedidoEntity), HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("Erro em cadastrar pedido erro %s", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> alterarPedido(@PathVariable(name = "id") Long id, @RequestBody PedidoEntity pedidoEntity) {
		try {
			logger.info("Acessando funcionalidade de alteração de pedido");
			Optional<PedidoEntity> pedidoOpt = pedido.findById(id);
			if (!pedidoOpt.isPresent()) {
				logger.info("Pedido id %d não cadastrado", id);
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			pedidoEntity.setIdPedido(id);
			return new ResponseEntity<>(pedido.save(pedidoEntity), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Erro em cadastrar pedido id %d erro: %s", id, e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping
	public ResponseEntity<?> deleteById(@PathVariable(name = "id") Long id) {
		try {
			logger.info("Acessando funcionalidade de excluir pedido por id");
			Optional<PedidoEntity> pedidoOpt = pedido.findById(id);
			if (!pedidoOpt.isPresent()) {
				logger.info("Pedido com id %d não cadastrado", id);
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			pedido.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Erro em deletar pedido com id %d erro: %s", id, e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
