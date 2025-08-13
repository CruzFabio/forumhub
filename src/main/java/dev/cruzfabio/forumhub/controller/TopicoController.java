package dev.cruzfabio.forumhub.controller;

import dev.cruzfabio.forumhub.domain.topico.*;
import dev.cruzfabio.forumhub.domain.validacoes.ValidadorCadastroTopico;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private List<ValidadorCadastroTopico> validadorCadastroTopico;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroTopico dados, UriComponentsBuilder uriComponentsBuilder) {

        validadorCadastroTopico.forEach(v -> v.validar(dados));

        var topico = new Topico(dados);
        topicoRepository.save(topico);

        var uri = uriComponentsBuilder
                .path("/topicos/{id}")
                .buildAndExpand(topico.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoTopico(topico));
    }

    @GetMapping
    public ResponseEntity <Page<DadosListagemTopico>> listar(
            @RequestParam(required = false) String curso,
            @RequestParam(required = false) Integer ano,
            @PageableDefault(size = 10, sort = {"dataCriacao"}) Pageable paginacao) {

        Page<DadosListagemTopico> page;

        if (curso != null && ano != null) {
            page = topicoRepository.findByCursoAndAno(curso, ano, paginacao)
                    .map(DadosListagemTopico::new);
        } else {
            page = topicoRepository.findAllByAtivoTrue(paginacao)
                    .map(DadosListagemTopico::new);
        }

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var topico = topicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoTopico dados) {
        Optional<Topico> topicoOptional = topicoRepository.findById(id);

        if (topicoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Topico topico = topicoOptional.get();
        topico.atualizarDados(dados);

        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        if (!topicoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        var topico = topicoRepository.getReferenceById(id);
        topico.excluir();
        return ResponseEntity.noContent().build();
    }

}
