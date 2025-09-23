package br.uniesp.si.techback.repository;

import br.uniesp.si.techback.domain.model.Plano;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanoRepository extends JpaRepository <Plano, Long> {
}
