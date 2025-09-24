package br.uniesp.si.techback.controller;

import br.uniesp.si.techback.domain.dto.PlanoDTO;
import br.uniesp.si.techback.domain.model.Plano;
import br.uniesp.si.techback.exception.ResourceNotFoundException;
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
    public ResponseEntity<List<PlanoDTO>> listar() {
        List<PlanoDTO> list = service.listar();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanoDTO> buscarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.buscarPorId(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Plano> criar(@Valid @RequestBody PlanoDTO dto) {
        Plano filmeSalvo = service.salvar(dto);
        return ResponseEntity.status(201).body(filmeSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanoDTO> atualizar(@PathVariable Long id, @Valid @RequestBody PlanoDTO dto) {
        try {
            PlanoDTO planoAtualizado = service.atualizar(id, dto);
            return ResponseEntity.ok(planoAtualizado);
        } catch (ResourceNotFoundException e) {
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
