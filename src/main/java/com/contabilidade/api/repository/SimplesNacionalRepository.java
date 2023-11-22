package com.contabilidade.api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.contabilidade.api.domain.tabelas.SimplesNacional;

@Repository
public interface SimplesNacionalRepository extends JpaRepository<SimplesNacional, UUID>{

}
