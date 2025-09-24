package br.uniesp.si.techback.service;

import br.uniesp.si.techback.domain.dto.PlanoDTO;
import br.uniesp.si.techback.exception.ResourceNotFoundException;
import br.uniesp.si.techback.domain.model.Plano;
import br.uniesp.si.techback.repository.PlanoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanoService {

    // com métodos toEntity e toDto

    private final PlanoRepository repository;


    public List<PlanoDTO> listar() {

        return repository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    public PlanoDTO buscarPorId(Long id) {
        Plano plano =  repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plano não encontrado com o ID: " + id));
        return toDto(plano);
    }

    @Transactional
    public Plano salvar(PlanoDTO dto) {
        Plano novoPlano = toEntity(dto);
        return repository.save(novoPlano);
    }


    @Transactional
    public PlanoDTO atualizar(Long id, PlanoDTO dto) {
        Plano planoExistente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Plano não encontrado com o ID: %d", id)
                ));
        planoExistente.setTipoPlano(dto.getTipoPlano());
        planoExistente.setPreco(dto.getPreco());
        planoExistente.setPreco(dto.getPreco());

        Plano planoAtualizado = repository.save(planoExistente);

        return toDto(planoAtualizado);
    }


    @Transactional
    public void excluir(Long id) {
        repository.deleteById(id);
    }

    public Plano toEntity(PlanoDTO dto){
        Plano plano = new Plano();

        plano.setId(dto.getId());
        plano.setTipoPlano(dto.getTipoPlano());
        plano.setPreco(dto.getPreco());

        return plano;
    }

    public PlanoDTO toDto(Plano plano){
        PlanoDTO dto = new PlanoDTO();

        dto.setId(plano.getId());
        dto.setTipoPlano(plano.getTipoPlano());
        dto.setPreco(plano.getPreco());

        return dto;
    }



}
