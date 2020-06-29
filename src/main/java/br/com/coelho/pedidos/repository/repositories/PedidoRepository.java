package br.com.coelho.pedidos.repository.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.coelho.pedidos.repository.enties.PedidoEntity;

public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {

}
