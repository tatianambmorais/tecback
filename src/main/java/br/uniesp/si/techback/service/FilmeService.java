package br.uniesp.si.techback.service;

import br.uniesp.si.techback.exception.EntidadeNaoEncontradaException;
import br.uniesp.si.techback.domain.model.Filme;
import br.uniesp.si.techback.domain.model.Genero;
import br.uniesp.si.techback.domain.dto.request.FilmeRequestDTO;
import br.uniesp.si.techback.repository.FilmeRepository;
import br.uniesp.si.techback.repository.GeneroRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmeService {

    private final FilmeRepository repository;
    private final GeneroRepository generoRepository;

    public List<Filme> listar() {
        return repository.findAll();
    }

    public Filme buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Filme não encontrado com o ID: " + id));
    }

    @Transactional
    public Filme salvar(FilmeRequestDTO dto) {

        Genero genero = generoRepository.findById(dto.generoId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Gênero não encontrado com ID: " + dto.generoId()));

        Filme filme = Filme.builder()
                .titulo(dto.titulo())
                .genero(genero)
                .sinopse(dto.sinopse())
                .dataLancamento(dto.dataLancamento())
                .duracaoMinutos(dto.duracaoMinutos())
                .classificacaoIndicativa(dto.classificacaoIndicativa())
                .build();
        return repository.save(filme);
    }

    @Transactional
    public Filme atualizar(Long id, FilmeRequestDTO dto) {
        Genero genero = generoRepository.findById(dto.generoId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Gênero não encontrado com ID: " + dto.generoId()));

        if (!repository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Filme não encontrado com o ID: " + id);
        }

       Filme filmeEditado = Filme.builder()
               .id(id)
               .titulo(dto.titulo())
               .genero(genero)
               .sinopse(dto.sinopse())
               .dataLancamento(dto.dataLancamento())
               .duracaoMinutos(dto.duracaoMinutos())
               .classificacaoIndicativa(dto.classificacaoIndicativa())
               .build();
        return repository.save(filmeEditado);
    }

    @Transactional
    public void excluir(Long id) {
        if (!repository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Filme não encontrado com o ID: " + id);
        }
        repository.deleteById(id);
    }
}
