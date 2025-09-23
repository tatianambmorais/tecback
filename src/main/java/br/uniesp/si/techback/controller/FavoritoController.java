package br.uniesp.si.techback.controller;

import br.uniesp.si.techback.model.Favorito;
import br.uniesp.si.techback.repository.FavoritoRepository;
import br.uniesp.si.techback.service.FavoritoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favoritos")
public class FavoritoController {


    private final FavoritoService favoritoService;

    public FavoritoController(FavoritoService favoritoService) {
        this.favoritoService = favoritoService;
    }

    @PostMapping("/adicionar/{id}")
    public ResponseEntity<Void> adicionar(@PathVariable Long id) {
        favoritoService.adicionar(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        favoritoService.excluir(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Favorito>> listar(){

        List<Favorito> list = favoritoService.listar();

        if(list.isEmpty()){
            ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(list);
    }

}
