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

import br.com.coelho.pedidos.repository.enties.ProdutoEntity;
import br.com.coelho.pedidos.repository.repositories.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
@CrossOrigin
public class ProdutoController {

	public static final Logger logger = LoggerFactory.getLogger(ProdutoController.class);

	@Autowired
	public ProdutoRepository produto;

	@GetMapping
	public ResponseEntity<?> findAll() {
		try {
			logger.info("Acessando funcionalidade de listar Produtos");
			return new ResponseEntity<>(produto.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Erro em listar produtos erro: " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable(name = "id") Long id) {
		try {
			logger.info("Acessando funcionalidade de busca de produto por id");
			Optional<ProdutoEntity> produtoOpt = produto.findById(id);
			if (!produtoOpt.isPresent()) {
				logger.info("Produto com id %d não cadastrado", id);
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(produtoOpt.get(), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Erro em buscar produto id %d erro: %s", id, e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping
	public ResponseEntity<?> cadastrarProduto(@RequestBody ProdutoEntity produtoEntity) {
		try {
			logger.info("Acessando funcionalidade de cadastro de produto");
			return new ResponseEntity<>(produto.save(produtoEntity), HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("Erro em cadastrar cliente erro: %s", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> alterarProduto(@PathVariable(name = "id") Long id,
			@RequestBody ProdutoEntity produtoEntity) {
		try {
			logger.info("Acessando funcionalidade de alterar produto por id");
			Optional<ProdutoEntity> produtoOpt = produto.findById(id);
			if (!produtoOpt.isPresent()) {
				logger.info("Produto com id %d não cadastrado", id);
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			produtoEntity.setIdProduto(id);
			return new ResponseEntity<>(produto.save(produtoEntity), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Erro em alterar produto id %d erro: %s", id, e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarProduto(@PathVariable(name = "id") Long id,
			@RequestBody ProdutoEntity produtoEntity) {
		try {
			logger.info("Acessando funcionalidade de deletar produto por id");
			Optional<ProdutoEntity> produtoOpt = produto.findById(id);
			if (!produtoOpt.isPresent()) {
				logger.info("Produto com id %d não cadastrado", id);
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			produto.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Erro em deletar produto id %d erro: %s", id, e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<?> getByCodigo(@RequestParam(name = "codigo") Long codigo) {
		try {
			logger.info("Acessando funcionalidade de busca de produto por codigo");
			Optional<ProdutoEntity> produtoOpt = produto.findByCodProduto(codigo);
			if (!produtoOpt.isPresent()) {
				logger.info("Produto com codigo %d não cadastrado", codigo);
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(produtoOpt.get(), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Erro em buscar produto codigo %d erro: %s", codigo, e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
