package br.com.coelho.pedidos.repository.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.coelho.pedidos.repository.enties.VendedorEntity;

public interface VendedorRepository extends JpaRepository<VendedorEntity, Long> {

}
