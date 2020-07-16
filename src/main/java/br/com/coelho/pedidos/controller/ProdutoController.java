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

import br.com.coelho.pedidos.repository.repositories.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
@CrossOrigin
public class ProdutoController {
	
	public static final Logger logger = LoggerFactory.getLogger(ProdutoController.class);
	
	@Autowired
	public ProdutoRepository produto;
	
	@GetMapping
	public ResponseEntity<?> findAll(){
		try {
			logger.info("Acessando sistema de listar Produtos");
			return new ResponseEntity<>(produto.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Erro em listar produtos erro: " + e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
