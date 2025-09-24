package br.uniesp.si.techback.service;

import br.uniesp.si.techback.domain.dto.EnderecoDTO;
import br.uniesp.si.techback.domain.dto.UsuarioDTO;
import br.uniesp.si.techback.domain.model.Endereco;
import br.uniesp.si.techback.domain.model.Plano;
import br.uniesp.si.techback.domain.model.Usuario;
import br.uniesp.si.techback.exception.ResourceNotFoundException;
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

    // com métodos toEntity e toDto

    public UsuarioDTO buscarPorId(Long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o ID: " + id));
        return toDto(usuario);
    }

    public List<UsuarioDTO> listar() {
        return repository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Transactional
    public Usuario salvar(UsuarioDTO dto) {
        Usuario novoUsuario = toEntity(dto);
        return repository.save(novoUsuario);
    }

    @Transactional
    public void excluir(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public Usuario atualizar(Long cpf, UsuarioDTO dto) {
        boolean usuarioExiste = repository.existsById(dto.getId());

        if (!usuarioExiste) {
            throw new ResourceNotFoundException("Usuário não encontrado com o ID: " + dto.getId());
        }
        Usuario usuarioAtualizado = toEntity(dto);
        return repository.save(usuarioAtualizado);
    }

    public Usuario toEntity(UsuarioDTO dto){
        Plano plano = planoRepository.findById(dto.getPlanoId())
                .orElseThrow(() -> new ResourceNotFoundException("Plano não encontrado com ID: " + dto.getPlanoId()));


        Endereco endereco = Endereco.builder()
                .id(dto.getId())
                .logradouro(dto.getEndereco().getLogradouro())
                .numero(dto.getEndereco().getNumero())
                .cep(dto.getEndereco().getCep())
                .bairro(dto.getEndereco().getBairro())
                .cidade(dto.getEndereco().getCidade())
                .estado(dto.getEndereco().getEstado())
                .build();

        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setCpf(dto.getCpf());
        usuario.setEndereco(endereco);
        usuario.setEmail(dto.getEmail());
        usuario.setPlano(plano);
        return usuario;
    }

    public UsuarioDTO toDto(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getCpf());
        dto.setNome(usuario.getNome());
        dto.setCpf(usuario.getCpf());
        dto.setEmail(usuario.getEmail());
        dto.setPlanoId(usuario.getPlano().getId());

        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setId(usuario.getEndereco().getId());
        enderecoDTO.setLogradouro(usuario.getEndereco().getLogradouro());
        enderecoDTO.setNumero(usuario.getEndereco().getNumero());
        enderecoDTO.setCep(usuario.getEndereco().getCep());
        enderecoDTO.setBairro(usuario.getEndereco().getBairro());
        enderecoDTO.setCidade(usuario.getEndereco().getCidade());
        enderecoDTO.setEstado(usuario.getEndereco().getEstado());

        dto.setEndereco(enderecoDTO);
        return dto;
    }


}
