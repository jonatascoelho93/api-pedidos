package br.com.coelho.pedidos.repository.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.coelho.pedidos.repository.enties.VendedorEntity;

public interface VendedorRepository extends JpaRepository<VendedorEntity, Long> {

	Optional<VendedorEntity> findByCodigoVendedor(Long codigo);

}
