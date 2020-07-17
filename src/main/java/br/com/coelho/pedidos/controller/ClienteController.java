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
import org.springframework.web.bind.annotation.RequestParam;
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

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable(name = "id") Long id) {
		try {
			logger.info("Acessando funcionalidade de procurar cliente por id");
			Optional<ClienteEntity> clienteOpt = cliente.findById(id);
			if (!clienteOpt.isPresent()) {
				logger.info("Cliente com o id %d não cadadastrado", id);
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(clienteOpt.get(), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Erro em buscar cliente com o id %d erro: %s", id, e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping
	public ResponseEntity<?> cadastrarCliente(@RequestBody ClienteEntity clienteEntity) {
		try {
			logger.info("Acessando funcionalidade de cadastro de clientes");
			return new ResponseEntity<>(cliente.save(clienteEntity), HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("Erro em casdastrar cliente %s erro: %s", clienteEntity.getNomeCompleto(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> alterarCliente(@PathVariable(name = "id") Long id,
			@RequestBody ClienteEntity clienteEntity) {
		try {
			logger.info("Acessando funcionalidade de alterar cliente");
			Optional<ClienteEntity> clienteOpt = cliente.findById(id);
			if (!clienteOpt.isPresent()) {
				logger.info("Cliente com id %d não cadadastrado", id);
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			clienteEntity.setIdCliente(id);
			return new ResponseEntity<>(cliente.save(clienteEntity), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Erro em alterar cliente com id %d erro: %s", id, e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarCliente(@PathVariable(name = "id") Long id) {
		try {
			logger.info("Acessando funcionalidade de deletar cliente por id");
			Optional<ClienteEntity> clienteOpt = cliente.findById(id);
			if (!clienteOpt.isPresent()) {
				logger.info("Cliente com id %d não cadadastrado", id);
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			cliente.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Erro em buscar cliente com id %d erro: %s", id, e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping
	public ResponseEntity<?> getByRegistroFederal(@RequestParam(name = "registrofederal") String registroFederal) {
		try {
			logger.info("Acessando funcionalidade de procurar cliente por codigo");
			Optional<ClienteEntity> clienteOpt = cliente.findByRegistroFederal(registroFederal);
			if (!clienteOpt.isPresent()) {
				logger.info("Cliente com o registro federal %s não cadadastrado", registroFederal);
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(clienteOpt.get(), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Erro em buscar cliente com o registro federal %s erro: %s", registroFederal, e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping
	public ResponseEntity<?> getByCodigo(@RequestParam(name = "codigo") Long codigo) {
		try {
			logger.info("Acessando funcionalidade de procurar cliente por codigo");
			Optional<ClienteEntity> clienteOpt = cliente.findByCodCliente(codigo);
			if (!clienteOpt.isPresent()) {
				logger.info("Cliente com o codigo %d não cadadastrado", codigo);
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(clienteOpt.get(), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Erro em buscar cliente com o codigo %d erro: %s", codigo, e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
}
