package br.com.coelho.pedidos.repository.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.coelho.pedidos.repository.enties.PedidoItensEntity;

public interface PedidoItensRepository extends JpaRepository<PedidoItensEntity, Long> {

}
