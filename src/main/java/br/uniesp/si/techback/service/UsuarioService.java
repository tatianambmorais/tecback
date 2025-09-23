package br.uniesp.si.techback.service;

import br.uniesp.si.techback.domain.dto.request.UsuarioRequestDTO;
import br.uniesp.si.techback.domain.model.Endereco;
import br.uniesp.si.techback.domain.model.Plano;
import br.uniesp.si.techback.domain.model.Usuario;
import br.uniesp.si.techback.exception.EntidadeNaoEncontradaException;
import br.uniesp.si.techback.repository.EnderecoRepository;
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
    private EnderecoRepository enderecoRepository;

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
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Plano não encontrado com ID: " + dto.planoId()));

       Endereco endereco = Endereco.builder()
               .logradouro(dto.endereco().logradouro())
               .numero(dto.endereco().numero())
               .cep(dto.endereco().cep())
               .bairro(dto.endereco().bairro())
               .cidade(dto.endereco().cidade())
               .estado(dto.endereco().estado())
               .build();

        Usuario novoUsuario = Usuario.builder()
                .cpf(dto.cpf())
                .nome(dto.nome())
                .email(dto.email())
                .endereco(endereco)
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
        Usuario usuarioExistente = repository.findById(cpf)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado com o ID: " + cpf));

        Plano plano = planoRepository.findById(dto.planoId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Plano não encontrado com ID: " + dto.planoId()));

        usuarioExistente.setNome(dto.nome());
        usuarioExistente.setEmail(dto.email());
        usuarioExistente.setPlano(plano);

        if (usuarioExistente.getEndereco() != null) {
            usuarioExistente.getEndereco().setLogradouro(dto.endereco().logradouro());
            usuarioExistente.getEndereco().setNumero(dto.endereco().numero());
            usuarioExistente.getEndereco().setBairro(dto.endereco().bairro());
            usuarioExistente.getEndereco().setCidade(dto.endereco().cidade());
            usuarioExistente.getEndereco().setEstado(dto.endereco().estado());
            usuarioExistente.getEndereco().setCep(dto.endereco().cep());
        }

        return repository.save(usuarioExistente);
    }


}
