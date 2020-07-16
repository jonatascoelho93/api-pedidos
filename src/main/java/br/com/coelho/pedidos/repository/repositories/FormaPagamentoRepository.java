package br.com.coelho.pedidos.repository.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.coelho.pedidos.repository.enties.FormaPagamentoEntity;

public interface FormaPagamentoRepository extends JpaRepository<FormaPagamentoEntity, Long> {

	Optional<FormaPagamentoEntity> findByCodigoFormaDePgto(Long codigo);

}
