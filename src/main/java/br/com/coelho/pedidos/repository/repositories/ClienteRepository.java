package br.com.coelho.pedidos.repository.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.coelho.pedidos.repository.enties.ClienteEntity;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

}
