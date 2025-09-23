package br.uniesp.si.techback.service;

import br.uniesp.si.techback.exception.EntidadeNaoEncontradaException;
import br.uniesp.si.techback.model.Endereco;
import br.uniesp.si.techback.model.Plano;
import br.uniesp.si.techback.model.dto.EnderecoDTO;
import br.uniesp.si.techback.repository.EnderecoRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EnderecoService {
    private EnderecoRepository enderecoRepository;


    // fazendo com builder e record
    public Endereco criar(EnderecoDTO dto) {
       Endereco novoEndereco = Endereco.builder()
               .logradouro(dto.logradouro())
               .cidade(dto.cidade())
               .bairro(dto.bairro())
               .estado(dto.estado())
               .cep(dto.cep())
               .build();
        enderecoRepository.save(novoEndereco);
        return novoEndereco;

    }
    public Endereco buscarPorId(Long id) {
        Endereco endereco = enderecoRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("Endereço não encontrado com o ID: " + id));
        return endereco;
    }

    public List<Endereco> listar() {
        return enderecoRepository.findAll();
    }

    public void excluir(Long id) {
        enderecoRepository.deleteById(id);
    }

    public Endereco atualizar(Long id, EnderecoDTO dto) {
        Endereco endereco = enderecoRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("Endereço não encontrado com o ID: " + id));
        Endereco enderecoEditado = Endereco.builder()
                .id(id)
                .logradouro(dto.logradouro())
                .numero(dto.numero())
                .bairro(dto.bairro())
                .cidade(dto.cidade())
                .estado(dto.estado())
                .cep(dto.cep())
                .build();
        enderecoRepository.save(enderecoEditado);
        return enderecoEditado;
    }

}
