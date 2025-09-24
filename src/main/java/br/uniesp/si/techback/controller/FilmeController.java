package br.uniesp.si.techback.controller;

import br.uniesp.si.techback.exception.ResourceNotFoundException;
import br.uniesp.si.techback.domain.model.Filme;
import br.uniesp.si.techback.domain.dto.FilmeDTO;
import br.uniesp.si.techback.service.FilmeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filmes")
@RequiredArgsConstructor
public class FilmeController {

    private final FilmeService service;

    @GetMapping
    public ResponseEntity<List<Filme>> listar() {
        List<Filme> list = service.listar();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Filme> buscarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.buscarPorId(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Filme> criar(@Valid @RequestBody FilmeDTO dto) {
        Filme filmeSalvo = service.salvar(dto);
        return ResponseEntity.status(201).body(filmeSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Filme> atualizar(@PathVariable Long id, @Valid @RequestBody FilmeDTO dto) {
        try {
            Filme filmeAtualizado = service.atualizar(id, dto);
            return ResponseEntity.ok(filmeAtualizado);
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
