package dev.cruzfabio.forumhub.domain.topico;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoTopico(
        @NotBlank
        String mensagem,
        @NotNull
        @JsonProperty("status")
        StatusTopico statusTopico
) {
}
