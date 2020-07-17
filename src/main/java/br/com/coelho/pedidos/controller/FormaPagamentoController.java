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

import br.com.coelho.pedidos.repository.enties.FormaPagamentoEntity;
import br.com.coelho.pedidos.repository.repositories.FormaPagamentoRepository;

@RestController
@RequestMapping("/formapagamento")
@CrossOrigin
public class FormaPagamentoController {

	public static final Logger logger = LoggerFactory.getLogger(FormaPagamentoController.class);

	@Autowired
	public FormaPagamentoRepository formaPagamento;

	@GetMapping
	public ResponseEntity<?> findAll() {
		try {
			logger.info("Acessando funcionalidade de listar Formas de pagamentos");
			return new ResponseEntity<>(formaPagamento.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Erro em listar formas de pagamentos");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<?> getById(@PathVariable(name = "id") Long id) {
		try {
			logger.info("Acessando funcionalidade de busca de forma de pagamento por id");
			Optional<FormaPagamentoEntity> formaPgtoOpt = formaPagamento.findById(id);
			if (!formaPgtoOpt.isPresent()) {
				logger.info("Forma de pagamento com id %d não cadastrada", id);
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(formaPgtoOpt.get(), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Erro em buscar forma de pagamento com id %d erro: %s", id, e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping
	public ResponseEntity<?> cadastrarFormaPagamento(@RequestBody FormaPagamentoEntity formaPagamentoEntity) {
		try {
			logger.info("Acessando funcionalidade de cadastro de forma de pagamento");
			return new ResponseEntity<>(formaPagamento.save(formaPagamentoEntity), HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("Erro em cadastrar forma de pagamento %s" + formaPagamentoEntity.getDescricaoFormaDePgto());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> alterarFormaPagamento(@PathVariable(name = "id") Long id,
			@RequestBody FormaPagamentoEntity formaPgtoEntity) {
		try {
			logger.info("Acessando funcionalidade de alterar forma de pagamento");
			Optional<FormaPagamentoEntity> formaPgtoOpt = formaPagamento.findById(id);
			if (!formaPgtoOpt.isPresent()) {
				logger.info("Forma de pagamento com id %d não cadastrada", id);
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			formaPgtoEntity.setIdFormaDePgto(id);
			return new ResponseEntity<>(formaPagamento.save(formaPgtoEntity), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Erro em alterar forma de pagamento com id %d erro: %s", id, e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarFormaPagamento(@PathVariable(name = "id") Long id) {
		try {
			logger.info("Acessando funcionalidade de deletar forma de pagamento");
			Optional<FormaPagamentoEntity> formaPgtoOpt = formaPagamento.findById(id);
			if (!formaPgtoOpt.isPresent()) {
				logger.info("Forma de pagamento com id %d não cadastrada", id);
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			formaPagamento.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Erro em deletar forma de pagamento com id %d erro: %s", id, e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping
	public ResponseEntity<?> getByCodigo(@RequestParam(name = "codigo") Long codigo) {
		try {
			logger.info("Acessando funcionalidade de busca de forma de pagamento por codigo");
			Optional<FormaPagamentoEntity> formaPgtoOpt = formaPagamento.findByCodigoFormaDePgto(codigo);
			if (!formaPgtoOpt.isPresent()) {
				logger.info("Forma de pagamento com o codigo %d não cadastrada", codigo);
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(formaPgtoOpt.get(), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Erro em buscar forma de pagamento com codigo %d erro: %s", codigo, e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
