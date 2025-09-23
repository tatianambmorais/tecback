package br.uniesp.si.techback.service;

import br.uniesp.si.techback.exception.EntidadeNaoEncontradaException;
import br.uniesp.si.techback.model.Favorito;
import br.uniesp.si.techback.model.Filme;
import br.uniesp.si.techback.model.Plano;
import br.uniesp.si.techback.repository.PlanoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@RequiredArgsConstructor
public class PlanoService {
    private final PlanoRepository planoRepository;



    public Plano criar(Plano plano) {
        return planoRepository.save(plano);
    }

    public Plano buscarPorId(Long id) {
        Plano plano = planoRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("Plano não encontrado com o ID: " + id));
        return plano;
    }

    public List<Plano> listar() {
        return planoRepository.findAll();
    }

    public void excluir(Long id) {
        planoRepository.deleteById(id);
    }


}
