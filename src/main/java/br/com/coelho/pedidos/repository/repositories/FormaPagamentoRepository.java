package br.com.coelho.pedidos.repository.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.coelho.pedidos.repository.enties.FormaPagamentoEntity;

public interface FormaPagamentoRepository extends JpaRepository<FormaPagamentoEntity, Long> {

}
