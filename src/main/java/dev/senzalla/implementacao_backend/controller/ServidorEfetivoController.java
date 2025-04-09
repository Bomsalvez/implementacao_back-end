package dev.senzalla.implementacao_backend.controller;

import dev.senzalla.implementacao_backend.model.servidor.efetivo.module.ServidorEfetivoDto;
import dev.senzalla.implementacao_backend.model.servidor.efetivo.module.ServidorEfetivoForm;
import dev.senzalla.implementacao_backend.service.servidor.efetivo.ServidorEfetivoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/servidores/efetivos")
@RequiredArgsConstructor
public class ServidorEfetivoController {

    private final ServidorEfetivoService service;

    @PostMapping
    public ResponseEntity<ServidorEfetivoDto> create(@Valid @RequestBody ServidorEfetivoForm form) {
        return ResponseEntity.ok(service.create(form));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServidorEfetivoDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<ServidorEfetivoDto>> findAll(Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServidorEfetivoDto> update(@PathVariable Integer id, @Valid @RequestBody ServidorEfetivoForm form) {
        return ResponseEntity.ok(service.update(id, form));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
} 