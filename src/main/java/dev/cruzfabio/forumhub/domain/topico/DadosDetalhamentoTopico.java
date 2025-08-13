package dev.cruzfabio.forumhub.domain.topico;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record DadosDetalhamentoTopico(
    Long id,
    String titulo,
    String mensagem,
    String autor,
    String curso,
    LocalDateTime dataCriacao,
    @JsonProperty("status") StatusTopico statusTopico) {

    public DadosDetalhamentoTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getAutor(),
                topico.getCurso(), topico.getDataCriacao(), topico.getStatusTopico());
    }
}


