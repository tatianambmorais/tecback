package br.uniesp.si.techback.repository;

import br.uniesp.si.techback.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
