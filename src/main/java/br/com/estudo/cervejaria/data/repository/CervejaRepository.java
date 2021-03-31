package br.com.estudo.cervejaria.data.repository;

import br.com.estudo.cervejaria.data.entity.CervejaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CervejaRepository extends JpaRepository<CervejaEntity, Long> {
    Optional<CervejaEntity> findByNomeIgnoreCase(String nome);
}
