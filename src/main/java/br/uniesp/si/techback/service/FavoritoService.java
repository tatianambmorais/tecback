package br.uniesp.si.techback.service;

import br.uniesp.si.techback.exception.EntidadeNaoEncontradaException;
import br.uniesp.si.techback.model.Favorito;
import br.uniesp.si.techback.model.Filme;
import br.uniesp.si.techback.repository.FavoritoRepository;
import br.uniesp.si.techback.repository.FilmeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FavoritoService {
    private final FavoritoRepository favoritoRepository;
    private final FilmeRepository filmeRepository;

    FavoritoService(FavoritoRepository favoritoRepository, FilmeRepository filmeRepository) {
        this.favoritoRepository = favoritoRepository;
        this.filmeRepository = filmeRepository;
    }

    public Favorito adicionar(Long id) {
        Filme filmeFavoritar = filmeRepository.findById(id).orElse(null);
        Favorito favorito = new Favorito();
        favorito.setFilme(filmeFavoritar);
        favorito.setAdicionadoEm(LocalDateTime.now());
        return favoritoRepository.save(favorito);
    }

    public void excluir(Long id) {
        Filme filmeDesfavoritar = filmeRepository.findById(id).orElse(null);

        if (filmeDesfavoritar == null || filmeDesfavoritar.getId() == null){
            throw new IllegalArgumentException("Favorito ou ID não pode ser nulo");
        }
        Favorito favorito = favoritoRepository.findByFilmeId(filmeDesfavoritar.getId()).orElseThrow(() -> new EntidadeNaoEncontradaException("Filme favorito n o encontrado com o ID: " + id));
            favoritoRepository.delete(favorito);
    }

    public List<Favorito> listar(){
        return favoritoRepository.findAll();
    }
}
