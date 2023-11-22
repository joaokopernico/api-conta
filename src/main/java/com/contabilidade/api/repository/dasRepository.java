package com.contabilidade.api.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.contabilidade.api.domain.tabelas.fixas.DAS;

public interface dasRepository extends JpaRepository<DAS, UUID> {

	
	
	@Query(value = "Select * from DAS where anexo = ?", nativeQuery = true)
	public List<DAS> findByAnexo(int anexo);
	
}
