package dev.senzalla.implementacao_backend.service;

        import dev.senzalla.implementacao_backend.config.exception.FotoNaoEncontradaException;
        import dev.senzalla.implementacao_backend.config.exception.ServidorNaoEncontradoException;
        import dev.senzalla.implementacao_backend.model.fotopessoa.entity.FotoPessoa;
        import dev.senzalla.implementacao_backend.model.lotacao.entity.Lotacao;
        import dev.senzalla.implementacao_backend.model.lotacao.module.FotoUploadDto;
        import dev.senzalla.implementacao_backend.repository.FotoPessoaRepository;
        import dev.senzalla.implementacao_backend.repository.LotacaoRepository;
        import lombok.RequiredArgsConstructor;
        import org.springframework.stereotype.Service;
        import org.springframework.transaction.annotation.Transactional;
        import org.springframework.web.multipart.MultipartFile;

        import java.time.LocalDate;
        import java.util.UUID;

        @Service
        @RequiredArgsConstructor
        public class LotacaoFotoService {
            private final LotacaoRepository lotacaoRepository;
            private final FotoPessoaRepository fotoPessoaRepository;
            private final MinioService minioService;

            @Transactional
            public String uploadFoto(FotoUploadDto dto) throws Exception {
                Lotacao lotacao = buscarLotacaoPorId(dto.idServidor());
                String objectName = gerarNomeArquivo(dto.foto());
                String objectKey = minioService.uploadFile(dto.foto(), objectName);

                salvarFotoPessoa(lotacao, objectKey);

                return minioService.getTemporaryUrl(objectKey);
            }

            public String getFotoUrl(Integer idServidor) throws Exception {
                Lotacao lotacao = buscarLotacaoPorId(idServidor);
                FotoPessoa fotoPessoa = buscarFotoPrincipal(lotacao);
                return minioService.getTemporaryUrl(fotoPessoa.getFpUrl());
            }

            private Lotacao buscarLotacaoPorId(Integer idServidor) throws ServidorNaoEncontradoException {
                return lotacaoRepository.findById(idServidor)
                        .orElseThrow(() -> new ServidorNaoEncontradoException("Servidor com ID " + idServidor + " não encontrado"));
            }

            private FotoPessoa buscarFotoPrincipal(Lotacao lotacao) throws FotoNaoEncontradaException {
                return fotoPessoaRepository.findByPessoaAndFpPrincipal(lotacao.getPes(), true)
                        .orElseThrow(() -> new FotoNaoEncontradaException("Foto principal não encontrada para o servidor"));
            }

            private FotoPessoa salvarFotoPessoa(Lotacao lotacao, String objectKey) {
                FotoPessoa fotoPessoa = new FotoPessoa();
                fotoPessoa.setPessoa(lotacao.getPes());
                fotoPessoa.setFpUrl(objectKey);
                fotoPessoa.setFpData(LocalDate.now());
                fotoPessoa.setFpPrincipal(true);

                return fotoPessoaRepository.save(fotoPessoa);
            }

            private String gerarNomeArquivo(MultipartFile arquivo) {
                String extensao = extrairExtensao(arquivo.getOriginalFilename());
                return UUID.randomUUID().toString() + extensao;
            }

            private String extrairExtensao(String nomeArquivo) {
                int posicaoPonto = nomeArquivo.lastIndexOf(".");
                return posicaoPonto > 0 ? nomeArquivo.substring(posicaoPonto) : "";
            }
        }