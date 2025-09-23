package br.uniesp.si.techback.service;

import br.uniesp.si.techback.domain.dto.request.EnderecoRequestDTO;
import br.uniesp.si.techback.exception.EntidadeNaoEncontradaException;
import br.uniesp.si.techback.domain.model.Endereco;
import br.uniesp.si.techback.repository.EnderecoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EnderecoService {
    private EnderecoRepository repository;

    public List<Endereco> listar() {
        return repository.findAll();
    }

    public Endereco buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Endereço não encontrado com o ID: " + id));
    }

    @Transactional
    public Endereco salvar(EnderecoRequestDTO dto) {
       Endereco novoEndereco = Endereco.builder()
               .logradouro(dto.logradouro())
               .cidade(dto.cidade())
               .bairro(dto.bairro())
               .estado(dto.estado())
               .cep(dto.cep())
               .build();
        return repository.save(novoEndereco);
    }

    @Transactional
    public Endereco atualizar(Long id, EnderecoRequestDTO dto) {

        if (!repository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Endereço não encontrado com o ID: " + id);
        }

        Endereco enderecoEditado = Endereco.builder()
                .id(id)
                .logradouro(dto.logradouro())
                .numero(dto.numero())
                .bairro(dto.bairro())
                .cidade(dto.cidade())
                .estado(dto.estado())
                .cep(dto.cep())
                .build();
        return repository.save(enderecoEditado);
    }

    @Transactional
    public void excluir(Long id) {
        if (!repository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Endereço não encontrado com o ID: " + id);
        }
        repository.deleteById(id);
    }

}
