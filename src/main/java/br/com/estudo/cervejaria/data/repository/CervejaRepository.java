package br.com.estudo.cervejaria.data.repository;

import br.com.estudo.cervejaria.data.entity.CervejaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CervejaRepository extends JpaRepository<CervejaEntity, Long> {
    Optional<CervejaEntity> findByNome(String nome);
}
