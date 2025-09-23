package br.uniesp.si.techback.repository;

import br.uniesp.si.techback.domain.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
