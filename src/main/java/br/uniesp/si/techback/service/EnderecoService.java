package br.uniesp.si.techback.service;

import br.uniesp.si.techback.domain.dto.EnderecoDTO;
import br.uniesp.si.techback.exception.ResourceNotFoundException;
import br.uniesp.si.techback.domain.model.Endereco;
import br.uniesp.si.techback.repository.EnderecoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EnderecoService {

    // com métodos toEntity e toDto

    private EnderecoRepository repository;

    public List<Endereco> listar() {
        return repository.findAll();
    }

    public Endereco buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado com o ID: " + id));
    }

    @Transactional
    public Endereco salvar(EnderecoDTO dto) {
       Endereco novoEndereco = Endereco.builder()
               .logradouro(dto.getLogradouro())
               .cidade(dto.getCidade())
               .bairro(dto.getBairro())
               .estado(dto.getEstado())
               .cep(dto.getCep())
               .build();
        return repository.save(novoEndereco);
    }

    @Transactional
    public Endereco atualizar(Long id, EnderecoDTO dto) {

        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Endereço não encontrado com o ID: " + id);
        }

        Endereco enderecoEditado = Endereco.builder()
                .id(id)
                .logradouro(dto.getLogradouro())
                .numero(dto.getNumero())
                .bairro(dto.getBairro())
                .cidade(dto.getCidade())
                .estado(dto.getEstado())
                .cep(dto.getCep())
                .build();
        return repository.save(enderecoEditado);
    }

    @Transactional
    public void excluir(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Endereço não encontrado com o ID: " + id);
        }
        repository.deleteById(id);
    }

}
