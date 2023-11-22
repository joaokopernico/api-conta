package com.contabilidade.api.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.contabilidade.api.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID>{
	
	@Query(value = "SELECT * FROM cliente WHERE cnpj = ?", nativeQuery = true)
	Optional<Cliente> findByCnpj(String cnpj);

}
