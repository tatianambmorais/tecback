package br.uniesp.si.techback.repository;

import br.uniesp.si.techback.domain.model.Genero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneroRepository extends JpaRepository<Genero, Long> {
}
