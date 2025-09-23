package br.uniesp.si.techback.service;


import br.uniesp.si.techback.domain.dto.request.GeneroRequestDTO;
import br.uniesp.si.techback.exception.EntidadeNaoEncontradaException;
import br.uniesp.si.techback.domain.model.Genero;
import br.uniesp.si.techback.repository.GeneroRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GeneroService {
    private GeneroRepository repository;

    public Genero buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Gênero não encontrado com o ID: " + id));
    }

    public List<Genero> listar() {
        return repository.findAll();
    }

    @Transactional
    public Genero salvar(GeneroRequestDTO dto) {
        Genero novoGenero = Genero.builder()
                .descricao(dto.descricao())
                .build();
        repository.save(novoGenero);
        return novoGenero;
    }

    @Transactional
    public Genero atualizar(Long id, GeneroRequestDTO dto) {

        if (!repository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Gênero não encontrado com o ID: " + id);
        }

        Genero generoEditado = Genero.builder()
                .id(id)
                .descricao(dto.descricao())
                .build();
        return repository.save(generoEditado);
    }


    @Transactional
    public void excluir(Long id) {
        if (!repository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Genero não encontrado com o ID: " + id);
        }
        repository.deleteById(id);
    }
}