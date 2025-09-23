package br.uniesp.si.techback.controller;

import br.uniesp.si.techback.domain.model.Favorito;
import br.uniesp.si.techback.service.FavoritoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/favoritos")
public class FavoritoController {

    private final FavoritoService service;

    @GetMapping
    public ResponseEntity<List<Favorito>> listar(){
        List<Favorito> list = service.listar();
        return ResponseEntity.ok(list);
    }

    @PostMapping("/adicionar/{id}")
    public ResponseEntity<Void> criar(@PathVariable Long id) {
        service.salvar(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        try {
            service.excluir(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
