package br.com.coelho.pedidos.repository.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.coelho.pedidos.repository.enties.EstoqueEntity;

public interface EstoqueRepository extends JpaRepository<EstoqueEntity, Long> {

}
