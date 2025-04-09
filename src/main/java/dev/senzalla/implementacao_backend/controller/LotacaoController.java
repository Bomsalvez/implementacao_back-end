package dev.senzalla.implementacao_backend.controller;

import dev.senzalla.implementacao_backend.model.lotacao.module.EnderecoFuncionalDto;
import dev.senzalla.implementacao_backend.model.lotacao.module.FotoUploadDto;
import dev.senzalla.implementacao_backend.model.lotacao.module.LotacaoDto;
import dev.senzalla.implementacao_backend.model.lotacao.module.LotacaoForm;
import dev.senzalla.implementacao_backend.model.lotacao.module.LotacaoServidorDto;
import dev.senzalla.implementacao_backend.service.lotacao.LotacaoFotoService;
import dev.senzalla.implementacao_backend.service.lotacao.LotacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/lotacoes")
@RequiredArgsConstructor
public class LotacaoController {

    private final LotacaoService service;
    private final LotacaoFotoService fotoService;

    @PostMapping
    public ResponseEntity<LotacaoDto> create(@Valid @RequestBody LotacaoForm form) {
        LotacaoDto lotacaoDto = service.create(form);
        return ResponseEntity.ok(lotacaoDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LotacaoDto> findById(@PathVariable Integer id) {
        LotacaoDto lotacaoDto = service.findById(id);
        return ResponseEntity.ok(lotacaoDto);
    }

    @GetMapping
    public ResponseEntity<Page<LotacaoDto>> findAll(Pageable pageable) {
        Page<LotacaoDto> lotacaoDto = service.findAll(pageable);
        return ResponseEntity.ok(lotacaoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LotacaoDto> update(@PathVariable Integer id, @Valid @RequestBody LotacaoForm form) {
        LotacaoDto lotacaoDto = service.update(id, form);
        return ResponseEntity.ok(lotacaoDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/unidade/{unidadeId}/servidores")
    public ResponseEntity<Page<LotacaoServidorDto>> findServidoresLotadosPorUnidade(@PathVariable Integer unidadeId, Pageable pageable) {
        Page<LotacaoServidorDto> servidores = service.findServidoresLotadosPorUnidade(unidadeId, pageable);
        return ResponseEntity.ok(servidores);
    }

    @GetMapping("/servidores/endereco-funcional")
    public ResponseEntity<Page<EnderecoFuncionalDto>> findEnderecoFuncionalPorNomeServidor(
            @RequestParam String nomeServidor, Pageable pageable) {
        Page<EnderecoFuncionalDto> enderecos = service.findEnderecoFuncionalPorNomeServidor(nomeServidor, pageable);
        return ResponseEntity.ok(enderecos);
    }

    @PostMapping("/servidores/{id}/foto")
    public ResponseEntity<String> uploadFoto(
            @PathVariable Integer id,
            @RequestParam("foto") MultipartFile foto) throws Exception {
        FotoUploadDto dto = new FotoUploadDto(id, foto);
        String url = fotoService.uploadFoto(dto);
        return ResponseEntity.ok(url);
    }

    @GetMapping("/servidores/{id}/foto")
    public ResponseEntity<String> getFotoUrl(@PathVariable Integer id) throws Exception {
        String url = fotoService.getFotoUrl(id);
        return ResponseEntity.ok(url);
    }
} 