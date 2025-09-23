package br.uniesp.si.techback.service;

import br.uniesp.si.techback.domain.dto.request.PlanoRequestDTO;
import br.uniesp.si.techback.exception.EntidadeNaoEncontradaException;
import br.uniesp.si.techback.domain.model.Plano;
import br.uniesp.si.techback.repository.PlanoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanoService {
    private final PlanoRepository repository;


    public List<Plano> listar() {
        return repository.findAll();
    }

    public Plano buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Plano não encontrado com o ID: " + id));
    }

    @Transactional
    public Plano salvar(PlanoRequestDTO dto) {
        Plano novoPlano = Plano.builder()
                .tipoPlano(dto.tipoPlano())
                .preco(dto.preco())
                .build();
        return repository.save(novoPlano);
    }

    @Transactional
    public Plano atualizar(Long id, PlanoRequestDTO dto) {

        if (!repository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Plano não encontrado com o ID: " + id);
        }
        Plano planoEditado = Plano.builder()
                .id(id)
                .tipoPlano(dto.tipoPlano())
                .preco(dto.preco())
                .build();
        return repository.save(planoEditado);
    }


    @Transactional
    public void excluir(Long id) {
        repository.deleteById(id);
    }


}
