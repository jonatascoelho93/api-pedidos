package br.com.coelho.pedidos.repository.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.coelho.pedidos.repository.enties.ProdutoEntity;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {

}
