package com.contabilidade.api.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.contabilidade.api.domain.tabelas.fixas.IRRF;

public interface irrfRepository extends JpaRepository<IRRF, UUID>{

}
