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

import br.com.coelho.pedidos.repository.enties.ClienteEntity;
import br.com.coelho.pedidos.repository.repositories.ClienteRepository;

@RestController
@RequestMapping("/cliente")
@CrossOrigin
public class ClienteController {

	public static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

	@Autowired
	public ClienteRepository cliente;

	@GetMapping
	public ResponseEntity<?> findAll() {
		try {
			logger.info("Acessando funcionalidade de listar Clientes");
			return new ResponseEntity<>(cliente.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Erro em listar clientes erro: " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<?> getByCodigo(@PathVariable(name = "codigo") Long codigo) {
		try {
			logger.info("Acessando funcionalidade de procurar cliente por codigo");
			Optional<ClienteEntity> clienteOpt = cliente.findByCodCliente(codigo);
			if (!clienteOpt.isPresent()) {
				logger.info("Cliente com o codigo %d não cadadastrado", codigo);
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(clienteOpt.get(), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Erro em buscar clinte com o codigo %d erro: %s", codigo, e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
