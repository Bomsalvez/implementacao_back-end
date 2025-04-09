package dev.senzalla.implementacao_backend.controller;

import dev.senzalla.implementacao_backend.model.unidade.module.UnidadeDto;
import dev.senzalla.implementacao_backend.model.unidade.module.UnidadeForm;
import dev.senzalla.implementacao_backend.service.UnidadeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/unidades")
@RequiredArgsConstructor
public class UnidadeController {

    private final UnidadeService service;

    @PostMapping
    public ResponseEntity<UnidadeDto> create(@Valid @RequestBody UnidadeForm form) {
        return ResponseEntity.ok(service.create(form));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnidadeDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<UnidadeDto>> findAll(Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UnidadeDto> update(@PathVariable Integer id, @Valid @RequestBody UnidadeForm form) {
        return ResponseEntity.ok(service.update(id, form));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
} 