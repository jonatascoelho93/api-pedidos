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

import br.com.coelho.pedidos.repository.repositories.FormaPagamentoRepository;

@RestController
@RequestMapping("/formapagamento")
@CrossOrigin
public class FormaPagamentoController {
	
	public static final Logger logger = LoggerFactory.getLogger(FormaPagamentoController.class);
	
	@Autowired
	public FormaPagamentoRepository formaPagamento;
	
	@GetMapping
	public ResponseEntity<?> findAll(){
		try {
			logger.info("Acessando o sistema de listar Formas de pagamentos" );
			return new ResponseEntity<>(formaPagamento.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Erro em listar formas de pagamentos");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
