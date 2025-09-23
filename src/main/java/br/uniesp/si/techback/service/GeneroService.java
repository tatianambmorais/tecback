package br.uniesp.si.techback.service;


import br.uniesp.si.techback.exception.EntidadeNaoEncontradaException;
import br.uniesp.si.techback.model.Genero;
import br.uniesp.si.techback.model.dto.GeneroDTO;
import br.uniesp.si.techback.repository.GeneroRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GeneroService {
    private GeneroRepository repository;

    public Genero criar(GeneroDTO dto) {
        Genero novoGenero = Genero.builder()
                .descricao(dto.descricao())
                .build();
        repository.save(novoGenero);
        return novoGenero;

    }

    public Genero buscarPorId(Long id) {
        Genero genero = repository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("Gênero não encontrado com o ID: " + id));
        return genero;
    }

    public List<Genero> listar() {
        return repository.findAll();
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }

    public Genero atualizar(Long id, GeneroDTO dto) {
        Genero genero = repository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("Gênero não encontrado com o ID: " + id));
        Genero generoEditado = Genero.builder()
                .id(id)
                .descricao(dto.descricao())
                .build();
        repository.save(generoEditado);
        return generoEditado;
    }
}