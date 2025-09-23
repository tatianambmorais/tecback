package br.uniesp.si.techback.controller;

import br.uniesp.si.techback.model.Endereco;
import br.uniesp.si.techback.model.Filme;
import br.uniesp.si.techback.model.Plano;
import br.uniesp.si.techback.model.dto.EnderecoDTO;
import br.uniesp.si.techback.service.EnderecoService;
import br.uniesp.si.techback.service.PlanoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
@RequiredArgsConstructor
public class EnderecoController {
    private final EnderecoService enderecoService;

    @GetMapping
    public ResponseEntity<List<Endereco>> listar() {
        List<Endereco> list = enderecoService.listar();

        if(list.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> buscarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(enderecoService.buscarPorId(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Endereco> criar(@Valid @RequestBody EnderecoDTO dto) {
        Endereco enderecoSalvo = enderecoService.criar(dto);
        return ResponseEntity.ok(enderecoSalvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            enderecoService.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Endereco> atualizar(@PathVariable Long id, @Valid @RequestBody EnderecoDTO dto) {
        try {
            Endereco filmeAtualizado = enderecoService.atualizar(id, dto);
            return ResponseEntity.ok(filmeAtualizado);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
