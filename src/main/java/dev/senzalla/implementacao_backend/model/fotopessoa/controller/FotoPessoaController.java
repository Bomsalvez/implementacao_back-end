package dev.senzalla.implementacao_backend.model.fotopessoa.controller;

import dev.senzalla.implementacao_backend.model.fotopessoa.entity.FotoPessoa;
import dev.senzalla.implementacao_backend.model.fotopessoa.service.FotoPessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fotos-pessoa")
public class FotoPessoaController {

    private final FotoPessoaService fotoPessoaService;

    @Autowired
    public FotoPessoaController(FotoPessoaService fotoPessoaService) {
        this.fotoPessoaService = fotoPessoaService;
    }

    @GetMapping
    public ResponseEntity<List<FotoPessoa>> listarTodasFotos() {
        return ResponseEntity.ok(fotoPessoaService.listarTodasFotos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FotoPessoa> buscarFotoPorId(@PathVariable Long id) {
        return fotoPessoaService.buscarFotoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<FotoPessoa> salvarFoto(@RequestBody FotoPessoa fotoPessoa) {
        return ResponseEntity.ok(fotoPessoaService.salvarFoto(fotoPessoa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFoto(@PathVariable Long id) {
        fotoPessoaService.deletarFoto(id);
        return ResponseEntity.noContent().build();
    }
} 