package br.uniesp.si.techback.controller;

import br.uniesp.si.techback.model.Filme;
import br.uniesp.si.techback.model.Genero;
import br.uniesp.si.techback.model.dto.GeneroDTO;
import br.uniesp.si.techback.service.FilmeService;
import br.uniesp.si.techback.service.GeneroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/generos")
@RequiredArgsConstructor
public class GeneroController {

    private final GeneroService service;

    @GetMapping
    public ResponseEntity<List<Genero>> listar() {
        List<Genero> list = service.listar();

        if(list.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genero> buscarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.buscarPorId(id));
        } catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<Genero> criar(@Valid @RequestBody GeneroDTO dto) {
        Genero generoSalvo = service.criar(dto);
        return ResponseEntity.ok(generoSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Genero> atualizar(@PathVariable Long id, @Valid @RequestBody GeneroDTO dto) {
        try {
            Genero generoAtualizado = service.atualizar(id, dto);
            return ResponseEntity.ok(generoAtualizado);
        } catch (Exception e) {
            return ResponseEntity.noContent().build();
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
