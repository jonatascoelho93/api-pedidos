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

import br.com.coelho.pedidos.repository.enties.EstoqueEntity;
import br.com.coelho.pedidos.repository.repositories.EstoqueRepository;

@RestController
@RequestMapping("/estoque")
@CrossOrigin
public class EstoqueController {

	public static final Logger logger = LoggerFactory.getLogger(EstoqueController.class);

	@Autowired
	public EstoqueRepository estoque;

	@GetMapping
	public ResponseEntity<?> findAll() {
		try {
			logger.info("Acessando funcionalidade de listar estoque");
			return new ResponseEntity<>(estoque.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Erro em listar estoque erro: " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable(name = "id") Long id) {
		try {
			logger.info("Acessando funcionalidade de buscar estoque por id");
			Optional<EstoqueEntity> estoqueOpt = estoque.findById(id);
			if (!estoqueOpt.isPresent()) {
				logger.info("Estoque com id %d não cadastrado", id);
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(estoqueOpt.get(), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Erro em buscar estoque com id %d erro: %s", id, e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
