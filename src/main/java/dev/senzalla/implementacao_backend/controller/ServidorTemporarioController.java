package dev.senzalla.implementacao_backend.controller;

import dev.senzalla.implementacao_backend.model.servidor.temporario.module.ServidorTemporarioDto;
import dev.senzalla.implementacao_backend.model.servidor.temporario.module.ServidorTemporarioForm;
import dev.senzalla.implementacao_backend.service.servidor.temporario.ServidorTemporarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servidores/temporarios")
@RequiredArgsConstructor
public class ServidorTemporarioController {

    private final ServidorTemporarioService service;

    @PostMapping
    public ResponseEntity<ServidorTemporarioDto> create(@Valid @RequestBody ServidorTemporarioForm form) {
        return ResponseEntity.ok(service.create(form));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServidorTemporarioDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<ServidorTemporarioDto>> findAll(Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServidorTemporarioDto> update(@PathVariable Integer id, @Valid @RequestBody ServidorTemporarioForm form) {
        return ResponseEntity.ok(service.update(id, form));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
} 