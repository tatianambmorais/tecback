package br.uniesp.si.techback.service;

import br.uniesp.si.techback.exception.EntidadeNaoEncontradaException;
import br.uniesp.si.techback.domain.model.Favorito;
import br.uniesp.si.techback.domain.model.Filme;
import br.uniesp.si.techback.repository.FavoritoRepository;
import br.uniesp.si.techback.repository.FilmeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FavoritoService {
    private final FavoritoRepository favoritoRepository;
    private final FilmeRepository filmeRepository;

    FavoritoService(FavoritoRepository favoritoRepository, FilmeRepository filmeRepository) {
        this.favoritoRepository = favoritoRepository;
        this.filmeRepository = filmeRepository;
    }

    @Transactional
    public Favorito salvar(Long id) {
        Filme filmeFavoritar = filmeRepository.findById(id).orElse(null);
        Favorito favorito = new Favorito();
        favorito.setFilme(filmeFavoritar);
        favorito.setAdicionadoEm(LocalDateTime.now());
        return favoritoRepository.save(favorito);
    }

    @Transactional
    public void excluir(Long id) {
        Filme filmeDesfavoritar = filmeRepository.findById(id).orElse(null);

        if (filmeDesfavoritar == null || filmeDesfavoritar.getId() == null){
            throw new IllegalArgumentException("Favorito ou ID não pode ser nulo");
        }
        Favorito favorito = favoritoRepository.findByFilmeId(filmeDesfavoritar.getId()).orElseThrow(() -> new EntidadeNaoEncontradaException("Filme favorito n o encontrado com o ID: " + id));
            favoritoRepository.delete(favorito);
    }
    @Transactional
    public List<Favorito> listar(){
        return favoritoRepository.findAll();
    }
}
