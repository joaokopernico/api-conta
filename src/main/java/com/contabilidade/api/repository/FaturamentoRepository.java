package com.contabilidade.api.repository;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.contabilidade.api.domain.Faturamento;



@Repository
public interface FaturamentoRepository extends JpaRepository<Faturamento, UUID> {
	
	
	@Query(value = "SELECT * FROM tb_faturamento WHERE data = ? and cliente_id = ?", nativeQuery = true)
	Optional<Faturamento> findByDataCliente(YearMonth data, UUID id);
	
	
	@Query(value = "SELECT * FROM tb_faturamento WHERE cliente_id = ?", nativeQuery = true)
	List<Faturamento> findByCliente(UUID id);
	
 

}
