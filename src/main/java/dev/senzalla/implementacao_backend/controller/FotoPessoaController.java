package dev.senzalla.implementacao_backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import dev.senzalla.implementacao_backend.model.fotopessoa.entity.FotoPessoa;
import dev.senzalla.implementacao_backend.service.FotoPessoaService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/fotos-pessoa")
@AllArgsConstructor
public class FotoPessoaController {

    private final FotoPessoaService service;


    @GetMapping("/{id}")
    public ResponseEntity<String> buscarFotoPorId(@PathVariable Long id) {
        String foto =  service.buscarFotoPorId(id);
        return ResponseEntity.ok(foto);
    }

    @PostMapping
    public ResponseEntity<FotoPessoa> salvarFoto(@RequestParam("file") MultipartFile file, @RequestParam("id") Long id) {
        FotoPessoa savedFoto = service.salvarFoto(file, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFoto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFoto(@PathVariable Long id) {
        service.deletarFoto(id);
        return ResponseEntity.noContent().build();
    }
} 