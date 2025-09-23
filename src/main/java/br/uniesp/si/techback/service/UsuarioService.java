package br.uniesp.si.techback.service;

import br.uniesp.si.techback.domain.dto.request.UsuarioRequestDTO;
import br.uniesp.si.techback.domain.model.Plano;
import br.uniesp.si.techback.domain.model.Usuario;
import br.uniesp.si.techback.exception.EntidadeNaoEncontradaException;
import br.uniesp.si.techback.repository.PlanoRepository;
import br.uniesp.si.techback.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioService {
    private UsuarioRepository repository;
    private PlanoRepository planoRepository;

    public Usuario buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado com o ID: " + id));
    }

    public List<Usuario> listar() {
        return repository.findAll();
    }

    @Transactional
    public Usuario salvar(UsuarioRequestDTO dto) {
        Plano plano = planoRepository.findById(dto.planoId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Gênero não encontrado com ID: " + dto.planoId()));

        Usuario novoUsuario = Usuario.builder()
                .cpf(dto.cpf())
                .nome(dto.nome())
                .email(dto.email())
                .plano(plano)
                .build();
        repository.save(novoUsuario);
        return novoUsuario;

    }


    @Transactional
    public void excluir(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public Usuario atualizar(Long cpf, UsuarioRequestDTO dto) {
        Plano plano = planoRepository.findById(dto.planoId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Plano não encontrado com ID: " + dto.planoId()));
        if (!repository.existsById(cpf)) {
            throw new EntidadeNaoEncontradaException("Usuário não encontrado com o ID: " + cpf);
        }
        Usuario usuarioEditado = Usuario.builder()
                .cpf(cpf)
                .nome(dto.nome())
                .email(dto.email())
                .plano(plano)
                .build();
        repository.save(usuarioEditado);
        return usuarioEditado;
    }

}
