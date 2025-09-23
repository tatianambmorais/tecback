package br.uniesp.si.techback.repository;

import br.uniesp.si.techback.model.Favorito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FavoritoRepository extends JpaRepository <Favorito, Long>{
    Optional<Favorito> findByFilmeId(Long filmeId);


}
