package br.uniesp.si.techback.controller;

import br.uniesp.si.techback.domain.dto.request.PlanoRequestDTO;
import br.uniesp.si.techback.domain.model.Plano;
import br.uniesp.si.techback.exception.EntidadeNaoEncontradaException;
import br.uniesp.si.techback.service.PlanoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/planos")
@RequiredArgsConstructor
public class PlanoController {
    private final PlanoService service;

    @GetMapping
    public ResponseEntity<List<Plano>> listar() {
        List<Plano> list = service.listar();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plano> buscarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.buscarPorId(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Plano> criar(@Valid @RequestBody PlanoRequestDTO dto) {
        Plano filmeSalvo = service.salvar(dto);
        return ResponseEntity.status(201).body(filmeSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Plano> atualizar(@PathVariable Long id, @Valid @RequestBody PlanoRequestDTO dto) {
        try {
            Plano planoAtualizado = service.atualizar(id, dto);
            return ResponseEntity.ok(planoAtualizado);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            service.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
