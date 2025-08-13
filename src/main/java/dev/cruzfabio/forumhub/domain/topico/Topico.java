package dev.cruzfabio.forumhub.domain.topico;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "topicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;

    @Enumerated(EnumType.STRING)
    private StatusTopico statusTopico;
    private String autor;
    private String curso;

    private Boolean ativo;


    public Topico(DadosCadastroTopico dados) {
        this.titulo = dados.titulo();
        this.mensagem = dados.mensagem();
        this.autor = dados.autor();
        this.curso = dados.curso();
        this.dataCriacao = LocalDateTime.now();
        this.statusTopico = StatusTopico.NAO_RESPONDIDO;
        this.ativo = true;
    }

    public void setMensagem(@NotBlank(message = "{mensagem.obrigatorio}") String mensagem) {
        this.mensagem = mensagem;
    }

    public void setStatus(StatusTopico status) {
        this.statusTopico = status;
    }


    public void atualizarDados(DadosAtualizacaoTopico dados) {
        if (dados.mensagem() != null) {
            this.mensagem = dados.mensagem();
        }
        if (dados.statusTopico() != null) {
            this.statusTopico = dados.statusTopico();
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
