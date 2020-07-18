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

import br.com.coelho.pedidos.repository.enties.VendedorEntity;
import br.com.coelho.pedidos.repository.repositories.VendedorRepository;

@RestController
@RequestMapping("/vendedor")
@CrossOrigin
public class VendedorController {

	public static final Logger logger = LoggerFactory.getLogger(VendedorController.class);

	@Autowired
	public VendedorRepository vendedor;

	@GetMapping
	public ResponseEntity<?> findAll() {
		try {
			logger.info("Acessando funcionalidade de listar vendedores");
			return new ResponseEntity<>(vendedor.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Erro em listar vendedores erro: %s", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable(name = "id") Long id) {
		try {
			logger.info("Acessando sistema de buscar vendedor por id");
			Optional<VendedorEntity> vendedorOpt = vendedor.findById(id);
			if (!vendedorOpt.isPresent()) {
				logger.info("Vendedor com id %d não cadastrado", id);
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(vendedorOpt.get(), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Erro em buscar vendedor id %d erro: %s", id, e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping
	public ResponseEntity<?> cadastrarVendedor(@RequestBody VendedorEntity vendedorEntity) {
		try {
			logger.info("Acessando sistema de cadastrar vendedor");
			return new ResponseEntity<>(vendedor.save(vendedorEntity), HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("Erro em cadastrar vendedor erro: %s", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> alterarCliente(@PathVariable(name = "id") Long id,
			@RequestBody VendedorEntity vendedorEntity) {
		try {
			logger.info("Acessando sistema de alterar vendedor");
			Optional<VendedorEntity> vendedorOpt = vendedor.findById(id);
			if (!vendedorOpt.isPresent()) {
				logger.info("Vendedor com id %d não cadastrado", id);
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			vendedorEntity.setIdVendedor(id);
			return new ResponseEntity<>(vendedor.save(vendedorEntity), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Erro em alterar vendedor id %d erro: %s", id, e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarVendedor(@PathVariable(name = "id") Long id) {
		try {
			logger.info("Acessando sistema de deletar vendedor");
			Optional<VendedorEntity> vendedorOpt = vendedor.findById(id);
			if (!vendedorOpt.isPresent()) {
				logger.info("Vendedor com id %d não cadastrado", id);
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			vendedor.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Erro em alterar vendedor id %d erro: %s", id, e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<?> getByCodigo(@RequestParam(name = "codigo") Long codigo) {
		try {
			logger.info("Acessando sistema de buscar vendedor por codigo");
			Optional<VendedorEntity> vendedorOpt = vendedor.findByCodigoVendedor(codigo);
			if (!vendedorOpt.isPresent()) {
				logger.info("Vendedor codigo %d não cadastrado", codigo);
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(vendedorOpt.get(), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Erro em buscar vendedor codigo %d erro: %s", codigo, e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
